<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="service.UserService" %>
<%@ page import="servlets.AddUserServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title>Title</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
        boolean flag = true;
    %>
</head>
<body>
<%
    if (AddUserServlet.bool == false){
%>  <script>
        alert("Пользователь не был добавлен")
    </script>
<%
    AddUserServlet.bool = true;
    }
%>
<form action="/list" method="GET">
    <center>
        <h1>Управление</h1>
        <h2>
            <a href="new">Добавить пользователя</a>
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Пользователи</h2></caption>
            <tr>
                <th>ID</th>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Пароль</th>
                <th>Email</th>
                <th>Действие</th>
            </tr>
            <%
                if (flag) {
                    UserService userService = new UserService();
                    userService.createTable();
                    flag = false;
                }
                List<User> list = new UserService().getAllUsers();
                request.setAttribute("listUser", list);
            %>
            <c:forEach var="user" items="${listUser}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.surname}"/></td>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.password}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td>
                        <a href="edit?id=<c:out value='${user.id}' />">Изменить</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${user.id}' />">Удалить</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</form>
</body>
</html>
