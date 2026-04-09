package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Configurações do banco de dados — ajuste conforme seu ambiente
    private static final String URL      = "jdbc:mysql://localhost:3306/sistema_gerenciamento";
    private static final String USUARIO  = "root";
    private static final String SENHA    = "";

    private ConnectionFactory() {}

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL não encontrado. Adicione o conector ao classpath.", e);
        }
    }
}