package quantum.play.store.Model;

public class Carrinho {
    private int idCarrinho;
    private int idUsuario;

    public Carrinho( int idCarrinho, int idUsuario) {
        this.idCarrinho = idCarrinho;
        this.idUsuario = idUsuario;
    }

    public int getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(int idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
