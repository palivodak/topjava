package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.controller.DaoMemoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Кирилл on 11.09.2016.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    private DaoMemoryImpl dao;
    private static String INSERT_OR_EDIT = "meal.jsp";
    private static String LIST_MEALS = "mealList.jsp";

    public MealServlet() {
        super();
        dao=new DaoMemoryImpl();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect to mealList");

        String forward="";
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(req.getParameter("mealId"));
            dao.delete(mealId);
            forward = LIST_MEALS;
            req.setAttribute("meals", dao.getAllMealList());
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(req.getParameter("mealId"));
            Meal meal= dao.getById(mealId);
            req.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeals")){
            forward = LIST_MEALS;
            req.setAttribute("meals", dao.getAllMealList());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Meal meal= new Meal();
        String mealId=req.getParameter("mealId");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime=LocalDateTime.parse(req.getParameter("dateTime"),formatter);
        meal.setDateTime(dateTime);
        meal.setDescription(req.getParameter("mealDescription"));
        meal.setCalories(Integer.parseInt(req.getParameter("calories")));
        if(mealId == null || mealId.isEmpty())
        {
            dao.add(meal);
        }
        else
        {
            meal.setId(Integer.parseInt(mealId));
            dao.edit(meal);
        }

        RequestDispatcher view = req.getRequestDispatcher(LIST_MEALS);
        req.setAttribute("meals", dao.getAllMealList());
        view.forward(req, resp);
    }
}
