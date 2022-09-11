package com.uce.edu.demo.repository.modelo;

import java.io.Serializable;

public class VehiculoCampo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String campo;

	public VehiculoCampo() {
		// TODO Auto-generated constructor stub
	}

	public VehiculoCampo(String campo) {
		super();
		this.campo = campo;
	}

	@Override
	public String toString() {
		return "VehiculoCampo [campo=" + campo + "]";
	}

	//SET y GET
	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
