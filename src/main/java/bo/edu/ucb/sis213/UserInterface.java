package bo.edu.ucb.sis213;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserInterface extends JFrame {
    public Usuario usuario=new Usuario(null,null,0,0,0);

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 3306;
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private static final String DATABASE = "atm";

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



	public UserInterface(Connection connection) {
        Login logueo=new Login(connection);
        logueo.setVisible(true);

		
	}
    

    public static void main(String[] args) {
            try {
                final Connection con = getConnection();
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            UserInterface frame = new UserInterface(con);
                            frame.setVisible(false);
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (SQLException ex) {
                System.err.println("No se puede conectar a Base de Datos");
                ex.printStackTrace();
                System.exit(1);
            }
		
	}
}