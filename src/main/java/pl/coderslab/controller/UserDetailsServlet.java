package pl.coderslab.controller;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.Group;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserDetailsServlet")
public class UserDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userid"));
        int groupId = Integer.parseInt(request.getParameter("groupid"));


        GroupDao groupDao = new GroupDao();
        Group group = groupDao.read(groupId);

        UserDao userDao = new UserDao();
        User user = userDao.read(userId);
        request.setAttribute("user", user);

        
        getServletContext().getRequestDispatcher("/currentuser.jsp")
                .forward(request, response);


    }
}
