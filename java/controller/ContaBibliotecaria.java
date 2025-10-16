/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cfqr4byfeo8uoj4b
 */
@WebServlet(name = "ContaBibliotecaria", urlPatterns = {"/contaBibliotecaria"})
public class ContaBibliotecaria extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        
        String valorRecebido = request.getParameter("dado");

        // Simula uma validação. O dado é inválido se for nulo, vazio ou igual a "erro".
        if (valorRecebido == null || valorRecebido.isEmpty() || "erro".equalsIgnoreCase(valorRecebido)) {
            // Caso inválido: encaminha para a página de erro e lança uma exceção.
            request.setAttribute("mensagem", "Dados inválidos foram fornecidos.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Erro.jsp");
            dispatcher.forward(request, response);
            
            // Opcional: Lançar uma exceção pode ser útil para logs ou para um manipulador de exceções global.
            // throw new IllegalArgumentException("Dados de entrada inválidos");
            
        } else {
            // Caso válido: encaminha para a página de sucesso.
            request.setAttribute("mensagem", "Dados válidos recebidos: " + valorRecebido);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Sucesso.jsp");
            dispatcher.forward(request, response);
        }
        
        
        
        
        
        
        
        
        
    }

}
