package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface DAO {
    Meal create(LocalDateTime dateTime, String description, int calories);
    Meal readById(int id);
    Meal updateById(int id, LocalDateTime dateTime, String description, int calories);
    Meal deleteById(int id);
    List<Meal> getList();
}
