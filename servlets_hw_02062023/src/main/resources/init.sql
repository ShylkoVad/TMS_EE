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
    id VARCHAR(60) NOT NULL,
    login VARCHAR(60) NOT NULL,
    password VARCHAR(60) NOT NULL,
    name VARCHAR(60) NOT NULL,
    surname VARCHAR(60) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id));

INSERT INTO shop.users(id, login, password, name, surname, balance) VALUES('acbb5fdc-1bca-457f-8b86-fde93e17649c','shilko_vad@mail.ru', '1234', 'Вадим', 'Шилько', 0.00);
INSERT INTO shop.users(id, login, password, name, surname, balance) VALUES('2f8a1d60-a3a7-4d20-aba6-b52004186fd9', 'login@mail.ru', '0000', 'Алексей', 'Иванов', 50.20);
INSERT INTO shop.users(id, login, password, name, surname, balance) VALUES('26daba6c-69a6-477d-9fac-4a5790636318', 'user1@mail.ru', '1111', 'Дмитрий', 'Козлов', 10.60);
--------------------------------------------------------
--  Table shop.categories
--------------------------------------------------------
DROP TABLE IF EXISTS shop.categories;
CREATE TABLE IF NOT EXISTS shop.categories (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    imagePath VARCHAR(100) NOT NULL,
    PRIMARY KEY (id));

INSERT INTO shop.categories(name, imagePath) VALUES('Платы Arduino', 'images/product/boardArduino.png');
INSERT INTO shop.categories(name, imagePath) VALUES('Наборы Arduino', 'images/product/kitArduino.png');
INSERT INTO shop.categories(name, imagePath) VALUES('Датчики Arduino', 'images/product/sensorArduino.png');
--------------------------------------------------------
--  Table shop.products
--------------------------------------------------------
DROP TABLE IF EXISTS shop.products;
CREATE TABLE IF NOT EXISTS shop.products (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    price DOUBLE NOT NULL,
    categoryId INT NOT NULL,
    imagePath VARCHAR(100) NOT NULL,
    PRIMARY KEY (id));

INSERT INTO shop.products(name, description, price, categoryId, imagePath) VALUES ('Uno R3',
                                                                                   'Контроллер Uno является самым подходящим вариантом для начала работы с платформой: она имеет удобный размер (не слишком большой, как у Mega и не такой маленький, как у Nano), достаточно доступна из-за массового выпуска всевозможных клонов, под нее написано огромное количество бесплатных уроков и скетчей.',
                                                                                   42.00, 1,
                                                                                   'images/category/arduino/arduino_Uno_-_R3.png');
INSERT INTO shop.products(name, description, price, categoryId, imagePath) VALUES('Mega 2560',
                                                                                  'Плата Arduino Mega 2560 предназначена для создания проектов, в которых не хватает возможностей обычных Arduino Uno. В этом устройстве максимальное из всех плат семейства Arduino количество пинов и расширенный набор интерфейсов.',
                                                                                  55.20, 1,
                                                                                  'images/category/arduino/arduino_Mega.png');

INSERT INTO shop.products(name, description, price, categoryId, imagePath) VALUES('Scratch+Arduino',
                                                                                  'Набор подготовлен по материалам популярной книги Дениса Голикова «Scratch и Arduino. 18 игровых проектов для юных программистов микроконтроллеров».',
                                                                                  350, 2,
                                                                                  'images/category/kitArduino/scratchArduino.png');
INSERT INTO shop.products(name, description, price, categoryId, imagePath) VALUES('Базовый набор "Arduino" 2.0',
                                                                                  'Если вы хотите не только изучить основы использования популярной микроконтроллерной платформы Arduino для разработки электронных проектов.',
                                                                                  340, 2,
                                                                                  'images/category/kitArduino/basicSetArduino2.0.png');

INSERT INTO shop.products(name, description, price, categoryId, imagePath) VALUES('Датчик контроля качества воздуха',
                                                                                  'Модуль Grove - Air quality sensor v1.3 представляет собой датчик, предназначенный для контроля качества воздуха в помещении.',
                                                                                  23, 3,
                                                                                  'images/category/sensorArduino/airQualitySensorV1.3.png');
INSERT INTO shop.products(name, description, price, categoryId, imagePath) VALUES('Датчик электричества на основе TA12-200',
                                                                                  'Модуль Grove - Electricity Sensor представляет собой датчика электричества на основе трансформатора тока ТА12-200, который может преобразовывать большой переменный ток в малую амплитуду.',
                                                                                  18, 3,
                                                                                  'images/category/sensorArduino/electricitySensor.png');