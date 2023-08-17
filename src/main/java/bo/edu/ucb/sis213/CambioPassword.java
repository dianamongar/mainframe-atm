package bo.edu.ucb.sis213;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class CambioPassword extends JFrame {

	private JPanel contentPane;
	private JTextField nuevo_pass;
	private JTextField confir_pass;

	public CambioPassword(Connection connection, Usuario usuario) {
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
		btnAceptar.setBackground(new Color(143, 188, 143));
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAceptar.setBounds(174, 213, 98, 37);
		contentPane.add(btnAceptar);
		
		JLabel lb_cambio_pass = new JLabel("Cambio de contrase\u00F1a");
		lb_cambio_pass.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lb_cambio_pass.setBounds(67, 42, 225, 29);
		contentPane.add(lb_cambio_pass);
		
		JLabel lb_new_pass = new JLabel("Nueva contrase\u00F1a :");
		lb_new_pass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lb_new_pass.setBounds(89, 106, 143, 19);
		contentPane.add(lb_new_pass);
		
		nuevo_pass = new JTextField();
		nuevo_pass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nuevo_pass.setBounds(242, 100, 195, 27);
		contentPane.add(nuevo_pass);
		nuevo_pass.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(240, 128, 128));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.setBounds(307, 213, 113, 37);
		contentPane.add(btnCancelar);
		
		JSeparator separator_down = new JSeparator();
		separator_down.setBounds(54, 279, 493, 13);
		contentPane.add(separator_down);
		
		confir_pass = new JTextField();
		confir_pass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		confir_pass.setColumns(10);
		confir_pass.setBounds(242, 147, 195, 27);
		contentPane.add(confir_pass);

		
		
		JLabel lb_conf_pass = new JLabel("Confirme contrase\u00F1a:");
		lb_conf_pass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lb_conf_pass.setBounds(89, 153, 143, 19);
		contentPane.add(lb_conf_pass);

		btnAceptar.addActionListener(new ActionListener() {

			@Override
            public void actionPerformed(ActionEvent e) {
                int contra = Integer.parseInt(nuevo_pass.getText());
				int contraconf = Integer.parseInt(confir_pass.getText());
				int res = usuario.cambiarPassword(connection, contra, contraconf);
				if(res == 1){
					JOptionPane.showMessageDialog(null, "Se ha cambiado correctamente su password.");
					dispose();
					MenuPrincipal menuPrincipalFrame = new MenuPrincipal(connection, usuario);
					menuPrincipalFrame.setVisible(true);
				}else{
					if(res == 2){
						JOptionPane.showMessageDialog(null, "No se pudo realizar el cambio de password.");
					}else{
						JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden :(");
					}
					
				}
                
            }
		});
	}
}