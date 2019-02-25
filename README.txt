Технологии: Java 8, Spring 5 (Boot 2, JPA, Security) MySQL(or PostgreSQL) Все запросы по REST API.Проект должен содержать следующие методы:

Создание пользовательского акаунта.
Аутентификацию для зарегистрированных пользователей используя JWT.
Постраничный вывод списка пользователей(только для аутентифицированных пользователей).
Редактирование и Удаление любого пользователя(только для аутентифицированных пользователей).
Каждый аккаунт имеет кошелек в определенной (одной) валюте. Реализовать возможность пополнения баланса кошелька, перевода платежа между аккаунтами с с проверкой на валидность данных
How to Run Application

Open application.properties file inside task/src/main/resources/ directory.

Fill in params in accordance to you local DB, e.g:

datasource:
    url: jdbc:mysql://<your-mysql-host-name>:3306/<your-db-name>
    username: root
    password: admin
Run TestOauthApplication.java class


Main controllers:

1.
Получаем токен, с которым ходим на каждый контроллер
http://localhost:8080/oauth/token?grant_type=password&username=dimon&password=password

2.
Перевод денег с одного кошелька на другой
http://<localhost>:<port>/api/<idFrom>/replenish/<idTo>?amount=20.0

3.
Пополнение кошелька
http://<localhost>:<port>/api/<id>/amount/add?amount=22.3

4.
Снятие денег с кошелька
http://<localhost>:<port>/api/<id>/amount/reduce?amount=22.3

