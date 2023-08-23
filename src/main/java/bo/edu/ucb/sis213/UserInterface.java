package bo.edu.ucb.sis213;

import java.awt.EventQueue;
import javax.swing.JFrame;

import bo.edu.ucb.sis213.dao.ConexionDao;
import bo.edu.ucb.sis213.view.LoginView;

import java.sql.Connection;
import java.sql.SQLException;

public class UserInterface extends JFrame {

	public UserInterface(Connection connection) {
        LoginView logueo=new LoginView();
        logueo.setVisible(true);

		
	}
    

    public static void main(String[] args) {
            try {
                new ConexionDao();
                final Connection con = ConexionDao.getConnection();
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