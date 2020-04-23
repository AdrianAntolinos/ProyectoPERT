package clases;

import java.util.Date;

public class Proveedor {

	private String cif_proveedor;
	private String raz_proveedor;
	private String reg_notarial;
	private String seg_responsabilidad;
	private double seg_importe;
	private Date fec_homologacion;

	public Proveedor(String cif_proveedor, String raz_proveedor, String reg_notarial, String seg_responsabilidad,
			double seg_importe, Date fec_homologacion) {
		this.cif_proveedor = cif_proveedor;
		this.raz_proveedor = raz_proveedor;
		this.reg_notarial = reg_notarial;
		this.seg_responsabilidad = seg_responsabilidad;
		this.seg_importe = seg_importe;
		this.fec_homologacion = fec_homologacion;
	}

	// GETTERS AND SETTERS

	public String getCif_proveedor() {
		return cif_proveedor;
	}

	public void setCif_proveedor(String cif_proveedor) {
		this.cif_proveedor = cif_proveedor;
	}

	public String getRaz_proveedor() {
		return raz_proveedor;
	}

	public void setRaz_proveedor(String raz_proveedor) {
		this.raz_proveedor = raz_proveedor;
	}

	public String getReg_notarial() {
		return reg_notarial;
	}

	public void setReg_notarial(String reg_notarial) {
		this.reg_notarial = reg_notarial;
	}

	public String getSeg_responsabilidad() {
		return seg_responsabilidad;
	}

	public void setSeg_responsabilidad(String seg_responsabilidad) {
		this.seg_responsabilidad = seg_responsabilidad;
	}

	public double getSeg_importe() {
		return seg_importe;
	}

	public void setSeg_importe(double seg_importe) {
		this.seg_importe = seg_importe;
	}

	public Date getFec_homologacion() {
		return fec_homologacion;
	}

	public void setFec_homologacion(Date fec_homologacion) {
		this.fec_homologacion = fec_homologacion;
	}

}
