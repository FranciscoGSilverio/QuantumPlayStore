package quantum.play.store.DAO;

import quantum.play.store.Model.Jogo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JogoDAO {
    private Connection con;
    public JogoDAO() {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        con = connectionDAO.connectToDB();
    }
    public void addJogoToCarrinho(int carrinhoId, int jogoId) {
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        carrinhoDAO.addJogoToCarrinho(carrinhoId, jogoId);
    }
    public List<Jogo> getJogosInCarrinho(int carrinhoId) {
        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
        return carrinhoDAO.getJogosInCarrinho(carrinhoId);}
    public List<Jogo> getAllJogos() {
        List<Jogo> jogos = new ArrayList<>();
        try {
            String query = "SELECT * FROM Jogo";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        jogos.add(new Jogo(rs.getInt("idJogo"),
                                rs.getString("nome"),
                                rs.getString("preco")
                        ));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar jogos: " + e.getMessage());
        }
        return jogos;
    }
}
