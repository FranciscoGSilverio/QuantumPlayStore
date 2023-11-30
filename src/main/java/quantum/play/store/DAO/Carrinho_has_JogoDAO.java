package quantum.play.store.DAO;

import quantum.play.store.Model.Carrinho;
import quantum.play.store.Model.Carrinho_has_Jogo;
import quantum.play.store.Model.Jogo;
import quantum.play.store.Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Carrinho_has_JogoDAO {
    private Connection con;

    // Constructor to initialize the database connection
    public Carrinho_has_JogoDAO() {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        con = connectionDAO.connectToDB();
    }

    public List<Jogo> showGamesInCart(Carrinho carrinho, Usuario usuario) {
        List<Jogo> gamesInCart = new ArrayList<>();

        try {
            // Query to select games in a specific cart and for a specific user
            String query = "SELECT J.* FROM Jogo J " +
                    "JOIN Carrinho_has_Jogo CJ ON J.idJogo = CJ.Jogo_idJogo " +
                    "JOIN Carrinho C ON CJ.Carrinho_idCarrinho = C.idCarrinho " +
                    "WHERE C.idCarrinho = ? AND C.Usuario_idUsuario = ?";

            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, carrinho.getIdCarrinho());
                pst.setInt(2, usuario.getId());

                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        Jogo jogo = new Jogo(
                                rs.getInt("idJogo"),
                                rs.getString("nome"),
                                rs.getString("preco")
                        );
                        gamesInCart.add(jogo);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar jogos no carrinho: " + e.getMessage());
            e.printStackTrace();
        }

        return gamesInCart;
    }


    public void removeGameFromCart(Carrinho_has_Jogo relationToRemove) {
        try {
            // Query to delete a specific relationship (game) from the cart
            String query = "DELETE FROM Carrinho_has_Jogo " +
                    "WHERE Carrinho_idCarrinho = ? AND Carrinho_Usuario_idUsuario = ? AND Jogo_idJogo = ?";

            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, relationToRemove.getCarrinho_idCarrinho());
                pst.setInt(2, relationToRemove.getCarrinho_Usuario_idUsuario());
                pst.setInt(3, relationToRemove.getJogo_idJogo());

                int affectedRows = pst.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Jogo removido do carrinho com sucesso.");
                } else {
                    System.out.println("Falha ao remover o jogo do carrinho.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover jogo do carrinho: " + e.getMessage());
        }
    }
}
