<%@ page import="com.lab.util.Model" %>
<%@ page import="com.lab.util.Point" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Lab 2</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css">
</head>

<body>

<div id="maincontent">

    <%-- Header --%>

    <div id="header">
        <h2>Кизилов Степан Александрович</h2>
        <h4>Группа P32312</h4>
        <h4>Вариант №6663122</h4>
    </div>

    <%-- Sidebar --%>

    <div id="sidebar">
        <div id="sidebar_content">
            <img src="static/img/areas.png" alt="plot">
        </div>
    </div>

    <div id="new_sidebar">
        <div id="new_sidebar_content">
            <canvas id="big_canvas" width="300px" height="300px"></canvas>
        </div>
    </div>

    <%-- Form --%>

    <div id="place_for_form">

        <form action="controller" method="get">

            <div class="form_wrapper">

                <%-- x coordinate --%>
                <div id="form_left" class="form_block">
                    <p>Введите X</p>
                    <div class="checkbox_wrapper">
                        <input type="checkbox" name="x_value" id="x_value_-3" value="-3">
                        <label for="x_value_-3">-3</label>
                    </div>
                    <div class="checkbox_wrapper">
                        <input type="checkbox" name="x_value" id="x_value_-2" value="-2">
                        <label for="x_value_-2">-2</label>
                    </div>
                    <div class="checkbox_wrapper">
                        <input type="checkbox" name="x_value" id="x_value_-1" value="-1">
                        <label for="x_value_-1">-1</label>
                    </div>
                    <div class="checkbox_wrapper">
                        <input type="checkbox" name="x_value" id="x_value_0" value="0">
                        <label for="x_value_0">0</label>
                    </div>
                    <div class="checkbox_wrapper">
                        <input type="checkbox" name="x_value" id="x_value_1" value="1">
                        <label for="x_value_1">1</label>
                    </div>
                    <div class="checkbox_wrapper">
                        <input type="checkbox" name="x_value" id="x_value_2" value="2">
                        <label for="x_value_2">2</label>
                    </div>
                    <div class="checkbox_wrapper">
                        <input type="checkbox" name="x_value" id="x_value_3" value="3">
                        <label for="x_value_3">3</label>
                    </div>
                    <div class="checkbox_wrapper">
                        <input type="checkbox" name="x_value" id="x_value_4" value="4">
                        <label for="x_value_4">4</label>
                    </div>
                    <div class="checkbox_wrapper">
                        <input type="checkbox" name="x_value" id="x_value_5" value="5">
                        <label for="x_value_5">5</label>
                    </div>
                </div>


                <%-- y coordinate --%>
                <div id="form_center" class="form_block">
                    <p>Введите Y</p>

                    <label for='y_value'></label>
                    <input type='text' name='y_value' id='y_value' />
                    <div id="y_message"></div>

                </div>

                <%-- radius --%>
                <div id="form_right" class="form_block">
                    <p>Введите R</p>
                    <div class="checkbox_wrapper">
                        <input type="checkbox" name="r_value" id="r_value_1" value="1">
                        <label for="r_value_1">1</label>
                    </div>
                    <div class="checkbox_wrapper">
                        <input type="checkbox" name="r_value" id="r_value_1.5" value="1.5">
                        <label for="r_value_1.5">1.5</label>
                    </div>
                    <div class="checkbox_wrapper">
                        <input type="checkbox" name="r_value" id="r_value_2" value="2">
                        <label for="r_value_2">2</label>
                    </div>
                    <div class="checkbox_wrapper">
                        <input type="checkbox" name="r_value" id="r_value_2.5" value="2.5">
                        <label for="r_value_2.5">2.5</label>
                    </div>
                    <div class="checkbox_wrapper">
                        <input type="checkbox" name="r_value" id="r_value_3" value="3">
                        <label for="r_value_3">3</label>
                    </div>
                </div>

                <div class="line_breaker"></div>

                <%-- Submit button --%>
                <input type="submit" name="btnSubmit" value="Отправить" />

            </div>

        </form>
    </div>

<%-- table --%>

    <div id="table_div">
        <table>
            <thead>
            <tr>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>Время запроса</th>
                <th>Время работы, мкс</th>
                <th>Результат</th>
            </tr>
            </thead>
            <tbody>
                <%
                    String userID = request.getSession(true).getId();
                    ServletContext sc = request.getServletContext();
                    Object supposedToBeModel = sc.getAttribute("values_" + userID);
                    Model model;
                    if (supposedToBeModel == null){
                        model = new Model();
                    } else {
                        model = (Model) supposedToBeModel;
                    }
                    ArrayList<Point> values = model.get();
                    for (int i = values.size() - 1; i >= 0; i--) {
                        Point point = values.get(i);
                        out.println("<tr>");
                        out.print("<td>" + String.valueOf(point.getX()) + "</td>");
                        out.print("<td>" + String.valueOf(point.getY()) + "</td>");
                        out.print("<td>" + String.valueOf(point.getR()) + "</td>");
                        out.print("<td>" + point.getTimeStamp().format(
                                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
                        ) + "</td>");
                        out.print("<td>" + String.valueOf(point.getWorkingTime()) + "</td>");
                        String result = point.getStatus() ? "HIT": "MISS";
                        out.print("<td>" + result + "</td>");
                        out.println("</tr>");
                        out.println();
                    }
                %>
            </tbody>
        </table>
    </div>

</div>

<%
    out.println("<script>");
    out.println("var items = [");
    for (int i = values.size() - 1; i >= 0; i--) {
        Point point = values.get(i);
        out.print("[");
        out.print(point.getX());
        out.print(",");
        out.print(point.getY());
        out.print(",");
        out.print(point.getR());
        out.println("],");
    }
    out.println("];");
    out.println("</script>");
%>

<script src="${pageContext.request.contextPath}/static/js/script.js"></script>

</body>

</html>