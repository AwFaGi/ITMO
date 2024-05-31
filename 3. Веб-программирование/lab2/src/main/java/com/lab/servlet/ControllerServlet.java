package com.lab.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControllerServlet", value = "/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        /* validate presence*/
        String xString = request.getParameter("x_value");
        String yString = request.getParameter("y_value");
        String rString = request.getParameter("r_value");

        if (xString == null || yString == null || rString == null){
            request.setAttribute("errorMessage", "418. You are a teapot!");
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }

        getServletContext().getRequestDispatcher("/area-check").forward(request, response);

    }

}