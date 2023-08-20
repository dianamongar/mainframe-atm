package bo.edu.ucb.sis213;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;

public class Retiro extends JFrame {

	private JPanel contentPane;
	private JTextField cant_retirada;

	public Retiro(Usuario usuario, Connection connection) {
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
		
		JLabel lb_retiro = new JLabel("Retiro");
		lb_retiro.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lb_retiro.setBounds(67, 42, 143, 29);
		contentPane.add(lb_retiro);
		
		JLabel lb_cant_reti = new JLabel("Cantidad a retirar :");
		lb_cant_reti.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lb_cant_reti.setBounds(92, 144, 143, 19);
		contentPane.add(lb_cant_reti);
		
		cant_retirada = new JTextField();
		cant_retirada.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cant_retirada.setBounds(257, 138, 195, 27);
		contentPane.add(cant_retirada);
		cant_retirada.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(240, 128, 128));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.setBounds(307, 213, 113, 37);
		contentPane.add(btnCancelar);

		btnCancelar.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				dispose();
				MenuPrincipal menuPrincipalFrame = new MenuPrincipal(connection, usuario);
				menuPrincipalFrame.setVisible(true);
            }
		});

		btnAceptar.addActionListener(new ActionListener() {
			double mon=0;
			@Override
            public void actionPerformed(ActionEvent e) {
				if(cant_retirada.getText()!=""){
					mon= Double.parseDouble(cant_retirada.getText());
					if(mon>0){
						int res=usuario.retirar(connection, mon);
						if(res==3){
						JOptionPane.showMessageDialog(null, "Retiro realizado con éxito!"+"su saldo actual es: "+usuario.saldo);
						dispose();
						MenuPrincipal menuPrincipalFrame = new MenuPrincipal(connection, usuario);
						menuPrincipalFrame.setVisible(true);
						
						}else if(res==2){
							JOptionPane.showMessageDialog(null, "saldo insuficiente :(");
						}else if(res==1){
							JOptionPane.showMessageDialog(null, "Cantidad no válida :(");
						}else {
							JOptionPane.showMessageDialog(null, "No se pudo realizar el retiro :(");
						}
					}else{
						JOptionPane.showMessageDialog(null, "Cantidad no válida :(");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Ingrese cantidad a retirar.");
				}
					
					//depositoFrame.setVisible(true);
				}
			});
		
		
		JSeparator separator_down = new JSeparator();
		separator_down.setBounds(54, 279, 493, 13);
		contentPane.add(separator_down);
	}
}