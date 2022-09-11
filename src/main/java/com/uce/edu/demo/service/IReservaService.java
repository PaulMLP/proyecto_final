package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.ReservaLigeroReporte;

public interface IReservaService {

	public void reservar(Reserva reserva);

	public List<ReservaLigeroReporte> reporteReservas(LocalDateTime fechaIncio, LocalDateTime fechaFin);

	public void insertarReserva(Reserva reserva);

	public void actualizarReserva(Reserva reserva);

	public Reserva buscarPorNumero(String numero);

	public void eliminarReservaPorId(Integer id);

}
