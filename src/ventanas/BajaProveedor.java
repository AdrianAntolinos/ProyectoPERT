package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelo.BBDDGestor;

public class BajaProveedor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaProveedor frame = new BajaProveedor();
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
	public BajaProveedor() {
		setTitle("BAJA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCifproveedor = new JLabel("CIF_PROVEEDOR:");
		lblCifproveedor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCifproveedor.setBounds(6, 31, 130, 16);
		contentPane.add(lblCifproveedor);

		textField = new JTextField();
		textField.setBounds(149, 26, 261, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("DAR DE BAJA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String cif = textField.getText();
				BBDDGestor gestor = new BBDDGestor();
				gestor.bajaProveedor(cif);

			}
		});
		btnNewButton.setBounds(16, 59, 394, 29);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("VOLVER");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GestionProveedores proveedores = new GestionProveedores();
				proveedores.setVisible(true);
				dispose();

			}
		});
		btnNewButton_1.setBounds(19, 100, 391, 29);
		contentPane.add(btnNewButton_1);
	}

}
