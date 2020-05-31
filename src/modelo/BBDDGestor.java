package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import clases.Factura;
import clases.Proveedor;

public class BBDDGestor {

	Connection conexion;

	public boolean sePuede = true;

	public void conectar() {

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BBDDProyecto", "root", "");
			// sePuede = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("BBDD NO DISPONIBLE");
			e.getMessage();
		}
	}

	public void altaProveedor(Proveedor proveedor) {

		if (sePuede) {
			conectar();
			String sql = "INSERT INTO PROV_COMP VALUES (?,?,?,?,?,?)";
			try {
				PreparedStatement instruccion = conexion.prepareStatement(sql);

				instruccion.setString(1, proveedor.getCif_proveedor());
				instruccion.setString(2, proveedor.getRaz_proveedor());
				instruccion.setString(3, proveedor.getReg_notarial());
				instruccion.setString(4, proveedor.getSeg_responsabilidad());
				instruccion.setDouble(5, proveedor.getSeg_importe());
				java.util.Date fecha = proveedor.getFec_homologacion();
				java.sql.Date fechasql = date_UTIL_to_SQL(fecha);
				instruccion.setDate(6, fechasql);

				instruccion.executeUpdate();

				JOptionPane.showMessageDialog(null, "Se ha dado de alta al proveedor correctamente", "INFORMACIÓN",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				desconectar();
			}
		} else {
			JOptionPane.showMessageDialog(null, "[ERROR]: No se ha podido conectar con la base de datos", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void ValidarNUMFACTURA(String numerofactura) {

		conectar();
		String sql = "SELECT * FROM FACT_PROV WHERE NUM_FACTURA = ?";
		try {

			PreparedStatement instruccion = conexion.prepareStatement(sql);

			instruccion.setString(1, numerofactura);

			ResultSet filas = instruccion.executeQuery();

			if (filas.next()) {
				// se encuentra
				JOptionPane.showMessageDialog(null,
						"Imposible insertar la factura: " + numerofactura + " porque ya existe.", "INFORMACION",
						JOptionPane.INFORMATION_MESSAGE);

			}

			else {
				// no se encuentra
				JOptionPane.showMessageDialog(null, "Se ha creado la factura correctamente", "INFORMACIÓN",
						JOptionPane.INFORMATION_MESSAGE);
			}

			filas.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void ValidarSIHAYPROV(String cifproveedor) {

		conectar();
		String sql = "SELECT * FROM PROV_COMP WHERE CIF_PROVEEDOR = ?";
		try {

			PreparedStatement instruccion = conexion.prepareStatement(sql);

			instruccion.setString(1, cifproveedor);

			ResultSet filas = instruccion.executeQuery();

			if (!filas.next()) {
				// no se encuentra
				JOptionPane.showMessageDialog(null, "El proveedor con CIF: " + cifproveedor + " no se ha encontrado.",
						"INFORMACION", JOptionPane.ERROR_MESSAGE);

			}

			filas.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void altaFactura(Factura factura) {

		conectar();
		String sql = "INSERT INTO FACT_PROV VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement instruccion = conexion.prepareStatement(sql);

			instruccion.setString(1, factura.getCif_proveedor());
			instruccion.setString(2, factura.getRaz_proveedor());
			instruccion.setString(3, factura.getNum_factura());
			instruccion.setString(4, factura.getDes_factura());
			instruccion.setDouble(5, factura.getBas_imponible());
			instruccion.setDouble(6, factura.getIva_importe());
			instruccion.setDouble(7, factura.getTot_importe());
			java.util.Date fecha = factura.getFec_factura();
			java.sql.Date fechasql = date_UTIL_to_SQL(fecha);
			instruccion.setDate(8, fechasql);
			java.util.Date fecha2 = factura.getFec_vencimiento();
			java.sql.Date fechasql2 = date_UTIL_to_SQL(fecha2);
			instruccion.setDate(9, fechasql2);

			instruccion.executeUpdate();

			ValidarNUMFACTURA(factura.getNum_factura());
			ValidarSIHAYPROV(factura.getCif_proveedor());

		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
			// e.getMessage();
			ValidarNUMFACTURA(factura.getNum_factura());
			ValidarSIHAYPROV(factura.getCif_proveedor());

		} finally {
			desconectar();
		}

	}

	public void modificarProveedor(Proveedor proveedor) {

		if (sePuede) {
			conectar();
			String sql = "UPDATE PROV_COMP SET  RAZ_PROVEEDOR = ? , REG_NOTARIAL=? ,SEG_RESPONSABILIDAD= ?"
					+ ",SEG_IMPORTE=?, FEC_HOMOLOGACIÓN=? WHERE CIF_PROVEEDOR = ? ";
			try {
				PreparedStatement instruccion = conexion.prepareStatement(sql);

				instruccion.setString(1, proveedor.getRaz_proveedor());
				instruccion.setString(2, proveedor.getReg_notarial());
				instruccion.setString(3, proveedor.getSeg_responsabilidad());
				instruccion.setDouble(4, proveedor.getSeg_importe());
				java.util.Date fecha = proveedor.getFec_homologacion();
				java.sql.Date fechasql = date_UTIL_to_SQL(fecha);
				instruccion.setDate(5, fechasql);
				instruccion.setString(6, proveedor.getCif_proveedor());

				instruccion.executeUpdate();

				JOptionPane.showMessageDialog(null, "Se ha modificado el proveedor correctamente", "INFORMACIÓN",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				e.getMessage();
			} finally {
				desconectar();
			}
		} else {
			JOptionPane.showMessageDialog(null, "[ERROR]: No se ha podido conectar con la base de datos", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public Proveedor consultarUnProveedor(String cifaconsultar) {
		Proveedor prov = null;

		if (sePuede) {
			conectar();
			String sql = "SELECT * FROM PROV_COMP WHERE CIF_PROVEEDOR = ?";
			try {
				PreparedStatement instruccion = conexion.prepareStatement(sql);

				instruccion.setString(1, cifaconsultar);

				ResultSet filas = instruccion.executeQuery();

				if (filas.next()) {

					String raz = filas.getString("RAZ_PROVEEDOR");
					String reg = filas.getString("REG_NOTARIAL");
					String segresponsabilidad = filas.getString("SEG_RESPONSABILIDAD");
					double segimporte = filas.getDouble("SEG_IMPORTE");
					java.sql.Date fecha = filas.getDate("FEC_HOMOLOGACIÓN");
					java.util.Date fechautil = date_SQL_to_UTIL(fecha);
					prov = new Proveedor(cifaconsultar, raz, reg, segresponsabilidad, segimporte, fechautil);

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				desconectar();
			}

		}

		else {
			JOptionPane.showMessageDialog(null, "[ERROR]: No se ha podido conectar con la base de datos", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
		return prov;

	}

	public ArrayList<Proveedor> consultarTodosProveedores() {
		ArrayList<Proveedor> proveedores = new ArrayList<>();
		conectar();
		String sql = "SELECT * FROM PROV_COMP";
		try {
			PreparedStatement instruccion = conexion.prepareStatement(sql);
			ResultSet filas = instruccion.executeQuery();

			while (filas.next()) {
				String cifaconsultar = filas.getString("CIF_PROVEEDOR");
				String raz = filas.getString("RAZ_PROVEEDOR");
				String reg = filas.getString("REG_NOTARIAL");
				String segresponsabilidad = filas.getString("SEG_RESPONSABILIDAD");
				double segimporte = filas.getDouble("SEG_IMPORTE");
				java.sql.Date fecha = filas.getDate("FEC_HOMOLOGACIÓN");
				java.util.Date fechautil = date_SQL_to_UTIL(fecha);
				Proveedor prov = new Proveedor(cifaconsultar, raz, reg, segresponsabilidad, segimporte, fechautil);
				proveedores.add(prov);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			desconectar();
		}
		return proveedores;
	}

	public ArrayList<Factura> consultarTodasFacturas() {
		ArrayList<Factura> facturas = new ArrayList<>();
		conectar();
		String sql = "SELECT * FROM FACT_PROV";
		try {
			PreparedStatement instruccion = conexion.prepareStatement(sql);
			ResultSet filas = instruccion.executeQuery();

			while (filas.next()) {
				String cifaconsultar = filas.getString("CIF_PROVEEDOR");
				String raz = filas.getString("RAZ_PROVEEDOR");
				String numfact = filas.getString("NUM_FACTURA");
				String desfact = filas.getString("DES_FACTURA");
				double basimp = filas.getDouble("BAS_IMPONIBLE");
				double ivaimp = filas.getDouble("IVA_IMPORTE");
				double totimp = filas.getDouble("TOT_IMPORTE");

				java.sql.Date fechafact = filas.getDate("FEC_FACTURA");
				java.util.Date fechautil = date_SQL_to_UTIL(fechafact);

				java.sql.Date fechavenc = filas.getDate("FEC_VENCIMIENTO");
				java.util.Date fechautil1 = date_SQL_to_UTIL(fechavenc);
				Factura f = new Factura(cifaconsultar, raz, numfact, desfact, basimp, ivaimp, totimp, fechautil,
						fechautil1);
				facturas.add(f);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			desconectar();
		}
		return facturas;
	}

	public void bajaProveedor(String cifqueborro) {

		if (sePuede) {
			conectar();
			String sql = "DELETE FROM PROV_COMP WHERE CIF_PROVEEDOR = ?";
			try {
				PreparedStatement instruccion = conexion.prepareStatement(sql);

				instruccion.setString(1, cifqueborro);

				int i = instruccion.executeUpdate();
				if (i != 1) {
					JOptionPane.showMessageDialog(null, "No hay ningún proveedor con ese cif ", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Se ha borrado el proveedor correctamente", "INFORMACIÓN",
							JOptionPane.INFORMATION_MESSAGE);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				desconectar();
			}
		} else {
			JOptionPane.showMessageDialog(null, "[ERROR]: No se ha podido conectar con la base de datos", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void bajaTodosProveedores() {

		conectar();
		String sql = "DELETE FROM PROV_COMP";
		try {
			PreparedStatement instruccion = conexion.prepareStatement(sql);
			int i = instruccion.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			desconectar();
		}

	}

	public void bajaTodasFacturas() {
		conectar();
		String sql = "DELETE FROM FACT_PROV";
		try {
			PreparedStatement instruccion = conexion.prepareStatement(sql);
			int i = instruccion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconectar();
		}
	}

	public void desconectar() {

		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static java.sql.Date date_UTIL_to_SQL(java.util.Date fechaEnUtil) {
		java.sql.Date fechaEnSql = new java.sql.Date(fechaEnUtil.getTime());
		return fechaEnSql;
	}

	public static java.util.Date date_SQL_to_UTIL(java.sql.Date fechaEnSql) {
		if (fechaEnSql == null) {
			return null;
		}
		java.util.Date fechaEnUtil = null;

		fechaEnUtil = new java.util.Date(fechaEnSql.getTime());

		return fechaEnUtil;
	}

}
