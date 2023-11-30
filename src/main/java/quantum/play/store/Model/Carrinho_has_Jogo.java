package quantum.play.store.Model;

public class Carrinho_has_Jogo {
    private int Carrinho_idCarrinho;
    private int Carrinho_Usuario_idUsuario;
    private int Jogo_idJogo;

    public Carrinho_has_Jogo(int Carrinho_idCarrinho, int Carrinho_Usuario_idUsuario, int Jogo_idJogo) {
        this.Carrinho_idCarrinho = Carrinho_idCarrinho;
        this.Carrinho_Usuario_idUsuario = Carrinho_Usuario_idUsuario;
        this.Jogo_idJogo = Jogo_idJogo;
    }

    public int getCarrinho_idCarrinho() {
        return Carrinho_idCarrinho;
    }

    public void setCarrinho_idCarrinho(int Carrinho_idCarrinho) {
        this.Carrinho_idCarrinho = Carrinho_idCarrinho;
    }

    public int getCarrinho_Usuario_idUsuario() {
        return Carrinho_Usuario_idUsuario;
    }

    public void setCarrinho_Usuario_idUsuario(int Carrinho_Usuario_idUsuario) {
        this.Carrinho_Usuario_idUsuario = Carrinho_Usuario_idUsuario;
    }

    public int getJogo_idJogo() {
        return Jogo_idJogo;
    }

    public void setJogo_idJogo(int Jogo_idJogo) {
        this.Jogo_idJogo = Jogo_idJogo;
    }
}
