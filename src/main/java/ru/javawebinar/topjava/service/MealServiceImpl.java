package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(int userId, Meal meal) {
        return repository.save(userId,meal);
    }

    @Override
    public void delete(int userId, int id) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(userId,id),id);

    }

    @Override
    public Meal get(int userId, int id) throws NotFoundException {
       return ExceptionUtil.checkNotFoundWithId(repository.get(userId,id),userId);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Collection<Meal> getAllFilteredByDate(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.getFilteredByDate(userId,startDate,endDate);
    }
}
