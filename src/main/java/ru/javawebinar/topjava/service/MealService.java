package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface MealService {
    Meal save(int userId,Meal meal);
    void delete(int userId,int id) throws NotFoundException;
    Meal get(int userId, int id) throws NotFoundException;
    Collection<Meal> getAll(int userId);
    Collection<Meal> getAllFilteredByDate(int userId, LocalDate startDate, LocalDate endDate);

}
