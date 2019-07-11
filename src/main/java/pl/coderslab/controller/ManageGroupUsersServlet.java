package pl.coderslab.controller;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/manageGroupUsers")
public class ManageGroupUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String newGroupName = request.getParameter("newGroupName");
        GroupDao groupDao = new GroupDao();
        try {
            int groupId = Integer.parseInt(request.getParameter("groupid"));
            Group group = new Group(groupId, newGroupName);
            groupDao.update(group);
        } catch (NumberFormatException e) {
            Group group = new Group(newGroupName);
            groupDao.create(group);
        }
        response.sendRedirect(request.getContextPath() + "/manageGroupUsers");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        GroupDao groupDao = new GroupDao();

        if (action == null) {

            ArrayList allGroups = groupDao.findAll();
            request.setAttribute("allGroups", allGroups);
            getServletContext().getRequestDispatcher("/managegroups.jsp")
                    .forward(request, response);

        } else if (action.equals("add")){

            getServletContext().getRequestDispatcher("/addgroup.jsp")
                    .forward(request, response);

        } else if (action.equals("edit")){
            int groupId = Integer.parseInt(request.getParameter("groupid"));
            Group currentGroup= groupDao.read(groupId);
            request.setAttribute("currentGroup", currentGroup);
            getServletContext().getRequestDispatcher("/editgroup.jsp")
                    .forward(request, response);

        } else if (action.equals("delete")){
            int groupId = Integer.parseInt(request.getParameter("groupid"));
            groupDao.delete(groupId);
            response.sendRedirect(request.getContextPath() + "/manageGroupUsers");

        }
    }
}
