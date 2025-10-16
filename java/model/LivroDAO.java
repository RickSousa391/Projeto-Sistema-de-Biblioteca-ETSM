
package model;

import java.sql.Statement;
import factory.conexaofactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;


public class LivroDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql="";
    
    public boolean gravar(Livro L) throws SQLException{
        con = conexaofactory.conectar();
        if(L.getIdLivro() == 0){
            sql="INSERT INTO livro(titulo,editoria,autor,anoPublicacao,status) "+
                "VALUES(?, ?, ?, ?, ?)";
            
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, L.getTitulo());
            ps.setString(2, L.getEditoria());
            ps.setString(3,L.getAutor());
            ps.setDate(4, new Date(L.getAnoPublicacao().getTime()));
            ps.setInt(5,L.getStatus());
        }else{
            
        }
        int linhasAfetadas = ps.executeUpdate();
        QuatidadeLivroDAO qdao = new QuatidadeLivroDAO();
        
        if (linhasAfetadas > 0){
            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    int gId = (int) rs.getLong(1);
                    qdao.gravar(gId, 9);
                }
            }
        }
        
        conexaofactory.close(con);
        return true; 
    }
    
    public boolean indisponivel(Livro L) throws SQLException{
        sql = "UPDATE livro SET status = 0 "
              +"WHERE idLivro = ? ";
        con = conexaofactory.conectar();
        ps = con.prepareStatement(sql);
        
        ps.setInt(1, L.getIdLivro());
        
        ps.executeUpdate();
        conexaofactory.close(con);
        return true;
    }
    
    public boolean disponivel(Livro L) throws SQLException{
        sql = "UPDATE livro SET status = 1 "
              +"WHERE idLivro = ? ";
        con = conexaofactory.conectar();
        ps = con.prepareStatement(sql);
        
        ps.setInt(1, L.getIdLivro());
        
        ps.executeUpdate();
        conexaofactory.close(con);
        return true;
    }
    
    
    public ArrayList<Livro> getLista() throws SQLException {
        ArrayList<Livro> livros = new ArrayList();
        sql = "SELECT L.idLivro, L.titulo, L.editoria, L.autor, L.anoPublicacao, L.status, Q.quantidade FROM livro L " 
              + "INNER JOIN quantidadelivros Q ON Q.idLivro = L.idLivro ";
        con = conexaofactory.conectar();
        ps = con.prepareStatement(sql);

        rs = ps.executeQuery();

        while (rs.next()) {
            Livro L = new Livro();
            L.setIdLivro(rs.getInt("L.idLivro"));
            L.setTitulo(rs.getString("L.titulo"));
            L.setEditoria(rs.getString("L.editoria"));
            L.setAutor(rs.getString("L.autor"));
            L.setAnoPublicacao(rs.getDate("L.anoPublicacao"));
            L.setStatus(rs.getInt("L.status"));
            L.setQuantidade(rs.getInt("Q.quantidade"));
            livros.add(L);   
        }
        
        conexaofactory.close(con);
        return livros;
    }
    
    public Livro atualiza(int idLivro, int status) throws SQLException{
        Livro L = new Livro();
        sql = "UPDATE livro SET status = ? WHERE idLivro = ? ";
        con = conexaofactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, L.getStatus());
        ps.setInt(2, L.getIdLivro());
        
        ps.executeUpdate();
        conexaofactory.close(con);
        return L;
    }
    
    public Livro getCarregarPorId(int idLivro) throws SQLException{
        Livro L = new Livro();
        sql="SELECT idLivro, titulo, editoria, autor, anoPublicacao, status " + " FROM livro WHERE idLivro = ?";
        con = conexaofactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1,idLivro);
        rs = ps.executeQuery();
        
        if(rs.next()){
            L.setIdLivro(rs.getInt("idLivro"));
            L.setTitulo(rs.getString("titulo"));
            L.setEditoria(rs.getString("editoria"));
            L.setAutor(rs.getString("autor"));
            L.setAnoPublicacao(rs.getDate("anoPublicacao"));
            L.setStatus(rs.getInt("status"));
        }
        conexaofactory.close(con);
        return L;
    }
    
    public Livro getPesquisa(String titulo) throws SQLException{
        try{
        Livro L = new Livro();
        sql = "SELECT idLivro, titulo, editoria, autor, anoPublicacao, status FROM livro WHERE titulo LIKE ? ";
        con = conexaofactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setString(1, "%" +  L.getTitulo() + "%");
        rs = ps.executeQuery();
        
        if(rs.next()){
           L.setIdLivro(rs.getInt("idLivro"));
           L.setTitulo(rs.getString("titulo"));
           L.setEditoria(rs.getString("editoria"));
           L.setAutor(rs.getString("autor"));
           L.setAnoPublicacao(rs.getDate("anoPublicacao"));
           L.setStatus(rs.getInt("status"));
           
           conexaofactory.close(con);
           }
           return L;
        }catch (SQLException e){
            System.out.println("Erro " + e.getMessage());
            conexaofactory.close(con);
            return null;
        }
        
        
    }
    
    
    
    public boolean excluir(Livro L) throws SQLException{
        sql = "DELETE FROM livro WHERE idLivro = ?";
        con = conexaofactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, L.getIdLivro());
        ps.executeUpdate();
        
        conexaofactory.close(con);
        return true;
    }
    
}
