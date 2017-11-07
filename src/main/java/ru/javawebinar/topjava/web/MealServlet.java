package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.DAO.DAO;
import ru.javawebinar.topjava.DAO.MapDAOImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet{
    private DAO mapDao = new MapDAOImpl();
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");

        String act = request.getParameter("act");

        if (act != null) {
            switch (act){
                case "del":
                    mapDao.deleteById(Integer.parseInt(request.getParameter("val")));
                    break;
            }
        }

        List<Meal> currentMeals = mapDao.getList();

        request.setAttribute("mealsList", getMealWithExceed(currentMeals, 2000));
        request.getRequestDispatcher("meals.jsp").forward(request, response);

//        response.sendRedirect("meals.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("doPost");

        req.setCharacterEncoding("UTF-8");

        mapDao.create(LocalDateTime.parse(req.getParameter("datetime")), req.getParameter("description"), Integer.parseInt(req.getParameter("calories")));

        List<Meal> currentMeals = mapDao.getList();

        req.setAttribute("mealsList", getMealWithExceed(currentMeals, 2000));
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }

    public List<MealWithExceed> getMealWithExceed(List<Meal> meals, int caloriesPerDay){
        Map<LocalDate, Integer> datesWithCalories = meals
                .stream()
                .collect(Collectors.toMap(
                        p -> p.getDateTime().toLocalDate(),
                        Meal::getCalories,
                        Integer::sum));

        return meals.stream()
                .map(m -> new MealWithExceed(m.getID(), m.getDateTime(), m.getDescription(), m.getCalories(),
                        datesWithCalories.get(m.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
