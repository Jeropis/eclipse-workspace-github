package Graficos;

import java.awt.Color;
import java.awt.Font;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import clases.Chat;
import interfaces.ChatCodificado;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {
	private JTextField campoNombre;
	private JTextField campoMensaje;

	public Ventana() {

		try {

			Registry registro = LocateRegistry.getRegistry("192.168.1.131", 1099);
			final ChatCodificado cd = (ChatCodificado) registro.lookup("bdchats");

			getContentPane().setBackground(new Color(155, 255, 255));
			this.setSize(800, 600);
			this.setLocationRelativeTo(null);// Se pone al centro de la pantalla
			this.setTitle("chat");
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setResizable(false);
			getContentPane().setLayout(null);

			JLabel lblTituloChat = new JLabel("Chat");
			lblTituloChat.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
			lblTituloChat.setBounds(38, 31, 123, 77);
			getContentPane().add(lblTituloChat);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(38, 119, 520, 313);
			getContentPane().add(scrollPane);

			JTextArea textArea = new JTextArea();
			scrollPane.setViewportView(textArea);

			textArea.setText(cd.recuperarMensajes());

			campoNombre = new JTextField();
			campoNombre.setBackground(new Color(255, 255, 255));
			campoNombre.setBounds(568, 178, 206, 20);
			getContentPane().add(campoNombre);
			campoNombre.setColumns(10);

			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
			lblNombre.setBounds(568, 147, 206, 28);
			getContentPane().add(lblNombre);

			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(38, 473, 520, 50);
			getContentPane().add(scrollPane_1);

			campoMensaje = new JTextField();
			campoMensaje.setBackground(new Color(213, 255, 213));
			scrollPane_1.setViewportView(campoMensaje);
			campoMensaje.setColumns(10);

			JButton btnEnviar = new JButton("Enviar");
			btnEnviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (campoMensaje.getText().toLowerCase().equals("quit")) {
						System.exit(ABORT);
					} else {
						if (campoNombre.getText().isBlank() || campoMensaje.getText().isBlank()) {
							JOptionPane.showMessageDialog(null, "No has rellenado un campo");
						} else {
							Chat c = new Chat(campoNombre.getText(), campoMensaje.getText(), LocalDateTime.now());
							try {
								lblNombre.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
								lblNombre.setText(lblNombre.getText()+ ": \n" + campoNombre.getText());
								cd.añadirMensaje(campoNombre.getText(), campoMensaje.getText());
								textArea.setText(cd.recuperarMensajes(LocalDateTime.now()));
								campoNombre.setVisible(false);
								campoMensaje.setText("");
							} catch (RemoteException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			});
			btnEnviar.setBackground(new Color(125, 255, 158));
			btnEnviar.setFont(new Font("Tahoma", Font.BOLD, 20));
			btnEnviar.setBounds(608, 485, 134, 28);
			getContentPane().add(btnEnviar);
			
			JButton btnRefresh = new JButton("Refrescar Chat");
			btnRefresh.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						textArea.setText(cd.recuperarMensajes(LocalDateTime.now()));
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			btnRefresh.setBackground(new Color(255, 255, 128));
			btnRefresh.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
			btnRefresh.setBounds(424, 88, 134, 20);
			getContentPane().add(btnRefresh);
			
			JLabel lblNewLabel = new JLabel("Mensaje");
			lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
			lblNewLabel.setBounds(38, 454, 57, 20);
			getContentPane().add(lblNewLabel);

			this.setVisible(true);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
