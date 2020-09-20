package com.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class Logout extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		response.setContentType("text/html");
		
		//если существует сессия, она будет разрушена
		HttpSession session = request.getSession(false);
		if(session != null){
			session.invalidate();
		}
        // пользователь перенаправляется на страницу входа
		response.sendRedirect(request.getContextPath()+"/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
