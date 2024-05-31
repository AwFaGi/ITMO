package com.lab.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Deprecated
@WebServlet(name = "ErrorServlet", value = "/error/*")
public class ErrorServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<!DOCTYPE html>");
        printWriter.println("<html>");
        printWriter.println("<script>");

        String additionalValue = request.getPathInfo();
        if (additionalValue == null){
            printWriter.println("alert('418. I am a teapot!');");
        } else {
            switch (additionalValue){
                case "/1":
                    printWriter.println("alert('418. You are a teapot!');");
                    break;
                case "/2":
                    printWriter.println("alert('-100500. You are a valenok!');");
                    break;
                default:
                    printWriter.println("alert('Congratulations: server uronen!');");
                    break;
            }
        }

        printWriter.println("window.location.href = '/'");
        printWriter.println("</script>");
        printWriter.println("</html>");
    }


//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String additionalValue = request.getPathInfo();
//        ServletContext context = request.getSession(true).getServletContext();
//
//        Object smth = context.getAttribute("values");
//        Model model;
//        if (smth == null){
//            model = new Model();
//            context.setAttribute("values", model);
//        } else {
//            model = (Model) context.getAttribute("values");
//        }
//        PrintWriter writer = response.getWriter();
//        if (additionalValue == null){
//
//            writer.println(String.join(", ", model.get()));
//        } else {
//            model.add(additionalValue);
//        }
//        writer.println(request.getSession(true).getId());
//
//    }

}
