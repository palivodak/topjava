package ru.javawebinar.topjava.controller;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Кирилл on 12.09.2016.
 */
public interface DaoInterface {
    public void add(Meal meal);
    public void delete(int id);
    public void edit(Meal meal);
    public List<MealWithExceed> getAllMealList();
    public Meal getById(int id);
}
