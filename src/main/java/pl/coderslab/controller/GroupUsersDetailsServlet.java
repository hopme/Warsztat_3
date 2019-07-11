package pl.coderslab.controller;

import pl.coderslab.dao.ExcerciseDao;
import pl.coderslab.dao.GroupDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.Excercise;
import pl.coderslab.model.Group;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/groupUsersDetails")
public class GroupUsersDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userid"));

        ExcerciseDao excerciseDao = new ExcerciseDao();
        ArrayList<Excercise> userExcercises = excerciseDao.findAllAssignedToUserId(4);
        request.setAttribute("userExcercises", userExcercises);

        UserDao userDao = new UserDao();
        User currentUser = userDao.read(userId);
        request.setAttribute("currentUser", currentUser);

        getServletContext().getRequestDispatcher("/currentuser.jsp")
                .forward(request, response);

    }
}
