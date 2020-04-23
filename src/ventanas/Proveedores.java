package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Proveedores extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proveedores frame = new Proveedores();
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
	public Proveedores() {
		setTitle("Gestión de proveedores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("ALTA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Alta alta = new Alta();
				alta.setVisible(true);
				dispose();

			}
		});
		btnNewButton.setBounds(65, 22, 389, 29);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("BAJA");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Baja baja = new Baja();
				baja.setVisible(true);
				dispose();

			}
		});
		btnNewButton_1.setBounds(65, 63, 389, 29);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("MODIFICAR");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Modificar modificar = new Modificar();
				modificar.setVisible(true);
				dispose();

			}
		});
		btnNewButton_2.setBounds(65, 104, 389, 29);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("CONSULTAR");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Consultar consultar = new Consultar();
				consultar.setVisible(true);
				dispose();

			}
		});
		btnNewButton_3.setBounds(65, 146, 389, 29);
		contentPane.add(btnNewButton_3);

		JButton btnVolverAlMen = new JButton("VOLVER AL MENÚ");
		btnVolverAlMen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();

			}
		});
		btnVolverAlMen.setBounds(65, 187, 389, 29);
		contentPane.add(btnVolverAlMen);
	}
}
