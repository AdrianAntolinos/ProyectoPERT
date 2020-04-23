package ventanas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ficheros extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ficheros frame = new Ficheros();
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
	public Ficheros() {
		setTitle("Gestión de ficheros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFicheroJson = new JLabel("CARGAR FICHERO");
		lblFicheroJson.setHorizontalAlignment(SwingConstants.CENTER);
		lblFicheroJson.setBounds(35, 21, 438, 16);
		contentPane.add(lblFicheroJson);

		JButton btnAbrir = new JButton("JSON");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				// Filtro JSON
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.JSON", "json");
				fileChooser.setFileFilter(filtro);
				int seleccion = fileChooser.showOpenDialog(btnAbrir);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fichero = fileChooser.getSelectedFile();
				}

				JOptionPane.showMessageDialog(null, "Se ha cargado el fichero correctamente", "INFORMACIÓN",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});

		btnAbrir.setBounds(35, 49, 438, 29);
		contentPane.add(btnAbrir);

		JButton btnAbrir_1 = new JButton("XML");
		btnAbrir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				// Filtro XML
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.XML", "xml");
				fileChooser.setFileFilter(filtro);
				int seleccion = fileChooser.showOpenDialog(btnAbrir);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fichero = fileChooser.getSelectedFile();
				}

				JOptionPane.showMessageDialog(null, "Se ha cargado el fichero correctamente", "INFORMACIÓN",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});
		btnAbrir_1.setBounds(35, 128, 438, 29);
		contentPane.add(btnAbrir_1);

		JButton btnAbrir_2 = new JButton("BINARIO");
		btnAbrir_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				// Filtro BINARIO
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.BIN", "bin");
				fileChooser.setFileFilter(filtro);
				int seleccion = fileChooser.showOpenDialog(btnAbrir);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fichero = fileChooser.getSelectedFile();
				}

				JOptionPane.showMessageDialog(null, "Se ha cargado el fichero correctamente", "INFORMACIÓN",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});
		btnAbrir_2.setBounds(35, 87, 438, 29);
		contentPane.add(btnAbrir_2);

		JButton btnVolverAlMen = new JButton("VOLVER AL MENÚ");
		btnVolverAlMen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Menu menu = new Menu();
				menu.setVisible(true);
				dispose();

			}
		});
		btnVolverAlMen.setBounds(35, 169, 438, 29);
		contentPane.add(btnVolverAlMen);
	}
}
