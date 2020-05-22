<%@ page import="servlets.AddUserHibernateServlet" %>
<%@ page import="service.UserHibernateService" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>

<%
    if (AddUserHibernateServlet.bool == false){
%>  <script>
    alert("Пользователь не был добавлен")
</script>
<%
        AddUserHibernateServlet.bool = true;
    }
%>
<form action="allUser-hibernate.jsp" method="GET">
    <center>
        <h1>Управление</h1>
        <h2>
            <a href="new-hibernate">Добавить пользователя</a> <br><br>
            <a href="index.jsp">На главную</a>
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
                List<User> list = UserHibernateService.getInstance().getAllUsers();
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
                        <a href="edit-hibernate?id=<c:out value='${user.id}' />">Изменить</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete-hibernate?id=<c:out value='${user.id}' />">Удалить</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</form>
</body>
</html>
