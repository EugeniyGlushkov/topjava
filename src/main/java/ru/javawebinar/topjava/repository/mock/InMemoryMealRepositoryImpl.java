package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        for (int i = 0, end = MealsUtil.MEALS.size();i < end;i++) {
            save(MealsUtil.MEALS.get(i), i / 3 + 1);
        }
    }

    @Override
    public Meal save(Meal meal, Integer userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }

        if (!repository.containsKey(userId)) {

            repository.put(userId, new HashMap<>());
        }

        repository.get(userId).put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, Integer userId) {
        if (!repository.containsKey(userId) || !repository.get(userId).containsKey(id)) {
            return false;
        }

        repository.get(userId).remove(id);

        if (repository.get(userId).isEmpty()) {
            repository.remove(userId);
        }
        return true;
    }

    @Override
    public Meal get(int id, Integer userId) {
        return repository.containsKey(userId) && repository.get(userId).containsKey(id) ?
                repository.get(userId).get(id) : null;
    }

    @Override
    public Collection<Meal> getAll(Integer userId, LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate) {
        if (!repository.containsKey(userId)) {
            return new ArrayList<>();
        }
        List<Meal> meals = repository.get(userId).values().stream().filter(meal ->
                DateTimeUtil.dateTimeIsBetween(meal.getDate(), startDate != null ? startDate : LocalDate.MIN,
                        endDate != null ? endDate : LocalDate.MAX) &&
                        DateTimeUtil.dateTimeIsBetween(meal.getTime(), startTime != null ? startTime : LocalTime.MIN,
                                endTime != null ? endTime : LocalTime.MAX)).collect(Collectors.toList());

        meals.sort((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()));
        return meals;
    }
}

