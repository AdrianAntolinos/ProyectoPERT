package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame {

	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setTitle("Menú");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 181);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);

		JButton btnGestinDeProveedores = new JButton("Gestión de proveedores");
		btnGestinDeProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GestionProveedores proveedores = new GestionProveedores();
				proveedores.setVisible(true);
				dispose();

			}
		});
		btnGestinDeProveedores.setBounds(34, 26, 386, 29);
		panel.add(btnGestinDeProveedores);

		JButton btnNewButton = new JButton("Gestión de facturas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GestionFacturas ficheros = new GestionFacturas();
				ficheros.setVisible(true);
				dispose();

			}
		});
		btnNewButton.setBounds(34, 94, 386, 29);
		panel.add(btnNewButton);
	}

}
