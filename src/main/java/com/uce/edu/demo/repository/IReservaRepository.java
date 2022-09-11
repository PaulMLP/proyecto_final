package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.ReservaLigeroReporte;

public interface IReservaRepository {

	public void insertar(Reserva reserva);

	public Reserva buscar(Integer id);

	public List<ReservaLigeroReporte> reporteReservas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

	public void actualizarReserva(Reserva reserva);

	public Reserva buscarPorNumero(String numeroReserva);

	public void eliminarReservaPorId(Integer id);

}
