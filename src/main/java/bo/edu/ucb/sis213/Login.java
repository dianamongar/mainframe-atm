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

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField_user;
	private JTextField textField_password;

	public Login(Connection connection) {
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
		textField_user.setColumns(10);
		
		textField_password = new JTextField();
		textField_password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_password.setBounds(287, 196, 168, 31);
		contentPane.add(textField_password);
		textField_password.setColumns(10);
		
		JLabel lb_atm_montero = new JLabel("ATM Montero");
		lb_atm_montero.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lb_atm_montero.setBounds(233, 32, 154, 25);
		contentPane.add(lb_atm_montero);

        
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(new Color(143, 188, 143));
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAceptar.setBounds(250, 253, 98, 37);
		contentPane.add(btnAceptar);

        btnAceptar.addActionListener(new ActionListener() {

			@Override
            public void actionPerformed(ActionEvent e) {
				Usuario temp=new Usuario(null,null,0,0,0);
                String us = textField_user.getText();
                int contra = Integer.parseInt(textField_password.getText());
                temp= temp.verificarUsuario(connection, contra, us);
				if(temp.nombre!= null){
					dispose();
					MenuPrincipal menuPrincipalFrame = new MenuPrincipal(connection, temp);
					menuPrincipalFrame.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
				}
                //
            }
		});

	}
}