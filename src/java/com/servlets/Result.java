package com.servlets;

import com.connection.UserSQL;
import com.connection.SQLite_library;
import com.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Result extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User user = (User) request.getSession().getAttribute("user");
        
        if (user != null) {
            user.setList(null);
            // выделяем DAO объект для работы с клиентом
            UserSQL clientDAO = new SQLite_library();
            clientDAO.setupClient(user);
        //request.getSession().setAttribute("user",null);
        /*
            проверка на редирект в случае, если в сессии клиента нет: предыдущий комментарий
            раскоментировать, а следующее закоментировать
         */
          request.getSession().setAttribute("user", user);
        }
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
