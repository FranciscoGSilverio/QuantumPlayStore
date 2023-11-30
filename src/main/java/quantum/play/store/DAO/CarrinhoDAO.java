package quantum.play.store.DAO;

import quantum.play.store.Model.Carrinho;
import quantum.play.store.Model.Jogo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDAO {
    private Connection con;

    public CarrinhoDAO() {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        con = connectionDAO.connectToDB();
    }
    public void insertCarrinho(Carrinho carrinho) {
        try {
            String query = "INSERT INTO Carrinho (idCarrinho, Usuario_idUsuario) VALUES (?, ?)";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, carrinho.getIdCarrinho());
                pst.setInt(2, carrinho.getIdUsuario());

                int affectedRows = pst.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Carrinho cadastrado com sucesso.");
                } else {
                    System.out.println("Falha ao cadastrar o carrinho.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar carrinho: " + e.getMessage());
        }
    }
    public Carrinho getCarrinhoById(int id) {
        try {
            String query = "SELECT * FROM Carrinho WHERE idCarrinho = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, id);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        return new Carrinho(rs.getInt("idCarrinho"),
                                rs.getInt("Usuario_idUsuario")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar carrinho: " + e.getMessage());
        }
        return null;
    }
    public Carrinho getCarrinhoByUserId(int userId){
        try {
            String query = "SELECT * FROM Carrinho WHERE Usuario_idUsuario = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, userId);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        return new Carrinho(
                                rs.getInt("idCarrinho"),
                                rs.getInt("Usuario_idUsuario")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar carrinho: " + e.getMessage());
        }
        return null;
    }
    public void addJogoToCarrinho(int carrinhoId, int jogoId) {
        try {
            String query = "INSERT INTO Carrinho_has_Jogo (Carrinho_idCarrinho, Carrinho_Usuario_idUsuario, Jogo_idJogo) " +
                    "VALUES (?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, carrinhoId);
                pst.setInt(2, getCarrinhoById(carrinhoId).getIdUsuario()); // Assuming you have a method to get Usuario ID for a Carrinho
                pst.setInt(3, jogoId);

                int affectedRows = pst.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Jogo adicionado ao carrinho com sucesso.");
                } else {
                    System.out.println("Falha ao adicionar o jogo ao carrinho.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar jogo ao carrinho: " + e.getMessage());
        }
    }
    public List<Jogo> getJogosInCarrinho(int carrinhoId) {
        List<Jogo> jogos = new ArrayList<>();
        try {
            String query = "SELECT Jogo.* FROM Jogo " +
                    "INNER JOIN Carrinho_has_Jogo ON Jogo.idJogo = Carrinho_has_Jogo.Jogo_idJogo " +
                    "WHERE Carrinho_has_Jogo.Carrinho_idCarrinho = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, carrinhoId);

                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        jogos.add(new Jogo(
                                rs.getInt("idJogo"),
                                rs.getString("nome"),
                                rs.getString("preco")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter jogos do carrinho: " + e.getMessage());
        }
        return jogos;
    }

}
