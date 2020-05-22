<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/" method="GET">
    <center>
        <h1>Управление</h1>
        <h2>
            <a href="allUser-jdbc.jsp">Использовать JDBC</a> <br><br>
            <a href="allUser-hibernate.jsp">Использовать Hibernate</a>
        </h2>
    </center>
</form>
</body>
</html>
