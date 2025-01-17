package bo.edu.ucb.sis213.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bo.edu.ucb.sis213.bl.UsuarioBl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmacionPasswordView extends JFrame {

	private JPanel contentPane;
	private JTextField textField_user;
	private JTextField textField_password;

	public ConfirmacionPasswordView(UsuarioBl usuarioBl) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 398);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb_user = new JLabel("Usuario :");
		lb_user.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_user.setBounds(154, 118, 123, 25);
		contentPane.add(lb_user);
		
		JLabel lb_pass = new JLabel("Contrase\u00F1a :");
		lb_pass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_pass.setBounds(120, 199, 123, 25);
		contentPane.add(lb_pass);
		
		textField_user = new JTextField();
		textField_user.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_user.setBounds(287, 115, 168, 31);
		contentPane.add(textField_user);
		textField_user.setEditable(false);
		textField_user.setColumns(10);

		textField_user.setText(String.valueOf(usuarioBl.usuario.usuario));
		
		textField_password = new JTextField();
		textField_password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_password.setBounds(287, 196, 168, 31);
		contentPane.add(textField_password);
		textField_password.setColumns(10);
		
		JLabel lb_atm_montero = new JLabel("Introduzca su pin actual");
		lb_atm_montero.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lb_atm_montero.setBounds(133, 32, 354, 25);
		contentPane.add(lb_atm_montero);

        
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(240, 128, 128));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.setBounds(307, 253, 113, 37);
		contentPane.add(btnCancelar);

		btnCancelar.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				dispose();
				MenuPrincipalView menuPrincipalFrame = new MenuPrincipalView(usuarioBl);
				menuPrincipalFrame.setVisible(true);
            }
		});

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(new Color(143, 188, 143));
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAceptar.setBounds(174, 253, 98, 37);
		contentPane.add(btnAceptar); 

        btnAceptar.addActionListener(new ActionListener() {

			@Override
            public void actionPerformed(ActionEvent e) {
                int contra=0;
				contra = Integer.parseInt(textField_password.getText());
				int resConfirmacion = usuarioBl.confirmarPassword(contra);
				if(resConfirmacion==1){
					JOptionPane.showMessageDialog(null, "Ingrese su contraseña.");
				}else if(resConfirmacion==2){
					JOptionPane.showMessageDialog(null, "Contraseña correcta! Ahora puede cambiar su pin.");
					dispose();
					CambioPasswordView cambioPasswordFrame = new CambioPasswordView(usuarioBl);
					cambioPasswordFrame.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta :(");
				}
                
            }
		});

	}
}
