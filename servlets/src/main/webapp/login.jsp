<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>
        Вход в систему
    </title>
    <style>
        * {
            font-size: 20px;
            text-align: center;
            margin: 10px;
        }

    </style>
</head>
<body>
<form method="post" action="home">
    Логин:<input type="text" name="login"/></br>
    Пароль:<input type="text" name="password"/></br>
    <input type="submit" value="Войти">
</form>
</body>
</html>