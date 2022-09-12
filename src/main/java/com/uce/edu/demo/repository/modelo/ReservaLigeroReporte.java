package com.uce.edu.demo.repository.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ReservaLigeroReporte implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String numero;
	private String estado;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fechaInicio;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fechaFin;
	private String clienteCedula;
	private String clienteNombre;
	private String vehiculoPlaca;
	private String vehiculoModelo;
	private String vehiculoMarca;
	
	public ReservaLigeroReporte() {
		// TODO Auto-generated constructor stub
	}

	public ReservaLigeroReporte(String numero, String estado, LocalDateTime fechaInicio, LocalDateTime fechaFin,
			String clienteCedula, String clienteNombre, String vehiculoPlaca, String vehiculoModelo,
			String vehiculoMarca) {
		this.numero = numero;
		this.estado = estado;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.clienteCedula = clienteCedula;
		this.clienteNombre = clienteNombre;
		this.vehiculoPlaca = vehiculoPlaca;
		this.vehiculoModelo = vehiculoModelo;
		this.vehiculoMarca = vehiculoMarca;
	}
	
	

	@Override
	public String toString() {
		return "ReservaLigeroReporte [numero=" + numero + ", estado=" + estado + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", clienteCedula=" + clienteCedula + ", clienteNombre=" + clienteNombre
				+ ", vehiculoPlaca=" + vehiculoPlaca + ", vehiculoModelo=" + vehiculoModelo + ", vehiculoMarca="
				+ vehiculoMarca + "]";
	}

	//SET Y GET
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDateTime getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}
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
	public String getVehiculoPlaca() {
		return vehiculoPlaca;
	}
	public void setVehiculoPlaca(String vehiculoPlaca) {
		this.vehiculoPlaca = vehiculoPlaca;
	}
	public String getVehiculoModelo() {
		return vehiculoModelo;
	}
	public void setVehiculoModelo(String vehiculoModelo) {
		this.vehiculoModelo = vehiculoModelo;
	}
	public String getVehiculoMarca() {
		return vehiculoMarca;
	}
	public void setVehiculoMarca(String vehiculoMarca) {
		this.vehiculoMarca = vehiculoMarca;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
