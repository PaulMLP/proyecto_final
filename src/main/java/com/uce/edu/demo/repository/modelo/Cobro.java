package com.uce.edu.demo.repository.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cobro")
public class Cobro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cobr_seq_id")
	@SequenceGenerator(name = "cobr_seq_id", sequenceName = "cobr_seq_id", allocationSize = 1)
	@Column(name = "cobr_id")
	private Integer id;

	@Column(name = "cobr_valor_subtotal")
	private BigDecimal valorSubtotal;

	@Column(name = "cobr_valor_iva")
	private BigDecimal valorIva;

	@Column(name = "cobr_valor_total")
	private BigDecimal valorTotalAPagar;

	@Column(name = "cobr_fecha_cobro")
	private LocalDateTime fechaCobro;

	@Column(name = "cobr_tarjeta")
	private String tarjeta;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rese_id")
	private Reserva reserva;

	@Override
	public String toString() {
		return "Cobro [id=" + id + ", valorSubtotal=" + valorSubtotal + ", valorIva=" + valorIva + ", valorTotalAPagar="
				+ valorTotalAPagar + ", fechaCobro=" + fechaCobro + ", tarjeta=" + tarjeta + "]";
	}

	// SET y GET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}

	public BigDecimal getValorIva() {
		return valorIva;
	}

	public void setValorIva(BigDecimal valorIva) {
		this.valorIva = valorIva;
	}

	public BigDecimal getValorTotalAPagar() {
		return valorTotalAPagar;
	}

	public void setValorTotalAPagar(BigDecimal valorTotalAPagar) {
		this.valorTotalAPagar = valorTotalAPagar;
	}

	public LocalDateTime getFechaCobro() {
		return fechaCobro;
	}

	public void setFechaCobro(LocalDateTime fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

}
