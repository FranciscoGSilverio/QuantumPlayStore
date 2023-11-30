package quantum.play.store.Model;

public class Jogo {
    private int id;
    private String nome;
    private String preco;
    public Jogo(int id, String nome, String preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

}
