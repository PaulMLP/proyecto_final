package com.uce.edu.demo.repository.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class VehiculoVipReporte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String placa;
	private String modelo;
	private String marca;
	private BigDecimal valorSubTotal;
	private BigDecimal valorTotal;

	public VehiculoVipReporte() {
		// TODO Auto-generated constructor stub
	}

	public VehiculoVipReporte(String placa, String modelo, String marca, BigDecimal valorSubTotal,
			BigDecimal valorTotal) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.valorSubTotal = valorSubTotal;
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return "VehiculoVipReporte [placa=" + placa + ", modelo=" + modelo + ", marca=" + marca + ", valorSubTotal="
				+ valorSubTotal + ", valorTotal=" + valorTotal + "]";
	}

	// SET Y GET
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public BigDecimal getValorSubTotal() {
		return valorSubTotal;
	}

	public void setValorSubTotal(BigDecimal valorSubTotal) {
		this.valorSubTotal = valorSubTotal;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}