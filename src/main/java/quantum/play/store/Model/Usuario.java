package quantum.play.store.Model;

public class Usuario implements Comparable<Usuario>{
    private int id;
    private String nome;
    private Endereco endereco;
    private String sexo;
    private int idade;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario( int id ,String nome, String sexo, int idade, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.endereco = endereco;
    }

    @Override
    public int compareTo(Usuario usuario) {
        return this.nome.compareTo(usuario.getNome());
    }
}
