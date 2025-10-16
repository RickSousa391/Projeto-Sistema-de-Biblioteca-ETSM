/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Livro;
import model.LivroDAO;

/**
 *
 * @author cfqr4byfeo8uoj4b
 */
@WebServlet(name = "GerenciarLivro", urlPatterns = {"/gerenciarLivro"})
public class GerenciarLivro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String acao = request.getParameter("acao");
        String idLivro = request.getParameter("idLivro");
        String mensagem = "";
        String Local = "cadastrarLivro.jsp";

        Livro L = new Livro();
        LivroDAO Ldao = new LivroDAO();
        
        
        

        try {
            if (acao.equals("listar")) {
                ArrayList<Livro> livros = new ArrayList<>();
                livros = Ldao.getLista();
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listadeLivro.jsp");
                request.setAttribute("livro", livros);
                dispatcher.forward(request, response);
            }else if (acao.equals("disponivel")) {
                L.setIdLivro(Integer.parseInt(idLivro));
                if (Ldao.disponivel(L)) {
                    mensagem = "Livro saiu da barca com sucesso!";
                    Local = "cosultarUsuario.jsp";
                    
                } else {
                    mensagem = "Ocorreu erro no sistema";
                }
            } else if (acao.equals("indisponivel")) {
                L.setIdLivro(Integer.parseInt(idLivro));
                if (Ldao.indisponivel(L)){
                    mensagem = "Livro volta ta disponivel com sucesso!";
                    Local = "listadeLivro.jsp";
                } else {
                    response.sendRedirect("listadeLivro.jsp");
                }
            }

        } catch (SQLException e) {
            String error_message = e.getMessage();
            System.out.println("Erro" + error_message);
            e.printStackTrace();
        }

        out.println(
                "<script type = 'text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='" + Local + "';"
                + "</script>"
        );

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String idLivro = request.getParameter("idLivro");
        String titulo = request.getParameter("titulo");
        String editoria = request.getParameter("editoria");
        String autor = request.getParameter("autor");
        String anoPublicacao = request.getParameter("anoPublicacao");
        String status = request.getParameter("status");
        String mensagem = "";
        String redimentom = "Livro.jsp";

        Livro L = new Livro();

        if (idLivro != null && !idLivro.isEmpty()) {
            L.setIdLivro(Integer.parseInt(idLivro));
        }

        if (titulo.isEmpty() || titulo.equals("")) {
            mensagem = "Informe o Titulo!";
            redimentom = "cadastrarLivro.jsp";
        } else {
            L.setTitulo(titulo);
        }

        if (editoria.isEmpty() || editoria.equals("")) {
            mensagem = "Informe o editoria!";
            redimentom = "cadastrarLivro.jsp";
        } else {
            L.setEditoria(editoria);
        }

        if (autor.isEmpty() || autor.equals("")) {
            mensagem = "Informe o autor!";
            redimentom = "cadastrarLivro.jsp";
        } else {
            L.setAutor(autor);
        }

        if (anoPublicacao.isEmpty() || anoPublicacao.equals("")) {
            mensagem = "Informe a Data De Cadastro";
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            try {
                L.setAnoPublicacao(df.parse(anoPublicacao));
            } catch (ParseException e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }

        }

        if (status != null && !status.isEmpty()) {
            L.setStatus(Integer.parseInt(status));
        } else {
            mensagem = "Informe se ta disponivel ou indisponivel";
        }

        LivroDAO ldao = new LivroDAO();

        try {
            if (ldao.gravar(L)) {
                mensagem = "Livro agora existir";
                redimentom = "listadeLivro.jsp";
            } else {
                mensagem = "Livro Não Existir";
            }
        } catch (SQLException e) {
            mensagem = "Erro de leitura livro.";
            redimentom = "cadastrarLivro.jsp";
            e.printStackTrace();
        }

        out.println(
                "<script type = 'text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='cadastrarLivro.jsp';"
                + "</script>"
        );

    }

}
