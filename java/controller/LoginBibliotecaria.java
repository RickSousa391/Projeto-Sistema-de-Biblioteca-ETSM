package controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotecaria;
import model.BibliotecariaDAO;

@WebServlet(name = "LoginBibliotecaria", urlPatterns = {"/loginBibliotecaria"})
public class LoginBibliotecaria extends HttpServlet {

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
        String senha = request.getParameter("senha");
        String mensagem = "";
        String redirecionamento = "login.jsp";

        Bibliotecaria bi = new Bibliotecaria();
        
       
        if (!nome.isBlank() && !senha.isBlank()){
            try {
                Class.forName("com.fasterxml.jackson.core.JsonProcessingException");
                System.out.println("Jackson está disponível!");
    
                BibliotecariaDAO bdao = new BibliotecariaDAO();
                bi = bdao.getBibliotecariaPorSN(nome, senha);
  
                if(bi != null){
                    if (bi.getNome() != null && bi.getSenha() != null){
                        mensagem = " Olá Bem-Vindos " + bi.getNome() + " Senha Confimarda " + bi.getSenha();
                        
                            String secret = "minha-chave-secreta";
                            Algorithm algorithm2 = Algorithm.HMAC512(secret);
                            long nowMillis = System.currentTimeMillis();

                            // Expiração em 1 hora (60 * 60 * 1000 milissegundos)
                            long expMillis = nowMillis + (60 * 60 * 1000);

                            Date now = new Date(nowMillis);
                            Date exp = new Date(expMillis);

                            // Criação do token
                            String token = JWT.create()
                                .withIssuedAt(now)
                                .withExpiresAt(exp)
                                .withClaim("id", bi.getIdBibliotecaria())
                                .sign(algorithm2);

                        Cookie cookie = new Cookie("auth_token", token);
                        cookie.setMaxAge(60*60); 
                        response.addCookie(cookie);
                        
                        redirecionamento = "index2.jsp";
                    }
                    else {
                        mensagem = " Você ainda não ta cadastrada.";
                    }
                }else{
                    mensagem = " Você ainda não ta cadastrada";   
                }
           
            }catch (SQLException e) {
                mensagem = "Erro dos dados";
                e.printStackTrace();
            }
            
            catch(ClassNotFoundException er){
                System.out.println(er.getMessage());
                er.printStackTrace();
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

