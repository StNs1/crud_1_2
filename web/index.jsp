<%@ page import="dao.UserDaoFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    if (UserDaoFactory.getUserDao().equals("hibernate")) {
        response.sendRedirect("allUser-hibernate.jsp");
    } else if (UserDaoFactory.getUserDao().equals("jdbc")) {
        response.sendRedirect("allUser-jdbc.jsp");
    } else {
%> <script>
    alert("Неверный daotype")
</script>
<%
    }
%>
<%--
<form action="/" method="GET">
    <center>
        <h1>Управление</h1>
        <h2>
            <a href="allUser-jdbc.jsp">Использовать JDBC</a> <br><br>
            <a href="allUser-hibernate.jsp">Использовать Hibernate</a>
        </h2>
    </center>
</form> --%>
</body>
</html>
