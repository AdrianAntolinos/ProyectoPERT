package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import clases.Proveedor;
import modelo.BBDDGestor;

public class ListadoProveedores extends JFrame {

	private JPanel contentPane;
	Empresa empresa;
	JList list;
	DefaultListModel modelo;
	File fichero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoProveedores frame = new ListadoProveedores();
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
	JButton btnImportarJSON;

	public ListadoProveedores() {
		setTitle("Listado de proveedores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setLayout(null);

		list = new JList();
		list.setBounds(22, 6, 382, 249);
		contentPane.add(list);

		btnImportarJSON = new JButton("IMPORTAR JSON");
		btnImportarJSON.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				importarJSON();

			}
		});
		btnImportarJSON.setBounds(6, 267, 179, 29);
		contentPane.add(btnImportarJSON);

		JButton btnNewButton_1 = new JButton("EXPORTAR JSON");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				exportarJSON();

			}
		});
		btnNewButton_1.setBounds(255, 267, 171, 29);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("IMPORTAR XML");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				importarXML();
			}

		});
		btnNewButton_2.setBounds(6, 308, 184, 29);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("EXPORTAR XML");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				exportarXML();

			}
		});
		btnNewButton_3.setBounds(255, 308, 171, 29);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("VOLVER");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GestionProveedores gp = new GestionProveedores();
				gp.setVisible(true);
				dispose();

			}
		});
		btnNewButton_4.setBounds(6, 349, 420, 29);
		contentPane.add(btnNewButton_4);

		BBDDGestor gestor = new BBDDGestor();

		ArrayList<Proveedor> proveedores = gestor.consultarTodosProveedores();
		empresa = new Empresa();
		empresa.setProveedores(proveedores);

		pintar();

	}

	public void pintar() {
		// pintar proveedores en el ListView
		if (empresa.getProveedores() != null) {

			modelo = new DefaultListModel();
			for (Proveedor pro : empresa.getProveedores()) {
				modelo.addElement(pro.getCif_proveedor() + " : " + pro.getRaz_proveedor());
			}
			list.setModel(modelo);
		}
	}

	public void exportarXML() {

		try {

			JAXBContext contexto = JAXBContext.newInstance(Empresa.class);

			Marshaller miMarshaller = contexto.createMarshaller();

			miMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			miMarshaller.marshal(empresa, new File("JAXEmpresa.xml"));

			miMarshaller.marshal(empresa, System.out);

			JOptionPane.showMessageDialog(null, "Exportado a XML correctamente", "INFORMACIÓN",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void importarXML() {

		try {

			JAXBContext contexto = JAXBContext.newInstance(Empresa.class);

			File file = new File("JAXEmpresa.xml");

			Unmarshaller jaxbUnmarshaller = contexto.createUnmarshaller();

			empresa = (Empresa) jaxbUnmarshaller.unmarshal(file);

			JOptionPane.showMessageDialog(null, "Importado de XML correctamente", "INFORMACIÓN",
					JOptionPane.INFORMATION_MESSAGE);

			pintar();

			// ACTUALIZAR LA BBDD
			BBDDGestor gestor = new BBDDGestor();
			gestor.bajaTodosProveedores();
			for (Proveedor p : empresa.getProveedores()) {
				gestor.altaProveedor(p);
			}

		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void exportarJSON() {

		String textoJSON = empresa.convertirAJson();

		try {
			PrintWriter pw = new PrintWriter("JSONEmpresa.json");
			pw.println(textoJSON);
			pw.close();

			JOptionPane.showMessageDialog(null, "Exportado a JSON correctamente", "INFORMACIÓN",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void leerJSON(File file) {
		File fichero = file;
		BufferedReader br = null;
		String todo = "";
		try {
			// br = new BufferedReader(new FileReader("JSONEmpresa.json"));
			br = new BufferedReader(new FileReader(fichero));
			String linea = br.readLine();

			while (linea != null) {
				todo = todo + linea;
				linea = br.readLine();
			}

			br.close();
			Empresa emo = empresa.recuperarDeJson(todo);
			empresa = emo;

			JOptionPane.showMessageDialog(null, "Importado de JSON correctamente", "INFORMACIÓN",
					JOptionPane.INFORMATION_MESSAGE);

			pintar();
			// ACTUALIZAR LA BBDD
			BBDDGestor gestor = new BBDDGestor();
			gestor.bajaTodosProveedores();
			for (Proveedor p : empresa.getProveedores()) {
				gestor.altaProveedor(p);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void importarJSON() {

		File fichero = null;
		JFileChooser fileChooser = new JFileChooser();
		// Filtro BINARIO
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.JSON", "json");
		fileChooser.setFileFilter(filtro);
		int seleccion = fileChooser.showOpenDialog(btnImportarJSON);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			fichero = fileChooser.getSelectedFile();
		}
		if (fichero != null) {
			leerJSON(fichero);
		}

	}

}
