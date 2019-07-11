package pl.coderslab.controller;

import pl.coderslab.dao.ExcerciseDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.Excercise;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/manageExercises")
public class ManageExercisesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("newtitle");
        String  description = request.getParameter("newdescription");
        ExcerciseDao excerciseDao = new ExcerciseDao();

        try {
            int exId = Integer.parseInt(request.getParameter("exid"));
            Excercise excercise = new Excercise(exId, title, description);
            excerciseDao.update(excercise);
        } catch (NumberFormatException e) {
            Excercise excercise = new Excercise(title, description);
            excerciseDao.create(excercise);
        }
        response.sendRedirect(request.getContextPath() + "/manageExercises");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        ExcerciseDao excerciseDao = new ExcerciseDao();

        if (action == null) {

            ArrayList allExercises = excerciseDao.findAll();
            request.setAttribute("allExercises", allExercises);
            getServletContext().getRequestDispatcher("/manageexercises.jsp")
                    .forward(request, response);

        } else if (action.equals("add")){

            getServletContext().getRequestDispatcher("/addexercise.jsp")
                    .forward(request, response);

        } else if (action.equals("edit")){
            int exId = Integer.parseInt(request.getParameter("exid"));
            Excercise currentExercise= excerciseDao.read(exId);
            request.setAttribute("currentExercise", currentExercise);
            getServletContext().getRequestDispatcher("/editexercise.jsp")
                    .forward(request, response);

        } else if (action.equals("delete")){
            int exId = Integer.parseInt(request.getParameter("exid"));
            excerciseDao.delete(exId);
            response.sendRedirect(request.getContextPath() + "/manageExercises");

        }
    }
}