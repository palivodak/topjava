package ru.javawebinar.topjava.repository.mock;

import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Map<Integer,Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    

    @Override
    public Meal save(int userId,Meal meal) {
        if(!repository.containsKey(userId)){
            Map<Integer, Meal> mealMap=new ConcurrentHashMap<>();
            meal.setId(counter.incrementAndGet());
            mealMap.put(meal.getId(),meal);
            repository.put(userId,mealMap);
        }
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        Map<Integer, Meal> mealMap=repository.get(userId);
        mealMap.put(meal.getId(),meal);
        repository.put(userId,mealMap);
        return meal;
    }

    @Override
    public Collection<Meal> getFilteredByDate(int userId,LocalDate startDate, LocalDate endDate) {
        return getAll(userId).stream()
                .filter(t->t.getDateTime().toLocalDate().isBefore(endDate.plusDays(1))&&t.getDateTime().toLocalDate().isAfter(startDate.minusDays(1)))
                .collect(Collectors.toList());

    }

    @Override
    public boolean delete(int userId, int id) {
        if(repository.containsKey(userId)&&repository.get(userId).containsKey(id)){
        repository.get(userId).remove(id);
        return true;}
        else return false;
    }

    @Override
    public Meal get(int userId,int id) {
                return repository.get(userId).get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        if(repository.get(userId)==null){return Collections.emptyList();}
            else{
       return repository.get(userId).values().stream()
                .sorted((t1,t2)-> t2.getDateTime().compareTo(t1.getDateTime()))
                .collect(Collectors.toList());}



    }
}

