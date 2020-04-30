package ventanas;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;

import clases.Proveedor;

@XmlRootElement
public class Empresa {

	private ArrayList<Proveedor> proveedores;

	public ArrayList<Proveedor> getProveedores() {
		return proveedores;
	}

	@XmlElementWrapper(name = "ListaProveedores")
	@XmlElement(name = "Proveedor")
	public void setProveedores(ArrayList<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public String convertirAJson() {
		Gson unGson = new Gson();
		String cadenaEnFormatoGSON = unGson.toJson(this);
		return cadenaEnFormatoGSON;
	}

	public Empresa recuperarDeJson(String cadenaEnFormatoGSON) {
		Gson unGson = new Gson();
		Empresa empresa = unGson.fromJson(cadenaEnFormatoGSON, Empresa.class);
		return empresa;
	}

}
