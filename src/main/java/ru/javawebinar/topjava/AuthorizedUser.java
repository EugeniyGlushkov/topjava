package ru.javawebinar.topjava;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class AuthorizedUser {
    private static Integer id = 1;

    private static LocalDate dateMin = null,
            dateMax = null;


    private static LocalTime timeMin = null,
            timeMax = null;

    public static int id() {
        return id;
    }

    public static void setId(Integer id) {
        AuthorizedUser.id = id;
    }

    public static int getCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }

    static public LocalDate getDateMin() {
        return dateMin;
    }

    static public LocalDate getDateMax() {
        return dateMax;
    }

    static public LocalTime getTimeMin() {
        return timeMin;
    }

    static public LocalTime getTimeMax() {
        return timeMax;
    }

    static public void setDateMin(LocalDate datMin) {
        dateMin = datMin;
    }

    static public void setDateMax(LocalDate datMax) {
        dateMax = datMax;
    }

    static public void setTimeMin(LocalTime timMin) {
        timeMin = timMin;
    }

    static public void setTimeMax(LocalTime timMax) {
        timeMax = timMax;
    }

    public static void resetFilter(){
        dateMin = null;
        dateMax = null;
        timeMin = null;
        timeMax = null;
    }
}