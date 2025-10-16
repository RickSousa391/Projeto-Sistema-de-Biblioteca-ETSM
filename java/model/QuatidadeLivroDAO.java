package model;

import factory.conexaofactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class QuatidadeLivroDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql="";
    
   public boolean gravar(int idLivro, int quantidade) throws SQLException{
       con = conexaofactory.conectar();
       
        
        sql = "INSERT INTO quantidadelivros(idLivro, quantidade)"
                +"VALUES (?, ?)";
            
        ps = con.prepareStatement(sql);
        ps.setInt(1, idLivro);
        ps.setInt(2, quantidade);
 
        ps.executeUpdate();
        conexaofactory.close(con);
        return true;   
       
   }
   
   public boolean atualizar(int idLivro, int quantidade) throws SQLException{
       con = conexaofactory.conectar();
       sql = "UPDATE quantidadelivros SET quantdade = ? WHERE idLivro = ?";
       
       ps = con.prepareStatement(sql);
       ps.setInt(1, quantidade);
       ps.setInt(2, idLivro);
       
       int resultado = ps.executeUpdate();
       return resultado > 0 ;
       
   }
    
    
    public boolean adicionar(int Q) throws SQLException{
        
        int c = getQuantidade(Q);
        int qn = c++;
        
        
        con = conexaofactory.conectar();
            sql = "UPDATE quantidadelivros SET quantidade = ?" 
                    + " WHERE idQuantidadeLivro = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, qn);
            ps.setInt(2, Q);
        
        
        ps.executeUpdate();
        conexaofactory.close(con);
        return true;
        
        
    }
    
    public boolean diminur(int idLivro) throws SQLException{
        
        int c = getQuantidade(idLivro);
        int qn = c--;
        
        
        con = conexaofactory.conectar();
            sql = "UPDATE quantidadelivros SET quantidade = ?" 
                    + " WHERE idQuantidadeLivro = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, qn);
            ps.setInt(2, idLivro);
        
        
        ps.executeUpdate();
        conexaofactory.close(con);
        return true;
        
        
    }
    
    
    
    public int getQuantidade(int idLivro) throws SQLException{
        con = conexaofactory.conectar();
        sql = "SELECT quantidade FROM quantidadelivros WHERE idLivro = ?";
        ps = con.prepareStatement(sql);
        ps.setInt(1, idLivro);
        rs = ps.executeQuery();
        
        int num = 0;
        
        if (rs.next()){
            num = rs.getInt("quantidade");
        }
        
        conexaofactory.close(con);
        
        return num;
    }
    
    public ArrayList<QuatidadeLivro> getLista() throws SQLException {
        ArrayList<QuatidadeLivro> QuatidadeLivros = new ArrayList();
        sql = "SELECT L.idLivro, L.titulo, " + 
              "q.idQuantidadeLivro, q.idLivro, q.quantidade FROM quantidadelivro q "+
              "INNER JOIN livro L ON q.idLivro = L.idLivro ";
        con = conexaofactory.conectar();
        ps = con.prepareStatement(sql);

        rs = ps.executeQuery();

        while (rs.next()) {
            
            Livro L = new Livro();
            
            L.setIdLivro(rs.getInt("L.idLivro"));
            L.setTitulo(rs.getString("titulo"));
            
            QuatidadeLivro QL = new QuatidadeLivro();
            
            QL.setIdQuantidadeLivro(rs.getInt("idQuantidadeLivro"));
            QL.setQuantidade(rs.getInt("Quantidade"));
            
            QL.setLivro(L);
            QuatidadeLivros.add(QL);
            
        }
        
        conexaofactory.close(con);
        return QuatidadeLivros;
    }
    
}
