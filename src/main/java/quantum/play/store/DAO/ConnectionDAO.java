package quantum.play.store.DAO;

import java.sql.*;

public class ConnectionDAO {
    Connection con;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;

    String database = "QuantumPlayStore";
    String user = "root";
    String password = "sky2003biel";
    String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC";

    public Connection connectToDB() {
        try {
            con = java.sql.DriverManager.getConnection(url, user, password);
            return con;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }

}
