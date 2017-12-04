package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {
    //del
    private static final Sort SORT_DATETIME = new Sort(Sort.Direction.DESC, "dateTime");

    @Autowired
    private CrudMealRepository crudRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public Meal save(Meal Meal, int userId) {
        User ref = crudUserRepository.findById(userId).orElse(null);

        if (ref == null) {
            return null;
        }
        Meal.setUser(ref);
        if (!Meal.isNew() && userId != crudRepository.findById(Meal.getId()).get().getUser().getId()){
            return null;
        }

        return crudRepository.save(Meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal ref = crudRepository.findById(id).orElse(null);
        return ref == null ? null : ref.getUser().getId() == userId ? ref : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findAll(userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId)
    {
        return crudRepository.findAllBetween(startDate, endDate, userId);
    }
}
