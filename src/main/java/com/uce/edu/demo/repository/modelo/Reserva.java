package com.uce.edu.demo.repository.modelo;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rese_seq_id")
	@SequenceGenerator(name = "rese_seq_id", sequenceName = "rese_seq_id", allocationSize = 1)
	@Column(name = "rese_id")
	private Integer id;

	@Column(name = "rese_fecha_inicio")
	private LocalDateTime fechaInicio;

	@Column(name = "rese_fecha_fin")
	private LocalDateTime fechaFin;

	@Column(name = "rese_estado")
	private String estado;
	
	@Column(name = "rese_numero")
	private String numero;

	@OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL)
	private Cobro cobro;

	@ManyToOne
	@JoinColumn(name = "clie_id")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "vehi_id")
	private Vehiculo vehiculo;


	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", estado=" + estado
				+ ", numero=" + numero + ", cobro=" + cobro + ", cliente=" + cliente + ", vehiculo=" + vehiculo + "]";
	}

	// SET y GET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cobro getCobro() {
		return cobro;
	}

	public void setCobro(Cobro cobro) {
		this.cobro = cobro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
