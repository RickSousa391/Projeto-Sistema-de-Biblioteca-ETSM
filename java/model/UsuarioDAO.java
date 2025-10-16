package model;

import factory.conexaofactory;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class UsuarioDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = "";

    public boolean gravar(Usuario u) throws SQLException {
        con = conexaofactory.conectar();
        
        if (u.getIdUsuario() == 0 ){
            sql = "INSERT INTO usuario(nome, contato, email, cpf)"
                    +"VALUES (?, ?, ?, ?)";
            
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getNome());
            ps.setString(2, u.getContato());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getCpf());
            
        } else {
            sql = "UPDATE usuario SET nome = ?, contato = ?, email = ? , cpf = ? " 
                   + " WHERE idUsuario = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getNome());
            ps.setString(2, u.getContato());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getCpf());
            ps.setInt(5, u.getIdUsuario());
        }
        
        ps.executeUpdate();
        conexaofactory.close(con);
        return true;
    }

    public ArrayList<Usuario> getLista() throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList();
        sql = "SELECT idUsuario, nome, contato, email, cpf FROM usuario";
        con = conexaofactory.conectar();
        ps = con.prepareStatement(sql);

        rs = ps.executeQuery();

        while (rs.next()) {
            Usuario usuariosL = new Usuario();
            
            usuariosL.setIdUsuario(rs.getInt("idUsuario"));
            usuariosL.setNome(rs.getString("nome"));
            usuariosL.setContato(rs.getString("contato"));
            usuariosL.setEmail(rs.getString("email"));
            usuariosL.setCpf(rs.getString("cpf"));
            usuarios.add(usuariosL);
        }
        conexaofactory.close(con);
        return usuarios;
   }
    
    public Usuario getUsuarioPorCPF(String nome, String cpf) throws SQLException{
        try{
        Usuario u = new Usuario();
        sql = "SELECT nome, cpf FROM usuario WHERE nome = ? AND cpf = ? LIMIT 1";
        con = conexaofactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, cpf);
   
        rs = ps.executeQuery();
        
        if(rs.next()){
            u.setNome(rs.getString("nome"));
            u.setCpf(rs.getString("cpf"));
            return u;
        }else{
            return u;
        }
        }catch (SQLException e){
            System.out.println("Erro " + e.getMessage());
            conexaofactory.close(con);
            return null;
        }
    }
    
    public Usuario getCarregarPorId(int idUsuario) throws SQLException{
        Usuario u = new Usuario();
        sql="SELECT idUsuario, nome, contato, email, cpf " + " FROM usuario WHERE idUsuario = ?";
        con = conexaofactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1,idUsuario);
        rs = ps.executeQuery();
        
        if(rs.next()){
            u.setIdUsuario(rs.getInt("idUsuario"));
            u.setNome(rs.getString("nome"));
            u.setContato(rs.getString("contato"));
            u.setEmail(rs.getString("email"));
            u.setCpf(rs.getString("cpf"));
        }
        conexaofactory.close(con);
        return u;
    }
    
    public boolean excluir(Usuario u) throws SQLException{
        sql = "DELETE FROM usuario WHERE idUsuario = ?";
        con = conexaofactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, u.getIdUsuario());
        ps.executeUpdate();
        
        conexaofactory.close(con);
        return true;
    }
}
