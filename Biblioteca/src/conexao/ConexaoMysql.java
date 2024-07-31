package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoMysql {

    public static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
    public static final String USER = "root";
    public static final String PASSWORD = "admin";

    private Connection dbconn = null;

    public Connection getConnection() {
        return dbconn;
    }

    public void OpenDatabase() {
        try {
            dbconn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado com sucesso em: " + URL);
        } catch (SQLException e) {
            System.out.println("Erro na conexão com o banco de dados: " + e.getMessage());
        }
    }

    public void CloseDatabase() {
        try {
            if (dbconn != null && !dbconn.isClosed()) {
                dbconn.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
        }
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return dbconn.prepareStatement(sql);
    }

    public int ExecutaQuery(String sql) {
        try (Statement sqlmgr = dbconn.createStatement()) {
            return sqlmgr.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
        }
        return -1;
    }

    public ResultSet ExecutaConsulta(String sql) {
        try (Statement sqlmgr = dbconn.createStatement()) {
            return sqlmgr.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao executar a consulta: " + e.getMessage());
            return null;
        }
    }

}
