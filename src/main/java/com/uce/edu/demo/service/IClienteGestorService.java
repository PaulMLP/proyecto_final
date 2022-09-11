package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.uce.edu.demo.repository.modelo.Cobro;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;

public interface IClienteGestorService {

	public Reserva disponibilidad(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFin);

	public void registrarReserva(Reserva reserva, String tarjeta);

	public Vehiculo vehiculoFechaReserva(Vehiculo vehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin);

	public boolean filtroFecha(Reserva reserva, LocalDateTime fechaInicio, LocalDateTime fechaFin);

	public int compararFechas(LocalDateTime fecha1, LocalDateTime fecha2);

	public Cobro calculoCobro(int dias, BigDecimal valor);

	public String fechaDisponible(Vehiculo vehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin);

	public boolean datosValidos(String placa, String cedula);
}
