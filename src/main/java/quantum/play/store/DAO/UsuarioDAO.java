package quantum.play.store.DAO;

import quantum.play.store.Model.Carrinho;
import quantum.play.store.Model.Endereco;
import quantum.play.store.Model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsuarioDAO{
    private Connection con;
    CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
    public UsuarioDAO() {
        ConnectionDAO connectionDAO = new ConnectionDAO();
        con = connectionDAO.connectToDB();
    }
    public void insertUsuario(Usuario usuario) {
        try {
            String query = "INSERT INTO Usuario (nome, sexo, idade) VALUES (?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                pst.setString(1, usuario.getNome());
                pst.setString(2, usuario.getSexo());
                pst.setInt(3, usuario.getIdade());

                int affectedRows = pst.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Usuário cadastrado com sucesso.");

                    // Retrieve the auto-generated ID
                    try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            usuario.setId(generatedKeys.getInt(1));
                            // Now that the user is inserted, insert the address
                            insertEndereco(usuario.getEndereco(), usuario.getId());
                            Carrinho carrinho = new Carrinho(0, usuario.getId());
                            carrinhoDAO.insertCarrinho(carrinho);
                            System.out.println("ID do usuário: " + usuario.getId());
                        }
                    }
                } else {
                    System.out.println("Falha ao cadastrar o usuário.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
    private void insertEndereco(Endereco endereco, int idUsuario) {
        try {
            String query = "INSERT INTO Endereco (Rua, Numero, CEP, Cidade, Estado, Complemento, Usuario_idUsuario) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, endereco.getRua());
                pst.setString(2, endereco.getNumero());
                pst.setString(3, endereco.getCep());
                pst.setString(4, endereco.getCidade());
                pst.setString(5, endereco.getEstado());
                pst.setString(6, endereco.getComplemento());
                pst.setInt(7, idUsuario);

                pst.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar endereço: " + e.getMessage());
        }
    }
    public Usuario getUsuarioById(int id) {
        try {
            String query = "SELECT * FROM Usuario WHERE idUsuario = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, id);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        int idUsuario = rs.getInt("idUsuario");
                        String nome = rs.getString("nome");
                        String sexo = rs.getString("sexo");
                        int idade = rs.getInt("idade");

                        // Retrieve the address information for the user
                        Endereco endereco = getEnderecoByUsuarioId(idUsuario);

                        return new Usuario(idUsuario, nome, sexo, idade, endereco);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar usuário: " + e.getMessage());
        }
        return null;
    }
    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String query = "SELECT * FROM Usuario";
            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(query)) {
                while (rs.next()) {
                    int idUsuario = rs.getInt("idUsuario");
                    String nome = rs.getString("nome");
                    String sexo = rs.getString("sexo");
                    int idade = rs.getInt("idade");

                    // Retrieve the address information for the user
                    Endereco endereco = getEnderecoByUsuarioId(idUsuario);

                    usuarios.add(new Usuario(idUsuario, nome, sexo, idade, endereco));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }
    private Endereco getEnderecoByUsuarioId(int usuarioId) {
        try {
            String query = "SELECT * FROM Endereco WHERE Usuario_idUsuario = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, usuarioId);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        return new Endereco(
                                rs.getInt("idEndereco"),
                                rs.getString("Rua"),
                                rs.getString("Numero"),
                                rs.getString("CEP"),
                                rs.getString("Cidade"),
                                rs.getString("Estado"),
                                rs.getString("Complemento"),
                                usuarioId
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter endereço: " + e.getMessage());
        }
        return null;
    }
    public boolean deleteUsuario(int id) {
        try {
            String query = "DELETE FROM Usuario WHERE idUsuario = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setInt(1, id);

                int affectedRows = pst.executeUpdate();
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover usuário: " + e.getMessage());
            return false;
        }
    }
    public boolean updateUsuario(Usuario usuario) {
        try {
            String query = "UPDATE Usuario SET nome = ?, sexo = ?, idade = ? WHERE idUsuario = ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, usuario.getNome());
                pst.setString(2, usuario.getSexo());
                pst.setInt(3, usuario.getIdade());
                pst.setInt(4, usuario.getId());

                int affectedRows = pst.executeUpdate();
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
    }
    public List<Usuario> getUsuariosByNome(String nome) {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String query = "SELECT * FROM Usuario WHERE nome LIKE ?";
            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, "%" + nome + "%");

                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        int idUsuario = rs.getInt("idUsuario");
                        String nomeUsuario = rs.getString("nome");
                        String sexo = rs.getString("sexo");
                        int idade = rs.getInt("idade");

                        // Retrieve the address information for the user
                        Endereco endereco = getEnderecoByUsuarioId(idUsuario);

                        usuarios.add(new Usuario(idUsuario, nomeUsuario, sexo, idade, endereco));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar usuários por nome: " + e.getMessage());
        }
        return usuarios;
    }
}


