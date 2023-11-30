package quantum.play.store.DAO;

import quantum.play.store.Model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {
    private Connection con;
    public EnderecoDAO() {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        con = connectionDAO.connectToDB();
    }
    public boolean updateAddressByUserId(int usuarioId, Endereco endereco){
        try {
            String query = "UPDATE Endereco SET Rua = ?, Numero = ?, CEP = ?, Cidade = ?, Estado = ?, Complemento = ? WHERE Usuario_idUsuario = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, endereco.getRua());
                pst.setString(2, endereco.getNumero());
                pst.setString(3, endereco.getCep());
                pst.setString(4, endereco.getCidade());
                pst.setString(5, endereco.getEstado());
                pst.setString(6, endereco.getComplemento());
                pst.setInt(7, usuarioId);

                pst.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar endereço: " + e.getMessage());
        }
        return false;
    }
}
