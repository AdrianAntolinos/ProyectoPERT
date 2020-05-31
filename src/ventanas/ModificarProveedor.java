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

public class ModificarProveedor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarProveedor frame = new ModificarProveedor();
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
	public ModificarProveedor() {
		setTitle("MODIFICAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("INTRODUCE EL CIF DEL PROVEEDOR:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(72, 37, 269, 16);
		contentPane.add(label);

		JLabel label_1 = new JLabel("NÚMERO DE REGISTRONOTARIAL:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(95, 139, 248, 16);
		contentPane.add(label_1);
		label_1.setVisible(false);

		JLabel label_2 = new JLabel("RAZÓN SOCIAL DEL PROVEEDOR:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(85, 111, 258, 16);
		contentPane.add(label_2);
		label_2.setVisible(false);

		JLabel label_3 = new JLabel("FECHA DE HOMOLOGACIÓN:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(123, 223, 220, 16);
		contentPane.add(label_3);
		label_3.setVisible(false);

		JLabel label_4 = new JLabel("CÓDIGO DE SEGURO DE RESPONSABILIDAD CIVIL:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(28, 167, 315, 16);
		contentPane.add(label_4);
		label_4.setVisible(false);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(353, 32, 192, 26);
		contentPane.add(textField);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(355, 106, 192, 26);
		contentPane.add(textField_1);
		textField_1.setVisible(false);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(355, 134, 192, 26);
		contentPane.add(textField_2);
		textField_2.setVisible(false);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(355, 162, 192, 26);
		contentPane.add(textField_3);
		textField_3.setVisible(false);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(355, 190, 192, 26);
		contentPane.add(textField_4);
		textField_4.setVisible(false);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(355, 218, 192, 26);
		contentPane.add(textField_5);
		textField_5.setVisible(false);

		JLabel label_5 = new JLabel("IMPORTE DEL SEGURO DE RESPONSABILIDAD CIVIL:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(-26, 195, 369, 16);
		contentPane.add(label_5);
		label_5.setVisible(false);

		JButton btnNewButton = new JButton("MODIFICAR");
		btnNewButton.setVisible(false);

		JButton btnObtenerDatos = new JButton("OBTENER DATOS");
		btnObtenerDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textField.getText().length() == 0 || textField_1.getText().length() == 0
						|| textField_2.getText().length() == 0 || textField_3.getText().length() == 0
						|| textField_4.getText().length() == 0 || textField_5.getText().length() == 0) {

				}

				BBDDGestor gestor = new BBDDGestor();

				String cif = textField.getText();
				Proveedor prov = gestor.consultarUnProveedor(cif);

				if (prov != null) {

					textField_1.setText(prov.getRaz_proveedor());
					textField_2.setText(prov.getReg_notarial());
					textField_3.setText(prov.getSeg_responsabilidad());
					textField_4.setText(prov.getSeg_importe() + "");
					textField_5.setText(dateToString(prov.getFec_homologacion(), null));

					textField_1.setVisible(true);
					textField_2.setVisible(true);
					textField_3.setVisible(true);
					textField_4.setVisible(true);
					textField_5.setVisible(true);
					label_1.setVisible(true);
					label_2.setVisible(true);
					label_3.setVisible(true);
					label_4.setVisible(true);
					label_5.setVisible(true);
					btnNewButton.setVisible(true);

				} else {
					// JOptionPane.showMessageDialog(null, "No hay ningún proveedor con este cif",
					// "ERROR",
					// JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnObtenerDatos.setBounds(353, 65, 192, 29);
		contentPane.add(btnObtenerDatos);

		JButton button_1 = new JButton("VOLVER");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GestionProveedores proveedores = new GestionProveedores();
				proveedores.setVisible(true);
				dispose();

			}
		});
		button_1.setBounds(149, 65, 192, 29);
		contentPane.add(button_1);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textField.getText().length() == 0 || textField_1.getText().length() == 0
						|| textField_2.getText().length() == 0 || textField_3.getText().length() == 0
						|| textField_4.getText().length() == 0 || textField_5.getText().length() == 0) {

					JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos", "ERROR",
							JOptionPane.INFORMATION_MESSAGE);

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
					gestor.modificarProveedor(p);

					// JOptionPane.showMessageDialog(null, "Se ha modificado el proveedor
					// correctamente", "INFORMACIÓN",
					// JOptionPane.INFORMATION_MESSAGE);

				}

			}
		});
		btnNewButton.setBounds(355, 253, 192, 29);
		contentPane.add(btnNewButton);
	}

	public static String dateToString(Date fechaEnDate, String formato) {
		if (fechaEnDate == null) {
			return null;
		}
		if (formato == null) {
			formato = "dd/MM/yyyy";
		}
		String fechaEnString = null;
		SimpleDateFormat miFormato = new SimpleDateFormat(formato);
		fechaEnString = miFormato.format(fechaEnDate);
		return fechaEnString;
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
