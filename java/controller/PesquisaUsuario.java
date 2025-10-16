package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import model.Usuario;
import model.UsuarioDAO;

@WebServlet(name = "PesquisaUsuario", urlPatterns = {"/pesquisaUsuario"})
public class PesquisaUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String mensagem = "";
        String redirecionamento = "listadeUsuario.jsp";

        Usuario u = new Usuario();
        
        if (!nome.isBlank() && !cpf.isBlank()){
            try {
                UsuarioDAO udao = new UsuarioDAO();
                u = udao.getUsuarioPorCPF(nome, cpf);
  
                if(u != null){
                    if (u.getNome() != null && u.getCpf() != null){
                        mensagem = " Usuario " + u.getNome() + " CPF " + u.getCpf() + " Encontrado ";
                        redirecionamento = "CadastrarEmprestimo.jsp";
                        
                    }
                    else {
                        mensagem = "Esse Usuario não Existe.";
                        redirecionamento = "cadastrarUsuario.jsp";
                    }
                }else{
                    mensagem = " Você ainda não ta cadastrada";
                    redirecionamento = "cadastrarUsuario.jsp";
                }
           
            }catch (SQLException e) {
                mensagem = "Erro dos dados";
                e.printStackTrace();

            }
        }
        
        else {
            mensagem = "Por favor, digite os campos requisitados";
        }
        
        
        out.println("<script type = 'text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='" + redirecionamento + "';"
                + "</script>"
        );

    }
}
