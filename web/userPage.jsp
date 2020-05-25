
<%@ page import="servlets.LoginServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<form action="/logout" method="get">
    ID: <%=LoginServlet.user.getId()%><br><br>
    Фамилия: <%=LoginServlet.user.getSurname()%><br><br>
    Имя: <%=LoginServlet.user.getName()%><br><br>
    Пароль: <%=LoginServlet.user.getPassword()%><br><br>
    Email: <%=LoginServlet.user.getEmail()%><br><br>
    Роль: <%=LoginServlet.user.getRole()%><br><br>
    <input type="submit" value="Выйти">
</form>
</body>
</html>
