
package model;

import factory.conexaofactory;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BibliotecariaDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql="";
    
    public boolean gravar (Bibliotecaria b) throws SQLException{
        con = conexaofactory.conectar();
        
        if(b.getIdBibliotecaria()==0){
            sql="INSERT INTO bibliotecaria(nome,contato,email, senha, registroFuncional)" +
                "VALUES(?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, b.getNome());
            ps.setString(2, b.getContato());
            ps.setString(3, b.getEmail());
            ps.setString(4, b.getSenha());
            ps.setString(5, b.getRegistroFuncional());
        }else{
            
        }
        ps.executeUpdate();
        conexaofactory.close(con);
        return true;
    }
    
    public Bibliotecaria getBibliotecariaPorSN(String nome, String senha) throws SQLException{
        try{
        Bibliotecaria b = new Bibliotecaria();
        sql = "SELECT idBibliotecaria, nome, senha FROM bibliotecaria WHERE nome = ? AND senha = ? LIMIT 1";
        con = conexaofactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, senha);
   
        rs = ps.executeQuery();
        
        if(rs.next()){
            b.setIdBibliotecaria(rs.getInt("idBibliotecaria"));
            b.setNome(rs.getString("nome"));
            b.setSenha(rs.getString("senha"));
            
            conexaofactory.close(con);
            return b;
        }else{
            return b;
        }
        }catch (SQLException e){
            System.out.println("Erro " + e.getMessage());
            conexaofactory.close(con);
            return null;
        }
    }
    
    public Bibliotecaria getBibliotecariaPorId(int id) throws SQLException{
        try{
            Bibliotecaria b = new Bibliotecaria();
            sql = "SELECT idBibliotecaria, nome FROM bibliotecaria WHERE idBibliotecaria = ?";
            con = conexaofactory.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            
            if (rs.next()){
                b.setIdBibliotecaria(rs.getInt("idBibliotecaria"));
                b.setNome(rs.getString("nome"));
            }
            conexaofactory.close(con);
            return b;
        } catch(SQLException e){
            conexaofactory.close(con);
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }
    
     public ArrayList<Bibliotecaria> getLista() throws SQLException {
        ArrayList<Bibliotecaria> bibliotecarios = new ArrayList();
        sql = "SELECT idBibliotecaria, nome, contato, email, registrofuncional FROM bibliotecaria";
        con = conexaofactory.conectar();
        ps = con.prepareStatement(sql);

        rs = ps.executeQuery();

        while (rs.next()) {
            Bibliotecaria bibliotecariaL = new Bibliotecaria();
            
            bibliotecariaL.setIdBibliotecaria(rs.getInt("idBibliotecaria"));
            bibliotecariaL.setNome(rs.getString("nome"));
            bibliotecariaL.setContato(rs.getString("contato"));
            bibliotecariaL.setEmail(rs.getString("email"));
            bibliotecariaL.setRegistroFuncional(rs.getString("registroFuncional"));
            bibliotecarios.add(bibliotecariaL);
        }
        conexaofactory.close(con);
        return bibliotecarios;
   }
     
   public Bibliotecaria getBibliotecariaPorCONTA(int idBibliotecaria, 
           String registroFuncional) throws SQLException{
       try{
           Bibliotecaria b = new Bibliotecaria();
           
           sql = "SELECT idBibliotecaria, nome, contato, email, registroFuncional FROM bibliotecaria WHERE idBibliotecaria = ? ";
           con = conexaofactory.conectar();
           ps = con.prepareStatement(sql);
           ps.setInt(1, idBibliotecaria);
           rs = ps.executeQuery();
           
           if(rs.next()){
           b.setIdBibliotecaria(rs.getInt("idBibliotecaria"));
           b.setNome(rs.getString("nome"));
           b.setContato(rs.getString("contato"));
           b.setEmail(rs.getString("email"));
           b.setRegistroFuncional(rs.getString("registroFuncional"));
           
           conexaofactory.close(con);
           }
           return b;
       }catch (SQLException e){
           System.out.println("Erro" + e.getMessage());
           conexaofactory.close(con);
           return null;
           
       }
   }
   
   public Bibliotecaria getBibliotecariaPorLogin(String nome, String contato, String registroFuncional) throws SQLException{
        try{
        Bibliotecaria b = new Bibliotecaria();
        sql = "SELECT idBibliotecaria, nome, contato, registroFuncional FROM bibliotecaria WHERE nome = ? AND contato = ? AND registroFuncional = ?  LIMIT 1";
        con = conexaofactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2,contato );
        ps.setString(3,registroFuncional);
   
        rs = ps.executeQuery();
        
        if(rs.next()){
            b.setIdBibliotecaria(rs.getInt("idBibliotecaria"));
            b.setNome(rs.getString("nome"));
            b.setContato(rs.getString("contato"));
            b.setRegistroFuncional(rs.getString("registroFuncional"));
            
            conexaofactory.close(con);
            return b;
        }else{
            return b;
        }
        }catch (SQLException e){
            System.out.println("Erro " + e.getMessage());
            conexaofactory.close(con);
            return null;
        }
    }
   
   
   
     
}
