
package model;

public class Bibliotecaria {
    private int idBibliotecaria;
    private String nome;
    private String contato;
    private String email;
    private String registroFuncional;
    private String senha;
    
    public Bibliotecaria(){
    }

    public int getIdBibliotecaria() {
        return idBibliotecaria;
    }

    public void setIdBibliotecaria(int idBibliotecaria) {
        this.idBibliotecaria = idBibliotecaria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistroFuncional() {
        return registroFuncional;
    }

    public void setRegistroFuncional(String registroFuncional){
        this.registroFuncional = registroFuncional;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }   
    
}
