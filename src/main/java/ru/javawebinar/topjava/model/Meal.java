package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class Meal {
    private final int ID;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public Meal(int id, LocalDateTime dateTime, String description, int calories) {
        this.ID = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public int getID() {
        return ID;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }
}
