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

import clases.Factura;
import modelo.BBDDGestor;

public class AltaFactura extends JFrame {

	private JPanel contentPane;
	private JTextField txtcif;
	private JTextField txtraz;
	private JTextField txtnum;
	private JTextField txtdescrip;
	private JTextField txtbase;
	private JTextField txtiva;
	private JLabel lblBaseImponible;
	private JLabel lblTotalImporte;
	private JTextField txttotal;
	private JLabel lblNewLabel;
	private JTextField txtfechafactura;
	private JLabel lblFechaVencimiento;
	private JTextField txtfechavencimiento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaFactura frame = new AltaFactura();
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
	public AltaFactura() {
		setTitle("Crear factura");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("CIF DEL PROVEEDOR:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(109, 21, 159, 16);
		contentPane.add(label);

		JLabel lblNmeroFactura = new JLabel("NÚMERO FACTURA:");
		lblNmeroFactura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNmeroFactura.setBounds(20, 77, 248, 16);
		contentPane.add(lblNmeroFactura);

		JLabel label_2 = new JLabel("RAZÓN SOCIAL DEL PROVEEDOR:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(10, 49, 258, 16);
		contentPane.add(label_2);

		JLabel lblIvaImporte = new JLabel("IVA IMPORTE:");
		lblIvaImporte.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIvaImporte.setBounds(48, 161, 220, 16);
		contentPane.add(lblIvaImporte);

		JLabel lblDescripcinDeLa = new JLabel("DESCRIPCIÓN DE LA FACTURA:");
		lblDescripcinDeLa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcinDeLa.setBounds(-47, 105, 315, 16);
		contentPane.add(lblDescripcinDeLa);

		txtcif = new JTextField();
		txtcif.setColumns(10);
		txtcif.setBounds(280, 16, 192, 26);
		contentPane.add(txtcif);

		txtraz = new JTextField();
		txtraz.setColumns(10);
		txtraz.setBounds(280, 44, 192, 26);
		contentPane.add(txtraz);

		txtnum = new JTextField();
		txtnum.setColumns(10);
		txtnum.setBounds(280, 72, 192, 26);
		contentPane.add(txtnum);

		txtdescrip = new JTextField();
		txtdescrip.setColumns(10);
		txtdescrip.setBounds(280, 100, 192, 26);
		contentPane.add(txtdescrip);

		txtbase = new JTextField();
		txtbase.setColumns(10);
		txtbase.setBounds(280, 128, 192, 26);
		contentPane.add(txtbase);

		txtiva = new JTextField();
		txtiva.setColumns(10);
		txtiva.setBounds(280, 156, 192, 26);
		contentPane.add(txtiva);

		JButton btnCrearFactura = new JButton("CREAR FACTURA");
		btnCrearFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtcif.getText().length() == 0 || txtraz.getText().length() == 0 || txtnum.getText().length() == 0
						|| txtdescrip.getText().length() == 0 || txtbase.getText().length() == 0
						|| txtiva.getText().length() == 0 || txttotal.getText().length() == 0
						|| txtfechafactura.getText().length() == 0 || txtfechavencimiento.getText().length() == 0) {

					JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos para crear la factura",
							"ERROR", JOptionPane.ERROR_MESSAGE);

				} else {

					String cif = txtcif.getText();
					String razonsocial = txtraz.getText();
					String numfactura = txtnum.getText();
					String descripcion = txtdescrip.getText();
					Double base = Double.parseDouble(txtbase.getText());
					Double iva = Double.parseDouble(txtiva.getText());
					Double total = Double.parseDouble(txttotal.getText());
					Date fechafactura = stringToDate(txtfechafactura.getText(), null);
					Date fechavencimiento = stringToDate(txtfechavencimiento.getText(), null);

					Factura f = new Factura(cif, razonsocial, numfactura, descripcion, base, iva, total, fechafactura,
							fechavencimiento);

					BBDDGestor gestor = new BBDDGestor();
					gestor.altaFactura(f);

					// JOptionPane.showMessageDialog(null, "Se ha creado la factura correctamente",
					// "INFORMACIÓN",
					// JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		btnCrearFactura.setBounds(280, 278, 192, 29);
		contentPane.add(btnCrearFactura);

		JButton button_1 = new JButton("VOLVER");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GestionFacturas gf = new GestionFacturas();
				gf.setVisible(true);
				dispose();
			}
		});
		button_1.setBounds(96, 278, 192, 29);
		contentPane.add(button_1);

		lblBaseImponible = new JLabel("BASE IMPONIBLE:");
		lblBaseImponible.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBaseImponible.setBounds(109, 133, 159, 16);
		contentPane.add(lblBaseImponible);

		lblTotalImporte = new JLabel("TOTAL IMPORTE:");
		lblTotalImporte.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalImporte.setBounds(36, 189, 232, 16);
		contentPane.add(lblTotalImporte);

		txttotal = new JTextField();
		txttotal.setBounds(280, 184, 189, 26);
		contentPane.add(txttotal);
		txttotal.setColumns(10);

		lblNewLabel = new JLabel("FECHA FACTURA:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(109, 217, 159, 16);
		contentPane.add(lblNewLabel);

		txtfechafactura = new JTextField();
		txtfechafactura.setBounds(280, 212, 189, 26);
		contentPane.add(txtfechafactura);
		txtfechafactura.setColumns(10);

		lblFechaVencimiento = new JLabel("FECHA VENCIMIENTO:");
		lblFechaVencimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaVencimiento.setBounds(86, 245, 182, 16);
		contentPane.add(lblFechaVencimiento);

		txtfechavencimiento = new JTextField();
		txtfechavencimiento.setBounds(280, 240, 189, 26);
		contentPane.add(txtfechavencimiento);
		txtfechavencimiento.setColumns(10);
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
