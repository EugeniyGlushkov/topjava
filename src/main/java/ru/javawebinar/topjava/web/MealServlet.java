package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.DAO.DAO;
import ru.javawebinar.topjava.DAO.MapDAOImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MealServlet extends HttpServlet{
    private DAO mapDao;
    private static final Logger log = getLogger(MealServlet.class);
    private static String action;
    private static int currentID;

    @Override
    public void init() throws ServletException {
        mapDao = new MapDAOImpl();
        action = "add";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        action = "add";

        List<Meal> currentMeals = mapDao.getList();
        request.setAttribute("currentid", currentID);
        request.setAttribute("action", action);
        request.setAttribute("mealsList", getMealWithExceed(currentMeals, 2000));
        request.getRequestDispatcher("meals.jsp").forward(request, response);

//        response.sendRedirect("meals.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("doPost");
        req.setCharacterEncoding("UTF-8");

        String delPar = req.getParameter("action");
        switch (req.getParameter("action")){
            case "del":
                mapDao.deleteById(Integer.parseInt(req.getParameter("ID")));
                break;
            case "add":
                if (action.equals("edit")){
                    mapDao.add(new Meal(currentID, LocalDateTime.parse(req.getParameter("datetime")),
                            req.getParameter("description"), Integer.parseInt(req.getParameter("calories"))));
                } else {
                    mapDao.add(getNewMeal(LocalDateTime.parse(req.getParameter("datetime")),
                            req.getParameter("description"), Integer.parseInt(req.getParameter("calories"))));
                }
                action = "add";
                break;
            case "edit":
                action = "edit";
                currentID = Integer.parseInt(req.getParameter("ID"));
                break;
        }

        List<Meal> currentMeals = mapDao.getList();
        req.setAttribute("currentid", currentID);
        req.setAttribute("action", action);
        req.setAttribute("mealsList", getMealWithExceed(currentMeals, 2000));
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }


}
