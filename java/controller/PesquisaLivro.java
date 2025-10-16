package controller;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import model.Livro;
import model.LivroDAO;





@WebServlet(name = "PesquisaLivro", urlPatterns = {"/pesquisaLivro"})
public class PesquisaLivro extends HttpServlet {
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String titulo = request.getParameter("titulo");
        String mensagem = "";
        String redirecionamento = "listadeLivro.jsp";
        
        

        Livro L = new Livro();
        
        
        if (!titulo.isBlank()){
            try {
                LivroDAO Ldao = new LivroDAO();
                L = Ldao.getPesquisa(titulo);
  
                if(L !=null){
                    if (titulo != null){
                        mensagem = " Usuario " + L.getTitulo()+ " Encontrado ";
                        redirecionamento = "listadeLivro.jsp";
                        
                    }
                    else {
                        mensagem = "Esse Usuario não Existe.";
                        redirecionamento = "cadastrarUsuario.jsp";
                    }
                }else{
                    mensagem = " Você ainda não ta cadastrada";
                    redirecionamento = "cadastrarUsuario.jsp";
                }
           
            }catch(SQLException e) {
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
