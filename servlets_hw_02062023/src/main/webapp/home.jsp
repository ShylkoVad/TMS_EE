<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/home.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>

    <title>Категории</title>


</head>
<body>

<div>
    <nav class=" navbar navbar-expand navbar-light p-4">
        <div class="container">
            <a class="navbar-brand" href="#">
                <strong class="fs-2">ArduinoShop</strong></a>

            <ul class="navbar-nav ms-auto ">
                <li class="nav-item">
                    <a class="nav-link mx-4 text-uppercase" href="#">
                        <img src="images/icons/shopping-cart.png"
                             class="me-2">
                        Корзина</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link mx-4 text-uppercase" href="#">
                        <img src="images/icons/user.png"
                             class="me-2">
                        Аккаунт</a>
                </li>
            </ul>
        </div>
    </nav>
</div>


<div class="border bg-secondary-subtle">
    <nav class="navbar-expand sticky-top navbar-light  p-3 shadow-sm">
        <div class="container">
            <div class=" collapse navbar-collapse " id="navbarNavDropdown">
                <ul class="navbar-nav mx-auto">
                    <li class="nav-item">
                        <a class="nav-link mx-5 text-uppercase active" href="login">Главная</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link mx-5 text-uppercase" href="login">Каталог</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link mx-5 text-uppercase" href="#">О магазине</a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Введите запрос" aria-label="Search">
                    <button class="btn btn-outline-dark my-2 my-sm-0" type="submit">Поиск</button>
                </form>
            </div>
        </div>
    </nav>
</div>

<div>
    <h2 class="fs-3; text-center">Каталог</h2>
    <div class="container-fluid">
        <div class="d-flex justify-content-center">
            <c:forEach items="${categories}" var="category">
                <div class="card w-25 m-2 text-center" type="category">
                    <a id="${category.getId()}"
                       href='<c:url value="/category?id=${category.getId()}&name=${category.getName()}"  />'>
                        <div class="card-body">
                            <img class="card-img" style="max-height:150px; max-width:150px;"
                                 src="${category.getImagePath()}"
                                 alt="Card image">
                            <h5 class="card-title; text-black">${category.getName()}</h5>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>