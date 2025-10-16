package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import model.UsuarioDAO;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;


@WebServlet(name = "GerenciarUsuario", urlPatterns = {"/gerenciarUsuario"})
public class GerenciarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String acao = request.getParameter("acao");
            String idUsuario = request.getParameter("idUsuario");
            String mensagem = "";
            
            int chaveUsuario = Integer.parseInt(idUsuario);
            
            Usuario u = new Usuario();
            UsuarioDAO usdao = new UsuarioDAO();
            
            try{
                if(acao.equals("alterar")){
                    u = usdao.getCarregarPorId(chaveUsuario);
                    if(u.getIdUsuario() != 0){
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cadastrarUsuario.jsp");
                        request.setAttribute("usuario", u);
                        dispatcher.forward(request, response);
                    }else{
                        mensagem = "Esse Usuario Não Foi Encontrado.";
                    }
                }else if(acao.equals("excluir")){
                     u.setIdUsuario(chaveUsuario);
                    boolean resultado = usdao.excluir(u);             
                    if(resultado){
                        mensagem = "Usuário deletado com sucesso"; 
                    }else{
                        mensagem = "Usuario não deletado";
                    }
                }
                
                
        
            }catch(SQLException e){
                String error_message = e.getMessage();
                System.out.println("Erro" + error_message);
                e.printStackTrace();
        }
            
        out.println(
            "<script type = 'text/javascript'>" +
            "alert('"+ mensagem +"');" +
            "location.href='listadeUsuario.jsp';"+
            "</script>"            
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String idUsuario = request.getParameter("idUsuario");
        String nome = request.getParameter("nome");
        String contato = request.getParameter("contato");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String mensagem = "";
        String local = "listadeUsuario.jsp";
        
        Usuario u = new Usuario();
        
        if(idUsuario != null  && !idUsuario.isEmpty()){
            try{
                u.setIdUsuario(Integer.parseInt(idUsuario));
            } catch(NumberFormatException e){
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
           }
        }
        

        if(nome.isEmpty() || nome.equals("")){
            mensagem = "Informe o nome";
        }else{
            u.setNome(nome);
        }
        
        if(contato.isEmpty() || contato.equals("")){
            mensagem = "Informe o contato";
        }else{
            u.setContato(contato);
        }
        
        if(email.isEmpty() || email.equals("")){
            mensagem = "Informe o email";
        }else{
            u.setEmail(email);
        }
        
        if(cpf.isEmpty() || cpf.equals("")){
            mensagem = "Informe o cpf"; 
        }else{
            u.setCpf(cpf);
        }
        
        UsuarioDAO udao = new UsuarioDAO();
        
        try{
            if(udao.gravar(u)){
                mensagem = "Usuario Cadastrado";
                local = "CadastrarEmprestimo.jsp";
            }else{
                mensagem = "Usuario Não Cadastrado";
                
            }
        }catch(SQLException e){
            mensagem = "Erro ao gravar usuário.";
            e.printStackTrace();
        }
        
        out.println(
                "<script type = 'text/javascript'>"
                +"alert('"+ mensagem +"');"
                +"location.href='" + local + "';"
                +"</script>"
        );
    }

}
