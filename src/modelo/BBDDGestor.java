package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import clases.Proveedor;

public class BBDDGestor {

	Connection conexion;

	public void conectar() {

		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/BBDDProyecto", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void altaProveedor(Proveedor proveedor) {

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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			desconectar();
		}

	}

	public void modificarProveedor(Proveedor proveedor) {

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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			desconectar();
		}

	}

	public Proveedor consultarUnProveedor(String cifaconsultar) {
		Proveedor prov = null;
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
		return prov;
	}

	public void bajaProveedor(String cifqueborro) {

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
