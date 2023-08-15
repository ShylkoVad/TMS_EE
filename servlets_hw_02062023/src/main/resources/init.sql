--------------------------------------------------------
--  DDL for schema shop
--------------------------------------------------------
DROP SCHEMA IF EXISTS shop;
CREATE SCHEMA IF NOT EXISTS shop;
--------------------------------------------------------
--  Table shop.users
--------------------------------------------------------
DROP TABLE IF EXISTS shop.users;
CREATE TABLE IF NOT EXISTS shop.users (
    id       INT            NOT NULL AUTO_INCREMENT,
    name     VARCHAR(45)    NOT NULL,
    surname  VARCHAR(60)    NOT NULL,
    birthday Timestamp      NOT NULL,
    balance  DECIMAL(10, 2) NOT NULL,
    email    VARCHAR(45)    NOT NULL,
    password VARCHAR(45)    NOT NULL,
    street   VARCHAR(45),
    accommodation_number VARCHAR(45),
    flat_number VARCHAR(45),
    phone_number VARCHAR(45),
    PRIMARY KEY (id),
    UNIQUE INDEX IDX_USERS_USER_ID_UNIQUE (id ASC),
    UNIQUE INDEX IDX_USERS_EMAIL_UNIQUE (email ASC),
    UNIQUE INDEX IDX_USERS_PASSWORD_UNIQUE (password ASC)
    );

INSERT INTO shop.users(name, surname, birthday, balance, email, password, street, accommodation_number, flat_number, phone_number)
VALUES ('Вадим', 'Шилько', '1984-09-08', 150.00, 'shilko_vad@mail.ru', '1234', '', '', '', '');
--------------------------------------------------------
--  Table shop.categories
--------------------------------------------------------
DROP TABLE IF EXISTS shop.categories;
CREATE TABLE IF NOT EXISTS shop.categories (
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX IDX_CATEGORIES_CATEGORY_ID_UNIQUE (id ASC),
    UNIQUE INDEX IDX_CATEGORIES_NAME_UNIQUE (name ASC)
    );

INSERT INTO shop.categories(name) VALUES('Платы Arduino');
INSERT INTO shop.categories(name) VALUES('Наборы Arduino');
INSERT INTO shop.categories(name) VALUES('Датчики Arduino');
INSERT INTO shop.categories(name) VALUES('Дисплеи и индикаторы');
INSERT INTO shop.categories(name) VALUES('Инструмент');
INSERT INTO shop.categories(name) VALUES('Аксессуары для умного дома');
INSERT INTO shop.categories(name) VALUES('Литература по программированию');
--------------------------------------------------------
--  Table shop.products
--------------------------------------------------------
DROP TABLE IF EXISTS shop.products;
CREATE TABLE IF NOT EXISTS shop.products (
    id          INT          NOT NULL AUTO_INCREMENT,
    name        VARCHAR(90)  NOT NULL,
    description VARCHAR(3000) NOT NULL,
    price       DOUBLE       NOT NULL,
    categoryId  INT          NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX IDX_PRODUCTS_ID_UNIQUE (id ASC),
    CONSTRAINT FK_PRODUCTS_CATEGORY_ID_CATEGORIES_ID
       FOREIGN KEY (categoryId)
          REFERENCES shop.categories (id)
          ON DELETE CASCADE
          ON UPDATE CASCADE
    );

INSERT INTO shop.products(name, description, price, categoryId)
VALUES ('Uno R3',
        'Контроллер Uno является самым подходящим вариантом для начала работы с платформой: она имеет удобный размер (не слишком большой, как у Mega и не такой маленький, как у Nano), достаточно доступна из-за массового выпуска всевозможных клонов, под нее написано огромное количество бесплатных уроков и скетчей.',
        42.00, 1);
INSERT INTO shop.products(name, description, price, categoryId)
VALUES('Mega 2560',
       'Плата Arduino Mega 2560 предназначена для создания проектов, в которых не хватает возможностей обычных Arduino Uno. В этом устройстве максимальное из всех плат семейства Arduino количество пинов и расширенный набор интерфейсов.',
       55.20, 1);
INSERT INTO shop.products(name, description, price, categoryId)
VALUES('Scratch+Arduino',
       'Набор подготовлен по материалам популярной книги Дениса Голикова «Scratch и Arduino. 18 игровых проектов для юных программистов микроконтроллеров».',
       350, 2);
INSERT INTO shop.products(name, description, price, categoryId)
VALUES('Базовый набор "Arduino" 2.0',
       'Если вы хотите не только изучить основы использования популярной микроконтроллерной платформы Arduino для разработки электронных проектов.',
       340, 2);
INSERT INTO shop.products(name, description, price, categoryId)
VALUES('Датчик контроля качества воздуха',
       'Модуль Grove - Air quality sensor v1.3 представляет собой датчик, предназначенный для контроля качества воздуха в помещении.',
       23, 3);
INSERT INTO shop.products(name, description, price, categoryId)
VALUES('Датчик электричества на основе TA12-200',
       'Модуль Grove - Electricity Sensor представляет собой датчика электричества на основе трансформатора тока ТА12-200, который может преобразовывать большой переменный ток в малую амплитуду.',
       18, 3);

--------------------------------------------------------
--  Table shop.orders
--------------------------------------------------------
DROP TABLE IF EXISTS shop.orders;
CREATE TABLE IF NOT EXISTS shop.orders (
    id         INT         NOT NULL AUTO_INCREMENT,
    userId     INT         NOT NULL,
    createdAt Timestamp   NOT NULL,
    price      DOUBLE      NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX IDX_ORDERS_ID_UNIQUE (id ASC),
    CONSTRAINT FK_ORDERS_USER_ID_USERS_ID
        FOREIGN KEY (userId)
            REFERENCES shop.users (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
    );
--------------------------------------------------------
--  Table shop.order_lists
--------------------------------------------------------
DROP TABLE IF EXISTS shop.order_lists;
CREATE TABLE IF NOT EXISTS shop.order_lists (
    orderId   INT NOT NULL,
    productId INT NOT NULL,
    quantity  INT NOT NULL DEFAULT 0,
    PRIMARY KEY (orderId, productId),
    CONSTRAINT FK_ORDERS_PRODUCTS_ORDER_ID_ORDERS_ID
        FOREIGN KEY (orderId)
            REFERENCES orders (id),
    CONSTRAINT FK_ORDERS_PRODUCTS_PRODUCT_ID_PRODUCTS_ID
        FOREIGN KEY (productId)
            REFERENCES products (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
    );
--------------------------------------------------------
--  Table shop.images
--------------------------------------------------------
DROP TABLE IF EXISTS shop.images;
CREATE TABLE IF NOT EXISTS shop.images
(
    id           INT          NOT NULL AUTO_INCREMENT,
    imagePath    VARCHAR(150) NOT NULL,
    categoryId   INT          NULL,
    productId    INT          NULL,
    primaryImage INT          NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX IDX_IMAGES_ID_UNIQUE (id ASC),
    CONSTRAINT FK_IMAGES_CATEGORIES_ID_CATEGORIES_ID
        FOREIGN KEY (categoryId)
            REFERENCES shop.categories (id),
    CONSTRAINT FK_IMAGES_PRODUCTS_ID_PRODUCTS_ID
        FOREIGN KEY (productId)
            REFERENCES shop.products (id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

INSERT INTO shop.images (imagePath, categoryId, productId, primaryImage)
VALUES ('images/product/boardArduino.png', 1, null, 1);
INSERT INTO shop.images (imagePath, categoryId, productId, primaryImage)
VALUES ('images/product/kitArduino.png', 2, null, 1);
INSERT INTO shop.images (imagePath, categoryId, productId, primaryImage)
VALUES ('images/product/sensorArduino.png', 3, null, 1);
INSERT INTO shop.images (imagePath, categoryId, productId, primaryImage)
VALUES ('', 4, null, 1);
INSERT INTO shop.images (imagePath, categoryId, productId, primaryImage)
VALUES ('', 5, null, 1);
INSERT INTO shop.images (imagePath, categoryId, productId, primaryImage)
VALUES ('', 6, null, 1);
INSERT INTO shop.images (imagePath, categoryId, productId, primaryImage)
VALUES ('', 7, null, 1);

INSERT INTO shop.images (imagePath, categoryId, productId, primaryImage)
VALUES ('images/category/arduino/arduino_Uno_-_R3.png', null, 1, 1);

INSERT INTO shop.images (imagePath, categoryId, productId, primaryImage)
VALUES ('images/category/arduino/arduino_Mega.png', null, 2, 1);

INSERT INTO shop.images (imagePath, categoryId, productId, primaryImage)
VALUES ('images/category/kitArduino/scratchArduino.png', null, 3, 1);

INSERT INTO shop.images (imagePath, categoryId, productId, primaryImage)
VALUES ('images/category/kitArduino/basicSetArduino2.0.png', null, 4, 1);

INSERT INTO shop.images (imagePath, categoryId, productId, primaryImage)
VALUES ('images/category/sensorArduino/airQualitySensorV1.3.png', null, 5, 1);

INSERT INTO shop.images (imagePath, categoryId, productId, primaryImage)
VALUES ('images/category/sensorArduino/electricitySensor.png', null, 6, 1);
