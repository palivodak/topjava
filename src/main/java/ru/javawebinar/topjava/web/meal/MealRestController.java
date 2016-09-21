package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {
    @Autowired
    private MealService service;

public Collection<MealWithExceed> getAll(){
    return MealsUtil.getWithExceeded(
            service.getAll(AuthorizedUser.id()),AuthorizedUser.getCaloriesPerDay());
    }

    public Collection<MealWithExceed> getFilteredByDate(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime){
       return MealsUtil.getWithExceeded(service.getAllFilteredByDate(AuthorizedUser.id(),startDate,endDate),AuthorizedUser.getCaloriesPerDay()).stream()
               .filter(t-> TimeUtil.isBetween(t.getDateTime().toLocalTime(),startTime,endTime))
               .collect(Collectors.toList());
    }

    public void delete(int mealId){
        service.delete(AuthorizedUser.id(),mealId);
    }

    public Meal save(Meal meal){
        return service.save(AuthorizedUser.id(),meal);
    }

    public Meal get(int mealId){
        return service.get(AuthorizedUser.id(),mealId);
    }

}
