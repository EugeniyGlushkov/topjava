package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MapDAOImpl implements DAO {
    private final Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();

    {
        add(getNewMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500));
        add(getNewMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000));
        add(getNewMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500));
        add(getNewMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000));
        add(getNewMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500));
        add(getNewMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510));
    }

    @Override
    public Meal add(Meal meal) {
            return mealMap.put(meal.getID(), meal);
    }

    @Override
    public Meal readById(int id) {
        return mealMap.get(id);
    }

    @Override
    public Meal deleteById(int id) {
        return mealMap.remove(id);
    }

    @Override
    public List<Meal> getList() {
        return new ArrayList<>(mealMap.values());
    }
}
