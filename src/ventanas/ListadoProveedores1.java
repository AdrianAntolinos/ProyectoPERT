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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import clases.Proveedor;
import modelo.BBDDGestor;

public class ListadoProveedores1 extends JFrame {

	private JPanel contentPane;
	Empresa empresa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoProveedores1 frame = new ListadoProveedores1();
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
	public ListadoProveedores1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setLayout(null);

		JList list = new JList();
		list.setBounds(22, 6, 382, 249);
		contentPane.add(list);

		JButton btnNewButton = new JButton("IMPORTAR JSON");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				importarJSON();

			}
		});
		btnNewButton.setBounds(42, 267, 179, 29);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("EXPORTAR JSON");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				exportarJSON();

			}
		});
		btnNewButton_1.setBounds(233, 267, 171, 29);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("IMPORTAR XML");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				importarXML();
			}

		});
		btnNewButton_2.setBounds(42, 308, 184, 29);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("EXPORTAR XML");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				exportarXML();

			}
		});
		btnNewButton_3.setBounds(233, 308, 171, 29);
		contentPane.add(btnNewButton_3);

		BBDDGestor gestor = new BBDDGestor();

		ArrayList<Proveedor> proveedores = gestor.consultarTodosProveedores();
		empresa = new Empresa();
		empresa.setProveedores(proveedores);

		// pintar el listado en el JTable

	}

	public void exportarXML() {

		try {
			// exportamos el objeto empresa a XML

			JAXBContext contexto = JAXBContext.newInstance(Empresa.class);

			Marshaller miMarshaller = contexto.createMarshaller();

			miMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			miMarshaller.marshal(empresa, new File("JAXEmpresa.xml"));

			miMarshaller.marshal(empresa, System.out); // vemos lo mismo por consola

			JOptionPane.showMessageDialog(null, "Exportado a XML correctamente", "OK", JOptionPane.INFORMATION_MESSAGE);

		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void importarXML() {

		try {
			// exportamos el objeto empresa a XML

			JAXBContext contexto = JAXBContext.newInstance(Empresa.class);

			File file = new File("JAXEmpresa.xml");

			Unmarshaller jaxbUnmarshaller = contexto.createUnmarshaller();
			// la solucion del unmarshall es un object, se necesita casting para convertirlo
			// en empresa
			empresa = (Empresa) jaxbUnmarshaller.unmarshal(file);

			JOptionPane.showMessageDialog(null, "Importado de XML correctamente", "OK",
					JOptionPane.INFORMATION_MESSAGE);

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

			JOptionPane.showMessageDialog(null, "Exportado a JSON correctamente", "OK",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void importarJSON() {
		BufferedReader br = null;
		String todo = "";
		try {
			br = new BufferedReader(new FileReader("JSONEmpresa.json"));

			todo = br.readLine();
			br.close();
			Empresa emo = empresa.recuperarDeJson(todo);
			empresa = emo;

			JOptionPane.showMessageDialog(null, "Importado de JSON correctamente", "OK",
					JOptionPane.INFORMATION_MESSAGE);
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

}
