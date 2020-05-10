package clases;

import java.io.Serializable;
import java.util.Date;

public class Factura implements Serializable {

	private String cif_proveedor;

	private String raz_proveedor;

	private String num_factura;

	private String des_factura;

	private double bas_imponible;

	private double iva_importe;

	private double tot_importe;

	private Date fec_factura;

	private Date fec_vencimiento;

	public Factura(String cif_proveedor, String raz_proveedor, String num_factura, String des_factura,
			double bas_imponible, double iva_importe, double tot_importe, Date fec_factura, Date fec_vencimiento) {
		this.cif_proveedor = cif_proveedor;
		this.raz_proveedor = raz_proveedor;
		this.num_factura = num_factura;
		this.des_factura = des_factura;
		this.bas_imponible = bas_imponible;
		this.iva_importe = iva_importe;
		this.tot_importe = tot_importe;
		this.fec_factura = fec_factura;
		this.fec_vencimiento = fec_vencimiento;
	}

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

	public String getNum_factura() {
		return num_factura;
	}

	public void setNum_factura(String num_factura) {
		this.num_factura = num_factura;
	}

	public String getDes_factura() {
		return des_factura;
	}

	public void setDes_factura(String des_factura) {
		this.des_factura = des_factura;
	}

	public double getBas_imponible() {
		return bas_imponible;
	}

	public void setBas_imponible(double bas_imponible) {
		this.bas_imponible = bas_imponible;
	}

	public double getIva_importe() {
		return iva_importe;
	}

	public void setIva_importe(double iva_importe) {
		this.iva_importe = iva_importe;
	}

	public double getTot_importe() {
		return tot_importe;
	}

	public void setTot_importe(double tot_importe) {
		this.tot_importe = tot_importe;
	}

	public Date getFec_factura() {
		return fec_factura;
	}

	public void setFec_factura(Date fec_factura) {
		this.fec_factura = fec_factura;
	}

	public Date getFec_vencimiento() {
		return fec_vencimiento;
	}

	public void setFec_vencimiento(Date fec_vencimiento) {
		this.fec_vencimiento = fec_vencimiento;
	}

}
