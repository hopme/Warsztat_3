package pl.coderslab.controller;

import pl.coderslab.dao.UserDao;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/manageUsers")
public class ManageUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String newUserName = request.getParameter("newUserName");
        String email = request.getParameter("newEmail");
        String password = request.getParameter("newPassword");
        UserDao userDao = new UserDao();
        try {
            Integer userId = Integer.parseInt(request.getParameter("userid"));
            User user = new User(userId, newUserName, email, password);
            userDao.update(user);
        } catch (NumberFormatException e) {
            User user = new User(newUserName, email, password);
            userDao.create(user);
        }
        response.sendRedirect(request.getContextPath() + "/manageUsers");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        UserDao userDao = new UserDao();

        if (action == null) {

            ArrayList allUsers = userDao.findAll();
            request.setAttribute("allUsers", allUsers);
            getServletContext().getRequestDispatcher("/manageusers.jsp")
                    .forward(request, response);

        } else if (action.equals("add")){

            getServletContext().getRequestDispatcher("/adduser.jsp")
                    .forward(request, response);

        }

        else if (action.equals("edit")){
            int userId = Integer.parseInt(request.getParameter("userid"));
            User currentUser= userDao.read(userId);
            request.setAttribute("currentUser", currentUser);
            getServletContext().getRequestDispatcher("/edituser.jsp")
                    .forward(request, response);

        } else if (action.equals("delete")){
            int userId = Integer.parseInt(request.getParameter("userid"));
            userDao.delete(userId);
            response.sendRedirect(request.getContextPath() + "/manageUsers");

        }
    }
}
