<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../css/product.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>

    <title>${product.getName()}</title>
</head>
<body>

<div class="container-fluid">
    <c:if test="${not empty product}">
        <h2 class="py-4" style="text-align: center">${product.getName()}</h2>
        <div class="offset-1 mb-5">
            <div class="row g-0">
                <div class="col-2">

                    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel"
                         style="margin-top: 30px; margin-bottom: 30px">
                        <div class="carousel-inner">
                            <c:forEach items="${images}" var="image">
                                <c:if test="${image.getPrimaryImage() == 1}">
                                    <div class="carousel-item active">
                                        <img src="${image.getImagePath()}" class="d-block img-fluid rounded-start ml-3"
                                             style="max-height:500px; width: auto;">
                                    </div>
                                </c:if>
                                <c:if test="${image.getPrimaryImage() == 0}">
                                    <div class="carousel-item">
                                        <img src="${image.getImagePath()}" class="d-block img-fluid rounded-start ml-3"
                                             style="max-height:500px; width: auto;">
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls"
                                data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Предыдущий</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls"
                                data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Следующий</span>
                        </button>
                    </div>
                </div>
                <div class="col-md-10">
                    <div class="card-body">
                        <p class="card-text ml-4">${product.getDescription()}</p>
                        <p style="vertical-align: middle">
                            <span class="ml-4 priceProduct">${product.getPrice()} р.</span>
                            <input type="number" class="input ml-4 mr-2" name="quantityProduct" value="1" min="1"
                                   step="1"
                                   max="">шт.
                            <a href="/cart/add?product_id=${product.getId()}">
                                <button id="addProductToCart" type="button" class="btn btn-outline-dark ml-4"
                                        onclick="productAddedToShoppingCart()">Купить
                                </button>
                            </a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>
<script> function productAddedToShoppingCart() {
    window.confirm("Продукт успешно добавлен в корзину!");
}</script>
</body>
</html>
