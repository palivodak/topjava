package ru.javawebinar.topjava.controller;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Кирилл on 12.09.2016.
 */
public class DaoMemoryImpl implements DaoInterface {
    public ConcurrentHashMap<Integer,Meal> memoryStorage=new ConcurrentHashMap();
    public AtomicInteger counter=new AtomicInteger(1);


    @Override
    public void add(Meal meal) {
        meal.setId(counter.get());
        memoryStorage.put(counter.getAndIncrement(),meal);

    }

    @Override
    public void delete(int id) {
        memoryStorage.remove(id);
    }

    @Override
    public void edit(Meal meal) {
        memoryStorage.put(meal.getId(),meal);
    }

    @Override
    public List<MealWithExceed> getAllMealList(){
        List<MealWithExceed> list=new ArrayList<>();
        List<Meal> tmp=new ArrayList<>();
        for(Map.Entry<Integer,Meal> map:memoryStorage.entrySet()){
            tmp.add(map.getValue());
        }
        list=MealsUtil.getFilteredWithExceeded(tmp,LocalTime.MIN,LocalTime.MAX,2000);
        return list;
    }

    @Override
    public Meal getById(int id) {
        return memoryStorage.get(id);
    }
}
