package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.AuthorizedUser.*;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll() {
        log.info("getAll");
        return MealsUtil.getWithExceeded(service.getAll(id(), getTimeMin(), getTimeMax(), getDateMin(), getDateMax()), AuthorizedUser.getCaloriesPerDay());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, id());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, id());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, id());
    }

    public void update(Meal meal) {
        log.info("update {} with id={}", meal);
        service.update(meal, id());
    }

    public void choose(Integer autUserId){
        AuthorizedUser.setId(autUserId);
    }

    public void resetFilter(){
        AuthorizedUser.resetFilter();
    }

    public void setMinDate(String minDate, String maxDate, String minTime, String maxTime){
        if (!minDate.isEmpty()) {
            AuthorizedUser.setDateMin(LocalDate.parse(minDate));
        }

        if (!maxDate.isEmpty()) {
            AuthorizedUser.setDateMax(LocalDate.parse(maxDate));
        }

        if (!minTime.isEmpty()) {
            AuthorizedUser.setTimeMin(LocalTime.parse(minTime));
        }

        if (!maxTime.isEmpty()) {
            AuthorizedUser.setTimeMax(LocalTime.parse(maxTime));
        }
    }
}