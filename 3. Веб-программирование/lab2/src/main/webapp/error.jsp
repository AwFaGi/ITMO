<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<p>
    An error occurred!
    <%= request.getAttribute("errorMessage")%>
</p>

<a href="index.jsp">Попробовать снова</a>

</body>
</html>
