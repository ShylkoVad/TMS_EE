--------------------------------------------------------
--------------------------------------------------------
DROP SCHEMA IF EXISTS users_db;
CREATE SCHEMA IF NOT EXISTS users_db;

--------------------------------------------------------
--------------------------------------------------------
DROP TABLE IF EXISTS users_db.users;
CREATE TABLE IF NOT EXISTS users_db.users (
       login VARCHAR(20) NOT NULL,
       password VARCHAR(20) NOT NULL);


--------------------------------------------------------
--------------------------------------------------------
INSERT INTO users_db.users(login, password) VALUES('shilko_vad@mail.ru', '1234');
INSERT INTO users_db.users(login, password) VALUES('login@mail.ru', '0000');
INSERT INTO users_db.users(login, password) VALUES('user1@mail.ru', '1111');
