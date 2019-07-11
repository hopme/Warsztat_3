package pl.coderslab.controller;

import pl.coderslab.dao.MainPageResultDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/")
public class MainPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int numberSolutions = Integer.parseInt(getServletContext().getInitParameter("number-solution"));

        MainPageResultDao mainPageResultDao = new MainPageResultDao();
        ArrayList mainPageResults = mainPageResultDao.findRecent(numberSolutions);
        request.setAttribute("mainPageResults", mainPageResults);
        getServletContext().getRequestDispatcher("/mainpage.jsp")
                .forward(request, response);




    }
}
