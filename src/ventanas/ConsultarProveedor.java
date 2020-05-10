package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class ConsultarProveedor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel label_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarProveedor frame = new ConsultarProveedor();
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
	public ConsultarProveedor() {
		setTitle("CONSULTAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIntroduceElCif = new JLabel("INTRODUCE EL CIF DEL PROVEEDOR:");
		lblIntroduceElCif.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIntroduceElCif.setBounds(66, 42, 269, 16);
		contentPane.add(lblIntroduceElCif);

		JLabel label_1 = new JLabel("NÚMERO DE REGISTRONOTARIAL:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(87, 144, 248, 16);
		contentPane.add(label_1);
		label_1.setVisible(false);

		JLabel label_2 = new JLabel("RAZÓN SOCIAL DEL PROVEEDOR:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(77, 116, 258, 16);
		contentPane.add(label_2);
		label_2.setVisible(false);

		JLabel label_3 = new JLabel("FECHA DE HOMOLOGACIÓN:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(115, 228, 220, 16);
		contentPane.add(label_3);
		label_3.setVisible(false);

		JLabel label_4 = new JLabel("CÓDIGO DE SEGURO DE RESPONSABILIDAD CIVIL:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(20, 172, 315, 16);
		contentPane.add(label_4);
		label_4.setVisible(false);

		label_5 = new JLabel("IMPORTE DEL SEGURO DE RESPONSABILIDAD CIVIL:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(-34, 200, 369, 16);
		contentPane.add(label_5);
		label_5.setVisible(false);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(347, 37, 192, 26);
		contentPane.add(textField);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(347, 111, 192, 26);
		contentPane.add(textField_1);
		textField_1.setVisible(false);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(347, 139, 192, 26);
		contentPane.add(textField_2);
		textField_2.setVisible(false);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(347, 167, 192, 26);
		contentPane.add(textField_3);
		textField_3.setVisible(false);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(347, 195, 192, 26);
		contentPane.add(textField_4);
		textField_4.setVisible(false);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(347, 223, 192, 26);
		contentPane.add(textField_5);
		textField_5.setVisible(false);

		JButton btnModificar = new JButton("CONSULTAR");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BBDDGestor gestor = new BBDDGestor();

				String cif = textField.getText();
				Proveedor prov = gestor.consultarUnProveedor(cif);

				if (prov != null) {

					textField_1.setText(prov.getRaz_proveedor());
					textField_2.setText(prov.getReg_notarial());
					textField_3.setText(prov.getSeg_responsabilidad());
					textField_4.setText(prov.getSeg_importe() + "");
					textField_5.setText(dateToString(prov.getFec_homologacion(), null));
					label_1.setVisible(true);
					label_2.setVisible(true);
					label_3.setVisible(true);
					label_4.setVisible(true);
					label_5.setVisible(true);
					textField_1.setVisible(true);
					textField_2.setVisible(true);
					textField_3.setVisible(true);
					textField_4.setVisible(true);
					textField_5.setVisible(true);

				} else {

					JOptionPane.showMessageDialog(null, "No se ha encontrado el cif", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		btnModificar.setBounds(347, 70, 192, 29);
		contentPane.add(btnModificar);

		JButton button_1 = new JButton("VOLVER");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GestionProveedores proveedores = new GestionProveedores();
				proveedores.setVisible(true);
				dispose();

			}
		});
		button_1.setBounds(143, 70, 192, 29);
		contentPane.add(button_1);

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

}
