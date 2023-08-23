package bo.edu.ucb.sis213.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bo.edu.ucb.sis213.bl.UsuarioBl;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JButton;

public class MenuPrincipalView extends JFrame {

	private JPanel contentPane;

	public MenuPrincipalView(UsuarioBl usuarioBl) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 355);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(55, 39, 467, 13);
		contentPane.add(separator);
		
		JLabel lblNewLabel = new JLabel("ATM Montero");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(228, 4, 155, 29);
		contentPane.add(lblNewLabel);
		
		JButton btnDeposito = new JButton("Deposito");
		btnDeposito.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnDeposito.setBounds(48, 78, 230, 45);
		contentPane.add(btnDeposito);

        btnDeposito.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				dispose();
				DepositoView depositoFrame = new DepositoView(usuarioBl);
				depositoFrame.setVisible(true);
            }
		});

		
		JButton btnConsultaSaldo = new JButton("Consulta saldo");
		btnConsultaSaldo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnConsultaSaldo.setBounds(48, 156, 230, 45);
		contentPane.add(btnConsultaSaldo);

        btnConsultaSaldo.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
					dispose();
					ConsultaView consultaFrame = new ConsultaView(usuarioBl);
				    consultaFrame.setVisible(true);
            }
		});
		
		JButton btnCambioContrasea = new JButton("Cambio contrase\u00F1a");
		btnCambioContrasea.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnCambioContrasea.setBounds(307, 157, 230, 45);
		contentPane.add(btnCambioContrasea);

        btnCambioContrasea.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				dispose();
					ConfirmacionPasswordView confPasswordFrame = new ConfirmacionPasswordView(usuarioBl);
					confPasswordFrame.setVisible(true);
            }
		});
		
		JButton btnRetiro = new JButton("Retiro");
		btnRetiro.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRetiro.setBounds(307, 78, 230, 45);
		contentPane.add(btnRetiro);

        btnRetiro.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				dispose();
					RetiroView retiroFrame = new RetiroView(usuarioBl);
					retiroFrame.setVisible(true);
            }
		});
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSalir.setBounds(181, 234, 230, 45);
		contentPane.add(btnSalir);

        btnSalir.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                dispose();
				LoginView loginFrame = new LoginView();
				loginFrame.setVisible(true);
            }
		});
	}
}