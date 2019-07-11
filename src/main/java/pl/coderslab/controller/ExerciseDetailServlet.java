package pl.coderslab.controller;

import pl.coderslab.dao.ExcerciseDao;
import pl.coderslab.model.Excercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exerciseDetails")
public class ExerciseDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));

        ExcerciseDao excerciseDao = new ExcerciseDao();
        Excercise currentExercise = excerciseDao.read(userId);

        request.setAttribute("currentExercise", currentExercise);
        getServletContext().getRequestDispatcher("/currentexercise.jsp")
                .forward(request, response);

    }
}
