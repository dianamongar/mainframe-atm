package bo.edu.ucb.sis213.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bo.edu.ucb.sis213.bl.UsuarioBl;

import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultaView extends JFrame {

	private JPanel contentPane;
	private JTextField saldo_actual;

	public ConsultaView(UsuarioBl usuarioBl) {
		double saldoActual=usuarioBl.consultarSaldo();
		
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
				dispose();
					MenuPrincipalView menuPrincipalFrame = new MenuPrincipalView(usuarioBl);
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
		saldo_actual.setText(String.valueOf(saldoActual));
		
		JSeparator separator_down = new JSeparator();
		separator_down.setBounds(54, 279, 493, 13);
		contentPane.add(separator_down);
	}
}