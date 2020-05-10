package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import clases.Factura;
import modelo.BBDDGestor;

public class GestionFacturas extends JFrame {

	private JPanel contentPane;
	ArrayList<Factura> facturas = null;
	private JList list;
	DefaultListModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionFacturas frame = new GestionFacturas();
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
	public GestionFacturas() {
		setTitle("Gestión de facturas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAbrir = new JButton("LECTURA FACTURAS");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File fichero = null;
				JFileChooser fileChooser = new JFileChooser();
				// Filtro BINARIO
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.BIN", "bin");
				fileChooser.setFileFilter(filtro);
				int seleccion = fileChooser.showOpenDialog(btnAbrir);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					fichero = fileChooser.getSelectedFile();
				}
				if (fichero != null) {
					leerFacturasBinario(fichero);
				}

			}
		});
		btnAbrir.setBounds(42, 215, 438, 29);
		contentPane.add(btnAbrir);

		JButton btnVolverAlMen = new JButton("VOLVER AL MENÚ");
		btnVolverAlMen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();

			}
		});
		btnVolverAlMen.setBounds(42, 297, 438, 29);
		contentPane.add(btnVolverAlMen);

		list = new JList();
		list.setBounds(44, 21, 436, 182);
		contentPane.add(list);

		JButton btnGuardar = new JButton("GUARDAR FACTURAS");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarFacturasBinario();
			}
		});
		btnGuardar.setBounds(42, 256, 438, 29);
		contentPane.add(btnGuardar);

		BBDDGestor gestor = new BBDDGestor();

		facturas = gestor.consultarTodasFacturas();

		pintarFacturas();
	}

	public void pintarFacturas() {

		// pintar facturas en el ListView
		if (facturas != null) {
			modelo = new DefaultListModel();
			for (Factura f : facturas) {
				modelo.addElement(f.getCif_proveedor() + " : " + f.getDes_factura());
			}
			list.setModel(modelo);
		}
	}

	public void guardarFacturasBinario() {

		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("backupFacturas.bin");
			oos = new ObjectOutputStream(fos);

			for (Factura f : facturas) {
				oos.writeObject(f);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(null, "Se ha guardado el fichero de facturas correctamente", "INFORMACIÓN",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void leerFacturasBinario(File file) {

		if (file.exists()) {

			facturas.clear();

			FileInputStream fis = null;
			ObjectInputStream ois = null;
			try {
				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);

				while (true) {

					Factura f = (Factura) ois.readObject();
					facturas.add(f);

				}

			} catch (EOFException e) {
				// fin del fichero
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (ois != null) {
						ois.close();
					}
					if (fis != null) {
						fis.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			pintarFacturas();

			// ACTUALIZAR LA BBDD
			BBDDGestor gestor = new BBDDGestor();
			gestor.bajaTodasFacturas();
			for (Factura f : facturas) {
				gestor.altaFactura(f);
			}
			JOptionPane.showMessageDialog(null, "Se ha leido el fichero de facturas correctamente", "INFORMACIÓN",
					JOptionPane.INFORMATION_MESSAGE);

		} else {
			JOptionPane.showMessageDialog(null, "Fichero de facturas NO ENCONTRADO", "ERROR",
					JOptionPane.ERROR_MESSAGE);

		}

	}
}
