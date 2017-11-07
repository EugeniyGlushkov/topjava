package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapDAOImpl implements DAO {
    private static volatile int idCounter = 1;
    private final Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();

    {
        create(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500);
        create(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000);
        create(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500);
        create(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000);
        create(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500);
        create(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510);
    }

    @Override
    public Meal create(LocalDateTime dateTime, String description, int calories) {
        synchronized (mealMap){
            return mealMap.put(idCounter, new Meal(idCounter++, dateTime, description, calories));
        }

    }

    @Override
    public Meal readById(int id) {
        return mealMap.get(id);
    }

    @Override
    public Meal updateById(int id, LocalDateTime dateTime, String description, int calories) {
        return mealMap.put(id, new Meal(id, dateTime, description, calories));
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
