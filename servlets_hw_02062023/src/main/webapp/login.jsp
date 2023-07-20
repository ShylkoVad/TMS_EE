<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Магазин</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3 offset-4">
            <h2>Вход в магазин</h2>
            <form method="post" action="login" class="needs-validation" novalidate>
                <div class="form-group">
                    <label for="email">Имя пользователя:</label>
                    <input type="text" class="form-control" id="email" placeholder="Введите email" name="email"
                           required>
                    <div class="invalid-feedback">Имя пользователя должно быть введено!</div>
                </div>
                <div class="form-group">
                    <label for="password">Пароль:</label>
                    <input type="password" class="form-control" id="password" placeholder="Введите пароль"
                           name="password"
                           required>
                    <div class="invalid-feedback">Пароль должен быть введен!</div>
                </div>
                <button id="loginBtn" type="submit" class="btn btn-primary">Войти</button>
            </form>
        </div>
    </div>
</div>
<script src="script/script.js"></script>
</body>
</html>
