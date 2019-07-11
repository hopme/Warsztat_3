package pl.coderslab.controller;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/usersInGroup")
public class UsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int groupId = Integer.parseInt(request.getParameter("id"));

        GroupDao groupDao = new GroupDao();
        Group currentGroup = groupDao.read(groupId);
        request.setAttribute("currentGroup", currentGroup);

        UserDao userDao = new UserDao();
        ArrayList usersInGroup = userDao.findAllByGroupId(groupId);
        request.setAttribute("usersInGroup", usersInGroup);
        getServletContext().getRequestDispatcher("/usersingroup.jsp")
                .forward(request, response);


    }
}
