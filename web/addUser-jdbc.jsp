<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title>Title</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<center>
    <h1>Управление</h1>
    <h2>
        <a href="allUser-jdbc.jsp">Список всех пользователей</a><br><br>
        <a href="index.jsp">На главную</a>
    </h2>
</center>
<div align="center">
    <c:if test="${user != null}">
    <form action="edit-jdbc" method="post">
        </c:if>
        <c:if test="${user == null}">
        <form action="new-jdbc" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${user != null}">
                            Изменить
                        </c:if>
                        <c:if test="${user == null}">
                            Добавить пользователя
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${user != null}">
                    <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
                </c:if>
                <tr>
                    <th>Фамилия:</th>
                    <td>
                        <input type="text" name="surname" size="45"
                               value="<c:out value='${user.surname}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Имя:</th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${user.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Пароль:</th>
                    <td>
                        <input type="password" name="password" size="45"
                               value="<c:out value='${user.password}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Почта:</th>
                    <td>
                        <input type="email" name="email" size="45"
                               value="<c:out value='${user.email}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save"/>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
