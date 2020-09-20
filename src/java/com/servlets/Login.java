package com.servlets;

import com.connection.UserSQL;
import com.connection.SQLite_library;
import com.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class Login extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // берем параметры формы входа: логин и пароль
        String login = request.getParameter("login");
        String pwd = request.getParameter("password");
        // выделяем сессию из запрлоса
        HttpSession session = request.getSession();
        //строитель строки ошибки
        StringBuilder errorBuilder = new StringBuilder();
        //сначала проверяем на null и пустую строку и собираем строку ошибки, которую передаем как атрибут
        if (login == null || pwd == null || login.isEmpty() || pwd.isEmpty()) {
            session.setAttribute("ec0", false);
            session.setAttribute("ec1", false);
            if (login == null || login.isEmpty()) {
                errorBuilder.append("<p>Поле ввода логина обязательно для заполнения</p>");
                session.setAttribute("ec0", true);
            }
            if (pwd == null || pwd.isEmpty()) {
                errorBuilder.append("<p>Поле ввода пароля обязательно для заполнения</p>");
                session.setAttribute("ec1", true);
            }
            //определяем строку ошибки как атрибут
            session.setAttribute("error", errorBuilder.toString());
            // перенаправляем на стартовую страницу, содержащую форму входа
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            // выделяем DAO объект для работы с клиентом
            UserSQL clientDAO = new SQLite_library();
            // если такой клиент существует в базе
            if (clientDAO.isClientExist(login, pwd)) {
                // создается объект класса пользователь и помещается в сессию как атрибут
                User user = new User();
                user.setLogin(login);
                user.setPassword(pwd);
                session.setAttribute("user", user);
                //сессия будет инактивирована через 30 минут
                session.setMaxInactiveInterval(30 * 60);
                // перенаправляем на result(сработает ResultServlet, котрый вернет result.html)
                response.sendRedirect(request.getContextPath() + "/result.html");

            } else {
                //добавляем ошибку
                errorBuilder.append("<p>Пользователь не найден</p>");
                //определяем строку ошибки как атрибут
                session.setAttribute("error", errorBuilder.toString());
                session.setAttribute("ec0", true);
                session.setAttribute("ec1", true);
                // перенаправляем на стартовую страницу, содержащую форму входа
                response.sendRedirect(request.getContextPath() + "/");
            }
        }


    }

}
