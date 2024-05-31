<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.lab.util.Model" %>
<%@ page import="com.lab.util.Point" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Lab 2</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <div class="form_block">
        <a href="/">Вернуться</a>
    </div>
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

                if (values.size() > 0){
                    Point point = values.get(values.size()-1);
                    out.println("<tr>");
                    out.print("<td>" + String.valueOf(point.getX()) + "</td>");
                    out.print("<td>" + String.valueOf(point.getY()) + "</td>");
                    out.print("<td>" + String.valueOf(point.getR()) + "</td>");
                    out.print("<td>" + point.getTimeStamp().format(
                            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
                    ) + "</td>");
                    out.print("<td>" + String.valueOf(point.getWorkingTime()-) + "</td>");
                    String result = point.getStatus() ? "HIT": "MISS";
                    out.print("<td>" + result + "</td>");
                    out.println("</tr>");
                    out.println();
                }

            %>
            </tbody>
        </table>
    </div>
</body>
</html>
