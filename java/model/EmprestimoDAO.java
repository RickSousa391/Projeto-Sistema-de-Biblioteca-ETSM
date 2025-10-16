
package model;

import factory.conexaofactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;



public class EmprestimoDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql="";
    
    public boolean gravar(Emprestimo em)throws SQLException{ 
        con = conexaofactory.conectar();
        boolean convertdr = true;
        String dr = ", dataDevolucaoReal";
        String values = "(?, ?, ?, ?, ?, ?)";
        
        if (em.getDataDevolucaoReal() == null) {
            convertdr = false;
            dr = "";
            values = "(?, ?, ?, ?, ?)";
        }
        
        if(em.getIdEmprestimo()==0){
            sql="INSERT INTO emprestimo(idUsuario, idBibliotecaria, idLivro, " + 
                "dataEmprestimo, dataDevolucaoPrevista" + dr + ")" +
                "VALUES" + values + ";";
            ps = con.prepareStatement(sql);
            ps.setInt(1, em.getUsuario().getIdUsuario());
            ps.setInt(2, em.getBibliotecaria().getIdBibliotecaria());
            ps.setInt(3, em.getLivro().getIdLivro());
            ps.setDate(4, new Date(em.getDataEmprestimo().getTime()));
            ps.setDate(5, new Date(em.getDataDevolucaoPrevista().getTime()));
            
            if (convertdr){
                ps.setDate(6, new Date(em.getDataDevolucaoReal().getTime()));
            }
        }else{
            
        }
        ps.executeUpdate();
        conexaofactory.close(con);
        return true;
    }
    
    public ArrayList<Emprestimo> getLista() throws SQLException{
        ArrayList<Emprestimo> emprestimos = new ArrayList<>();
        sql = "SELECT u.idUsuario, u.nome, " +
              "b.idBibliotecaria, b.nome, " +
              "L.idLivro, L.titulo, " +
              "em.idEmprestimo, em.idUsuario, em.idBibliotecaria, em.idLivro, " +
              "em.dataEmprestimo, em.dataDevolucaoPrevista, em.dataDevolucaoReal " +
              "FROM emprestimo em " +
              "INNER JOIN usuario u ON em.idUsuario = u.idUsuario " +
              "INNER JOIN bibliotecaria b ON em.idBibliotecaria = b.idBibliotecaria " +
              "INNER JOIN livro L ON em.idLivro = L.idLivro ";
        
        con = conexaofactory.conectar();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        while(rs.next()){
            Usuario u = new Usuario();
            
            u.setIdUsuario(rs.getInt("u.idUsuario"));
            u.setNome(rs.getString("u.nome"));
            
            Bibliotecaria b = new Bibliotecaria();
            
            b.setIdBibliotecaria(rs.getInt("b.idBibliotecaria"));
            b.setNome(rs.getString("b.nome"));
            
            
            Livro L = new Livro();
            
            L.setIdLivro(rs.getInt("L.idLivro"));
            L.setTitulo(rs.getString("L.titulo"));
            
            Emprestimo em = new Emprestimo();
            
            em.setIdEmprestimo(rs.getInt("em.idEmprestimo"));
            em.setDataEmprestimo(rs.getDate("em.dataEmprestimo"));
            em.setDataDevolucaoPrevista(rs.getDate("em.dataDevolucaoPrevista"));
            em.setDataDevolucaoReal(rs.getDate("em.dataDevolucaoReal"));
            
            em.setUsuario(u);
            em.setBibliotecaria(b);
            em.setLivro(L);
            
            emprestimos.add(em);
            
        }
        
        conexaofactory.close(con);
        
        return emprestimos;
        
    }
    
    
}
