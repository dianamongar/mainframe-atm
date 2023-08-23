package bo.edu.ucb.sis213.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDao {
    private static String HOST;
    private static int PORT;
    private static String USER;
    private static String PASSWORD;
    private static String DATABASE;

    public ConexionDao(){
        HOST = "127.0.0.1";
        PORT = 3306;
        USER = "root";
        PASSWORD = "123456";
        DATABASE = "atm";
    }

    public static Connection getConnection() throws SQLException {
        String jdbcUrl = String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DATABASE);
        try {
            // Asegúrate de tener el driver de MySQL agregado en tu proyecto
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL Driver not found.", e);
        }

        return DriverManager.getConnection(jdbcUrl, USER, PASSWORD);
    }
}
