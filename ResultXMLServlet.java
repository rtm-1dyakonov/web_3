package com.servlets;

import com.converters.ClientXMLConverter;
import com.models.Client;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ResultXMLServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Client client = (Client)request.getSession().getAttribute("user");
        ClientXMLConverter xmlConverter = new ClientXMLConverter();
        
        try (ByteArrayOutputStream bos = xmlConverter.convertClient(client)) {
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
