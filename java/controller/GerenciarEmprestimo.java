package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotecaria;
import model.Emprestimo;
import model.EmprestimoDAO;
import model.Livro;
import model.QuatidadeLivro;
import model.Usuario;
import model.QuatidadeLivroDAO;

@WebServlet(name = "GerenciarEmprestimo", urlPatterns = {"/gerenciarEmprestimo"})
public class GerenciarEmprestimo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String idQuantidadeLivro = request.getParameter("idQuantidadeLivro");
        String idLivro = request.getParameter("idLivro");
        String quantidade = request.getParameter("quantidade");
        String mensagem = "";

        Livro li = new Livro();

        if (idLivro != null && !idLivro.isEmpty()) {
            li.setIdLivro(Integer.parseInt(idLivro));
        }

        if (idQuantidadeLivro.isBlank()) {

            int chaveQuantidade = Integer.parseInt(idQuantidadeLivro);

            QuatidadeLivroDAO qdao = new QuatidadeLivroDAO();
            try {    
                if (qdao.adicionar(li.getQuantidade())) {
                    
                    boolean disponivel = (qdao.getQuantidade(li.getQuantidade()) > 0);
                    
                    if (disponivel) {
                        mensagem = "Esse Livro ta " + idLivro + "Disponivel";
                        mensagem = "Exemplos dos disponivel " + disponivel;
                        mensagem = "Emprestimo feito com sucesso";
                    } else {
                        mensagem = "Esse Livro " + idLivro;
                        mensagem = "Exemplos disponivel ta 0";
                        mensagem = "Emprestimo não tá feito";
                    }
                }
            } catch (SQLException e) {
                mensagem = "Erro ao gravar usuário.";
                e.printStackTrace();
            }

            out.println(
                    "<script type = 'text/javascript'>"
                    + "alert('" + mensagem + "');"
                    + "location.href='listadeEmprestimo.jsp';"
                    + "</script>"
            );
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String idEmprestimo = request.getParameter("idEmprestimo");
        String idUsuario = request.getParameter("idUsuario");
        String idBibliotecaria = request.getParameter("idBibliotecaria");
        String idLivro = request.getParameter("idLivro");
        String dataEmprestimo = request.getParameter("dataEmprestimo");
        String dataDevolucaoPrevista = request.getParameter("dataDevolucaoPrevista");
        String dataDevolucaoReal = request.getParameter("dataDevolucaoReal");
        String mensagem = "";

        Emprestimo em = new Emprestimo();

        if (idEmprestimo != null && !idEmprestimo.isEmpty()) {
            try {
                em.setIdEmprestimo(Integer.parseInt(idEmprestimo));
            } catch (NumberFormatException e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }

        }

        Usuario u = new Usuario();

        if (idUsuario != null && !idUsuario.isEmpty()) {
            u.setIdUsuario(Integer.parseInt(idUsuario));

            em.setUsuario(u);
        }

        Bibliotecaria bi = new Bibliotecaria();

        if (idBibliotecaria != null && !idBibliotecaria.isEmpty()) {
            bi.setIdBibliotecaria(Integer.parseInt(idBibliotecaria));

            em.setBibliotecaria(bi);

        }

        Livro li = new Livro();

        if (idLivro != null && !idLivro.isEmpty()) {
            li.setIdLivro(Integer.parseInt(idLivro));

            em.setLivro(li);
        }

        if (dataEmprestimo.isEmpty() || dataEmprestimo.equals("")) {
            mensagem = "Informe a Data De Cadastro";
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                em.setDataEmprestimo(df.parse(dataEmprestimo));
            } catch (ParseException e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }

        }

        if (dataDevolucaoPrevista.isEmpty() || dataDevolucaoPrevista.equals("")) {
            mensagem = "Informe a Data De Cadastro";
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                em.setDataDevolucaoPrevista(df.parse(dataDevolucaoPrevista));
            } catch (ParseException e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }

        }

        if (dataDevolucaoReal == null || dataDevolucaoReal.isEmpty() || dataDevolucaoReal.equals("")) {
            mensagem = "Informe a Data De Cadastro";
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                em.setDataDevolucaoReal(df.parse(dataDevolucaoReal));
            } catch (ParseException e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }

        }

        QuatidadeLivroDAO qdao = new QuatidadeLivroDAO();
        EmprestimoDAO emdao = new EmprestimoDAO();

        try {
            if (emdao.gravar(em)) {
                if (qdao.diminur(li.getIdLivro())) {
                    mensagem = "Emprestimo Feito com sucesso";
                }
            } else {
                mensagem = "Emprestimo Não Feito";
            }
        } catch (SQLException e) {
            mensagem = "Erro ao gravar usuário.";
            e.printStackTrace();
        }

        out.println(
                "<script type = 'text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='listadeEmprestimo.jsp';"
                + "</script>"
        );

    }

}
