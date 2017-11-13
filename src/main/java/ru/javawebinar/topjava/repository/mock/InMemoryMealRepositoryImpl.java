package ru.javawebinar.topjava.repository.mock;

import org.omg.CORBA.INTERNAL;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, Integer userId) {
        if (!repository.containsKey(id) || repository.get(id).getUserId() != userId) {
            return false;
        }

        repository.remove(id);
        return true;
    }

    @Override
    public Meal get(int id, Integer userId) {
        Meal crntMeal = repository.get(id);

        return crntMeal != null && crntMeal.getUserId() == userId ? crntMeal : null;
    }

    @Override
    public Collection<Meal> getAll(Integer userId) {
        List<Meal> meals = repository.values().stream().filter(meal -> meal.getUserId() == userId).
                collect(Collectors.toList());
        meals.sort((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()));
        return meals;
    }
}

