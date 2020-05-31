package com.servlets;

import com.com.dao.ClientDAO;
import com.com.dao.ClientDAOIml;
import com.models.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Client client = (Client) request.getSession().getAttribute("user");
        
        if (client != null) {
            client.setList(null);
            // выделяем DAO объект для работы с клиентом
            ClientDAO clientDAO = new ClientDAOIml();
            clientDAO.setupClient(client);
        //request.getSession().setAttribute("user",null);
        /*
            проверка на редирект в случае, если в сессии клиента нет: предыдущий комментарий
            раскоментировать, а следующее закоментировать
         */
          request.getSession().setAttribute("user", client);
        }
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
