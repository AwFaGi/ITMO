package com.lab.servlet;

import com.lab.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AreaCheckServlet", value = "/area-check")
public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long startTime = System.nanoTime();
        String xString = request.getParameter("x_value");
        String yString = request.getParameter("y_value");
        String rString = request.getParameter("r_value");

        /* validate sensibility */
        if (
                !DataValidator.validateDouble(xString) ||
                !DataValidator.validateDouble(yString) ||
                !DataValidator.validateDouble(rString)
        ){
            request.setAttribute("errorMessage", "-100500. You are a valenok!");
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }

        /* create point object */
        Point point = new Point(
                Double.parseDouble(xString),
                Double.parseDouble(yString),
                Double.parseDouble(rString)
        );

        /* validate values */
        if (!PointValidator.validate(point)) {
            request.setAttribute("errorMessage", "-100500. You are a valenok!");
            getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
        }

        /* validate area hit */
        boolean gotInto = AreaInValidator.validate(point);
        point.setStatus(gotInto);

        /* save it into context */
        String userID = request.getSession(true).getId();
        String getString = "values_" + userID;
        Object supposedToBeModel = getServletContext().getAttribute(getString);
        Model model;
        if (supposedToBeModel == null){
            model = new Model();
        } else {
            model = (Model) supposedToBeModel;
        }
        point.setWorkingTime((System.nanoTime() - startTime) / 1000);
        model.add(point);
        getServletContext().setAttribute(getString, model);

        /* show result html page */
        response.sendRedirect("/result.jsp");
    }
}
