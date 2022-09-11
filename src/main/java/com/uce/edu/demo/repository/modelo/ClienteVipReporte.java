package com.uce.edu.demo.repository.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ClienteVipReporte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String clienteCedula;
	private String clienteNombre;
	private String numeroReserva;
	private BigDecimal valorIva;
	private BigDecimal valorTotal;

	public ClienteVipReporte() {
		// TODO Auto-generated constructor stub
	}

	public ClienteVipReporte(String clienteCedula, String clienteNombre, String numeroReserva, BigDecimal valorIva, BigDecimal valorTotal) {
		super();
		this.clienteCedula = clienteCedula;
		this.clienteNombre = clienteNombre;
		this.numeroReserva = numeroReserva;
		this.valorIva = valorIva;
		this.valorTotal = valorTotal;
	}

	@Override
	public String toString() {
		return "ClienteVipReporte [clienteCedula=" + clienteCedula + ", clienteNombre=" + clienteNombre
				+ ", numeroReserva=" + numeroReserva + ", valorIva=" + valorIva + ", valorTotal=" + valorTotal + "]";
	}

	//SET y GET 
	public String getClienteCedula() {
		return clienteCedula;
	}

	public void setClienteCedula(String clienteCedula) {
		this.clienteCedula = clienteCedula;
	}

	public String getClienteNombre() {
		return clienteNombre;
	}

	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public BigDecimal getValorIva() {
		return valorIva;
	}

	public void setValorIva(BigDecimal valorIva) {
		this.valorIva = valorIva;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	
	public String getNumeroReserva() {
		return numeroReserva;
	}

	public void setNumeroReserva(String numeroReserva) {
		this.numeroReserva = numeroReserva;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
