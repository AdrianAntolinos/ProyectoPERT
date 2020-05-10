package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.Proveedor;
import modelo.BBDDGestor;

public class AltaProveedor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaProveedor frame = new AltaProveedor();
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
	public AltaProveedor() {
		setTitle("ALTA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 264);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCifproveedor = new JLabel("CIF DEL PROVEEDOR:");
		lblCifproveedor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCifproveedor.setBounds(185, 23, 159, 16);
		contentPane.add(lblCifproveedor);

		JLabel lblRegnotarial = new JLabel("NÚMERO DE REGISTRO NOTARIAL:");
		lblRegnotarial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRegnotarial.setBounds(96, 79, 248, 16);
		contentPane.add(lblRegnotarial);

		JLabel lblRazproveedor = new JLabel("RAZÓN SOCIAL DEL PROVEEDOR:");
		lblRazproveedor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRazproveedor.setBounds(86, 51, 258, 16);
		contentPane.add(lblRazproveedor);

		JLabel lblFechomologacin = new JLabel("FECHA DE HOMOLOGACIÓN:");
		lblFechomologacin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechomologacin.setBounds(124, 163, 220, 16);
		contentPane.add(lblFechomologacin);

		JLabel lblSegimporte = new JLabel("IMPORTE DEL SEGURO DE RESPONSABILIDAD CIVIL:");
		lblSegimporte.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSegimporte.setBounds(-25, 135, 369, 16);
		contentPane.add(lblSegimporte);

		JLabel lblSegresponsabilidad = new JLabel("CÓDIGO DE SEGURO DE RESPONSABILIDAD CIVIL:");
		lblSegresponsabilidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSegresponsabilidad.setBounds(29, 107, 315, 16);
		contentPane.add(lblSegresponsabilidad);

		textField = new JTextField();
		textField.setBounds(356, 18, 192, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(356, 46, 192, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(356, 74, 192, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(356, 102, 192, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setBounds(356, 130, 192, 26);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(356, 158, 192, 26);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		btnNewButton = new JButton("DAR DE ALTA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textField.getText().length() == 0 || textField_1.getText().length() == 0
						|| textField_2.getText().length() == 0 || textField_3.getText().length() == 0
						|| textField_4.getText().length() == 0 || textField_5.getText().length() == 0) {

					JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos para dar el alta", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else {

					String cif = textField.getText();
					String razonsocial = textField_1.getText();
					String regnotarial = textField_2.getText();
					String seg_responsabilidad = textField_3.getText();
					Double seg_importe = Double.parseDouble(textField_4.getText());
					Date fechahomologacion = stringToDate(textField_5.getText(), null);

					Proveedor p = new Proveedor(cif, razonsocial, regnotarial, seg_responsabilidad, seg_importe,
							fechahomologacion);

					BBDDGestor gestor = new BBDDGestor();
					gestor.altaProveedor(p);

					JOptionPane.showMessageDialog(null, "Se ha dado de alta al proveedor correctamente", "INFORMACIÓN",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		btnNewButton.setBounds(356, 191, 192, 29);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("VOLVER");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GestionProveedores proveedores = new GestionProveedores();
				proveedores.setVisible(true);
				dispose();

			}
		});
		btnNewButton_1.setBounds(152, 191, 192, 29);
		contentPane.add(btnNewButton_1);
	}

	public static Date stringToDate(String fechaEnString, String formato) {
		if (fechaEnString == null) {
			return null;
		}
		if (formato == null) {
			formato = "dd/MM/yyyy";
		}
		Date fechaenjava = null;
		SimpleDateFormat miFormato2 = new SimpleDateFormat(formato);
		try {
			fechaenjava = miFormato2.parse(fechaEnString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fechaenjava;
	}

}
