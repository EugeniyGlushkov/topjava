package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface DAO {
    Meal add(Meal meal);
    Meal readById(int id);
    Meal deleteById(int id);
    List<Meal> getList();
}
