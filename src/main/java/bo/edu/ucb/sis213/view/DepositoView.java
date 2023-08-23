package bo.edu.ucb.sis213.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bo.edu.ucb.sis213.bl.UsuarioBl;

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

public class DepositoView extends JFrame {

	private JPanel contentPane;
	private JTextField cant_depositada;

	public DepositoView(UsuarioBl usuarioBl) {
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
		
		
		JLabel lb_desposito = new JLabel("Deposito");
		lb_desposito.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lb_desposito.setBounds(67, 42, 143, 29);
		contentPane.add(lb_desposito);
		
		JLabel lb_cant_depo = new JLabel("Cantidad a depositar :");
		lb_cant_depo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lb_cant_depo.setBounds(92, 144, 143, 19);
		contentPane.add(lb_cant_depo);
		
		cant_depositada = new JTextField();
		cant_depositada.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cant_depositada.setBounds(257, 138, 195, 27);
		contentPane.add(cant_depositada);
		cant_depositada.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(240, 128, 128));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.setBounds(307, 213, 113, 37);
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
		btnAceptar.setBounds(174, 213, 98, 37);
		contentPane.add(btnAceptar);

		btnAceptar.addActionListener(new ActionListener() {
			double mon=0;
			@Override
            public void actionPerformed(ActionEvent e) {
				mon= Double.parseDouble(cant_depositada.getText());
				double resultado = usuarioBl.depositar(mon);
				if(resultado==1){
					JOptionPane.showMessageDialog(null, "Cantidad no válida :(");
				}else if(resultado==3){
					JOptionPane.showMessageDialog(null, "No se pudo realizar el depósito :(");
				}else{
					JOptionPane.showMessageDialog(null, "Depósito realizado con éxito!"+"su saldo actual es: "+resultado+" Bs.");
					dispose();
					MenuPrincipalView menuPrincipalFrame = new MenuPrincipalView(usuarioBl);
					menuPrincipalFrame.setVisible(true);
				}
			}
		});
		JSeparator separator_down = new JSeparator();
		separator_down.setBounds(54, 279, 493, 13);
		contentPane.add(separator_down);
	}
}
