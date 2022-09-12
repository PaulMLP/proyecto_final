package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IReservaRepository;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.ReservaLigeroReporte;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IReservaRepository reservaRepository;

	@Override
	public void reservar(Reserva reserva) {
		this.reservaRepository.insertar(reserva);
	}

	@Override
	public void insertarReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		this.reservaRepository.insertar(reserva);
	}

	@Override
	public void actualizarReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		this.reservaRepository.actualizarReserva(reserva);
	}

	@Override
	public Reserva buscarPorNumero(String numero) {
		// TODO Auto-generated method stub
		return this.reservaRepository.buscarPorNumero(numero);
	}

	@Override
	public void eliminarReservaPorId(Integer id) {
		// TODO Auto-generated method stub
		this.reservaRepository.eliminarReservaPorId(id);
	}

	@Override
	public List<ReservaLigeroReporte> reporteReservas(LocalDateTime fechaIncio, LocalDateTime fechaFin) {
		List<ReservaLigeroReporte> lista = this.reservaRepository.reporteReservas(fechaIncio, fechaFin);
		List<ReservaLigeroReporte> listaCambiada = lista.parallelStream().map(reserva -> {
			if (reserva.getEstado().equalsIgnoreCase("G"))
				reserva.setEstado("Generada");
			else
				reserva.setEstado("Ejecutada");
			return reserva;
		}).collect(Collectors.toList());

		return listaCambiada;
	}

}
