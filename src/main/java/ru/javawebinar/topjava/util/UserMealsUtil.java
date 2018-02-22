package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> userMealWithExceeds = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        for (UserMealWithExceed meal : userMealWithExceeds) {
            System.out.println(meal);
        }
//        .toLocalDate();
//        .toLocalTime();
    }

    /*public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        Map<LocalDate, Integer> caloriesInDay = new HashMap<>();

        for (UserMeal meal : mealList) {
            caloriesInDay.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), (x, y) -> x + y);
        }

        List<UserMealWithExceed> userMealWithExceeds = new ArrayList<>();

        for (UserMeal userMeal : mealList) {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                userMealWithExceeds.add(new UserMealWithExceed(userMeal,
                        caloriesInDay.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }

        return userMealWithExceeds;
    }*/

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field

        Map<LocalDate, Integer> caloriesInDay = new HashMap<>();

        return mealList.stream().map(m -> {
            caloriesInDay.merge(m.getDateTime().toLocalDate(), m.getCalories(), (x, y) -> x + y);
            return m;
        })
                .filter(m -> TimeUtil.isBetween(m.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.toList())
                .stream()
                .map(m -> new UserMealWithExceed(m,
                        caloriesInDay.get(m.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
