
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/login-hibernate" method="post">
    <center>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Вход
                </h2>
            </caption>
            <tr>
                <th>Почта:</th>
                <td>
                    <input type="email" name="email" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Пароль:</th>
                <td>
                    <input type="password" name="password" size="45"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Войти"/>
                </td>
            </tr>
        </table>
    </center>
</form>
</body>
</html>