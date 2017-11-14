package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {

    Meal create(Meal meal, Integer UserId);

    void delete(int id, Integer userId) throws NotFoundException;

    Meal get(int id, Integer userId) throws NotFoundException;

    void update(Meal meal, Integer userId);

    List<Meal> getAll(Integer userId, LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate);
}