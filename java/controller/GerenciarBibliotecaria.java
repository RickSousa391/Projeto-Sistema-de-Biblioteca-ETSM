package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotecaria;
import model.BibliotecariaDAO;


@WebServlet(name = "GerenciarBibliotecaria", urlPatterns = {"/gerenciarBibliotecaria"})
public class GerenciarBibliotecaria extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String acao = request.getParameter("acao");
            String idBibliotecaria = request.getParameter("idBibliotecaria");
            
      
            
            int chaveBibliotecaria = Integer.parseInt(idBibliotecaria);
            
            Bibliotecaria b = new Bibliotecaria();
            BibliotecariaDAO bdao = new BibliotecariaDAO();
            
            try{
                if(acao.equals("alterar")){
                    b = bdao.getBibliotecariaPorId(chaveBibliotecaria);
                    if(b.getIdBibliotecaria()!= 0){
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrarBibliotecaria.jsp");
                        request.setAttribute("bibliotecaria", b);
                        dispatcher.forward(request, response);
                    }else{
                        System.out.println("Esse Usuario Não Foi Encontrado");
                    }
                }
                
                }catch(SQLException e){
                String error_message = e.getMessage();
                System.out.println("Erro" + error_message);
                e.printStackTrace();
        }
            
           
            
            out.println(
            "<script type = 'text/javascript'>" +
            "alert('Ate mais');" +
            "location.href='login.jsp';"+
            "</script>"            
        );
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("Text/html");
        PrintWriter out = response.getWriter();
        String idBibliotecaria = request.getParameter("idBibliotecaria");
        String nome = request.getParameter("nome");
        String contato = request.getParameter("contato");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String registroFuncional = request.getParameter("registroFuncional");
        String mensagem = "";
        String redirecionamento = "login.jsp";
        
        Bibliotecaria b = new Bibliotecaria();
        
        if(idBibliotecaria != null && !idBibliotecaria.isEmpty()){
            try{
            b.setIdBibliotecaria(Integer.parseInt(idBibliotecaria));
        }catch(NumberFormatException e){
            System.out.println("ERRO: " + e.getMessage()); 
            e.printStackTrace();
        }
        }
        if(nome.isEmpty() || nome.equals("")){
            mensagem = "Informe o nome";
            redirecionamento = "cadastrarBibliotecaria.jsp";
            
        }else{
            b.setNome(nome);
        }
        
        if(contato.isEmpty() || contato.equals("")){
            mensagem = "Informe o contato";
            redirecionamento = "cadastrarBibliotecaria.jsp";
        }else{
            b.setContato(contato);
        }
        
        if(email.isEmpty() || email.equals("")){
            mensagem = "Informe o email";
            redirecionamento = "cadastrarBibliotecaria.jsp";
            
        }else{
            b.setEmail(email);
        }
        
        if(senha.isEmpty() || senha.equals("")){
            mensagem = "Informe a senha";
            redirecionamento = "cadastrarBibliotecaria.jsp";
            
        }else{
            b.setSenha(senha);
        }
        
        if(registroFuncional.isEmpty() || registroFuncional.equals("")){
            mensagem = "Informe o RegistroFuncional";
            redirecionamento = "cadastrarBibliotecaria.jsp";
            
            
        }else{
            b.setRegistroFuncional(registroFuncional);
        }
        
        BibliotecariaDAO bdao = new BibliotecariaDAO();
        
        try{
            if(bdao.gravar(b)){
                mensagem = "Nova Bibliotecaria Cadastrada";
                redirecionamento = "login.jsp";
            }else{
                mensagem = "Bibliotecaria Não Cadastrado";
            }
        }catch(SQLException e){
            System.out.println("ERRO: " + e.getMessage());
            e.printStackTrace();
        }
        
        out.println("<script type = 'text/javascript'>"
                + "alert('"+ mensagem +"');"
                +"location.href='"+ redirecionamento +"';"
                +"</script>"
        );
    }

}
