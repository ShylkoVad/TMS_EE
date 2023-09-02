<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/home.css">
    <link rel="stylesheet" type="text/css" href="../../css/user_account.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>


    <title>Личный кабинет</title>
</head>
<body>
<div class="container">


    <button class="tablink m-2" onclick="openTab('personalArea', this, 'white')" id="defaultOpen">Личный кабинет</button>
    <button class="tablink m-2" onclick="openTab('historyOrders', this, 'white')">История заказов</button>
    <button class="tablink m-2" onclick="openTab('updateUserDataImpl', this, 'white')">Обновление данных</button>


    <div id="personalArea" class="tabcontent text-start">
        <div class="border border-dark-subtle m-5 rounded-4">
            <div class="row">
                <div class="col-1 h5 m-2">Имя</div>
                <div class=" col-4 data_user h5 border border-dark-subtle m-2 rounded-2">
                    ${user.getName()}
                </div>
                <div class=" col-1 h5 m-2">Фамилия</div>
                <div class=" col-4 data_user h5 border border-dark-subtle m-2 rounded-2">
                    ${user.getSurname()}
                </div>
            </div>

            <div class="row">
                <div class="col-1 h5 m-2">Email</div>
                <div class=" col-4 data_user h5 border border-dark-subtle m-2 rounded-2">
                    ${user.getEmail()}
                </div>
            </div>

            <div class="row">
                <div class="col-2 h5 m-2">Дата рождения</div>
                <div class=" col-4 data_user h5 border border-dark-subtle m-2 rounded-2">
                    ${user.getBirthday()}
                </div>
            </div>

            <div class="row">
                <div class="col-1 h5 m-2">Баланс</div>
                <div class=" col-4 data_user h5 border border-dark-subtle m-2 rounded-2">
                    ${user.getBalance()} р.
                </div>
            </div>

        </div>
    </div>

    <div id="historyOrders" class="tabcontent">
        <div class="border border-dark-subtle m-5 rounded-4">
            <h2 class="m-2">История заказов:</h2>
            <div>
                <c:if test="${not empty orders}">
                    <c:forEach items="${orders}" var="order">
                        <div class=" row border border-dark-subtle m-1 rounded-4">
                            <div class="col-8 h5 m-2"> Заказ №${order.getId()} от ${order.getCreatedAt()}</div>
                            <div class="col-3 h5 m-2">${order.getPrice()} р.</div>
                            <div>${product.getName()}</div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>

    <div id="updateUserDataImpl" class="tabcontent">
        <div class="border border-dark-subtle m-5 rounded-4">
            <h2 class="m-2">Введите данные:</h2>
            <div class="activeOrders">
                <div class="h5 m-5">Данная страница в разработке</div>
            </div>
        </div>
    </div>

</div>


<script src="/script/user_account.js"></script>
</body>
</html>
