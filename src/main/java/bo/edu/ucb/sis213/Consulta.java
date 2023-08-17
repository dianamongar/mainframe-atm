package bo.edu.ucb.sis213;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Menu;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Consulta extends JFrame {

	private JPanel contentPane;
	private JTextField saldo_actual;
	public double saldo_ahora(Usuario usuario, Connection connection){
		double res=0;
		 String query = "SELECT saldo FROM usuarios WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, usuario.id);
            ResultSet resultSet = preparedStatement.executeQuery();

                
            if (resultSet.next()) {
                res = resultSet.getDouble("saldo");

                String updateQueryHist = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, 'consultaSaldo', ?)"; // Cambia esto según tu tabla
                PreparedStatement preparedStatementHist = connection.prepareStatement(updateQueryHist);
                preparedStatementHist.setInt(1, usuario.id);
                preparedStatementHist.setDouble(2, res);
                preparedStatementHist.executeUpdate();

                System.out.println("Su saldo actual es: $" + res);
				return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return 0;
	}
	public Consulta(Connection connection, Usuario usuario) {
		double res=saldo_ahora(usuario, connection);
		usuario.saldo=res;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 355);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator_up = new JSeparator();
		separator_up.setBounds(54, 82, 493, 13);
		contentPane.add(separator_up);
		
		JLabel atm_montero = new JLabel("ATM Montero");
		atm_montero.setFont(new Font("Tahoma", Font.PLAIN, 24));
		atm_montero.setBounds(228, 4, 155, 29);
		contentPane.add(atm_montero);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(new Color(65, 105, 225));
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAceptar.setBounds(242, 213, 98, 37);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
					MenuPrincipal menuPrincipalFrame = new MenuPrincipal(connection, usuario);
					menuPrincipalFrame.setVisible(true);
            }
		});
		
		JLabel lb_consulta = new JLabel("Consulta");
		lb_consulta.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lb_consulta.setBounds(67, 42, 143, 29);
		contentPane.add(lb_consulta);
		
		JLabel lb_cant_actual = new JLabel("Cantidad actual :");
		lb_cant_actual.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lb_cant_actual.setBounds(92, 144, 143, 19);
		contentPane.add(lb_cant_actual);
		
		

		saldo_actual = new JTextField();
		saldo_actual.setEditable(false);
		saldo_actual.setFont(new Font("Tahoma", Font.PLAIN, 18));
		saldo_actual.setBounds(257, 138, 195, 27);
		contentPane.add(saldo_actual);
		saldo_actual.setColumns(10);
		saldo_actual.setText(String.valueOf(res));
		
		JSeparator separator_down = new JSeparator();
		separator_down.setBounds(54, 279, 493, 13);
		contentPane.add(separator_down);
	}
}