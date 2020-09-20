package com.servlets;

import com.models.User;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Xml_Result extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        Xml_Converter xmlConverter = new Xml_Converter();
        
        try (ByteArrayOutputStream bos = xmlConverter.convertClient(user)) {
            response.setContentType("application/x-download");
            response.setHeader( "Content-Disposition", "filename=" + "result.xml" );
            OutputStream os = response.getOutputStream();
            bos.writeTo(os);
        }
      
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

   
}
