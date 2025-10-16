

package model;

public class QuatidadeLivro {
    private int idQuantidadeLivro;
    private Livro livro;
    private int quantidade;
    
    public QuatidadeLivro(){
        
    }

    public int getIdQuantidadeLivro() {
        return idQuantidadeLivro;
    }

    public void setIdQuantidadeLivro(int idQuantidadeLivro) {
        this.idQuantidadeLivro = idQuantidadeLivro;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    
    
}
