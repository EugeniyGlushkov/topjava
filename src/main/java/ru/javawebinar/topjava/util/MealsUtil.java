package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MealsUtil {
    private static AtomicInteger idCounter = new AtomicInteger(0);

    public static void main(String[] args) {
//        .toLocalDate();
//        .toLocalTime();
    }

    public static Meal getNewMeal(LocalDateTime dateTime, String description, int calories){
        return new Meal(idCounter.incrementAndGet(), dateTime, description, calories);
    }

    public static List<MealWithExceed> getMealWithExceed(List<Meal> meals, int caloriesPerDay){
        Map<LocalDate, Integer> datesWithCalories = meals
                .stream()
                .collect(Collectors.toMap(
                        p -> p.getDateTime().toLocalDate(),
                        Meal::getCalories,
                        Integer::sum));

        return meals.stream()
                .map(m -> new MealWithExceed(m.getID(), m.getDateTime(), m.getDescription(), m.getCalories(),
                        datesWithCalories.get(m.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
