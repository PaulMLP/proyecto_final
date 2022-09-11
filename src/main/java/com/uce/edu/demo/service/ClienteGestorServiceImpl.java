package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IClienteRepository;
import com.uce.edu.demo.repository.IReservaRepository;
import com.uce.edu.demo.repository.IVehiculoRepository;
import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Cobro;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;

@Service
public class ClienteGestorServiceImpl implements IClienteGestorService {

	@Autowired
	private IVehiculoRepository vehiculoRepository;

	@Autowired
	private IClienteRepository clienteRepository;

	@Autowired
	private IReservaRepository reservaRepository;


	//1.b
	@Override
	public void registrarReserva(Reserva reserva, String tarjeta) {
		// TODO Auto-generated method stub
		Cobro cobro = reserva.getCobro();
		cobro.setTarjeta(tarjeta);
		reserva.setCobro(cobro);
		reserva.setEstado("G");
		reserva.setNumero(convertir.apply(LocalDateTime.now()));
		this.reservaRepository.insertar(reserva);
	}

	@Override
	public Reserva disponibilidad(String placa, String cedula, LocalDateTime fechaInicio, LocalDateTime fechaFin) {

		Reserva reserva = new Reserva();

		Cliente cliente = this.clienteRepository.buscarClientePorCedula(cedula);
		Vehiculo vehiculo = this.vehiculoRepository.buscarPorPlaca(placa);

		Cobro cobro = calculoCobro(compararFechas(fechaInicio, fechaFin), vehiculo.getValorDia());

		if ((vehiculoFechaReserva(vehiculo, fechaInicio, fechaFin) != null)) {
			reserva.setVehiculo(vehiculo);
			reserva.setCliente(cliente);
			reserva.setCobro(cobro);

			reserva.setFechaInicio(fechaInicio);
			reserva.setFechaFin(fechaFin);
			return reserva;

		} else {
			return null;
		}
	}
	
	@Override
	public Vehiculo vehiculoFechaReserva(Vehiculo vehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin) {

		List<Reserva> reservas = vehiculo.getReservas();
		List<Reserva> reservaConflicto = reservas.parallelStream()
				.filter(reserva -> filtroFecha(reserva, fechaInicio, fechaFin)).collect(Collectors.toList());

		if (reservaConflicto.size() == 0) {
			// No hay reservas en esas fechas
			return vehiculo;
		} else {
			return null;
		}

	}

	@Override
	public String fechaDisponible(Vehiculo vehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin) {

		List<Reserva> reservas = vehiculo.getReservas();
		List<Reserva> reservaConflicto = reservas.parallelStream()
				.filter(reserva -> filtroFecha(reserva, fechaInicio, fechaFin)).collect(Collectors.toList());

		if (reservaConflicto.size() != 0) {
			List<Reserva> reservasOrden = reservaConflicto.stream()
					.sorted(Comparator.comparing(Reserva::getFechaFin).reversed()).collect(Collectors.toList());
			return reservasOrden.get(0).getFechaFin().plusDays(1).toString();
		} else {
			return null;
		}

	}

	@Override
	public boolean filtroFecha(Reserva reserva, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		boolean conflicto = true;
		conflicto = (fechaInicio.isBefore(reserva.getFechaInicio()) && fechaFin.isBefore(reserva.getFechaInicio()))
				|| (fechaInicio.isAfter(reserva.getFechaFin()) && fechaFin.isAfter(reserva.getFechaFin()));

		return !conflicto;
	}

	@Override
	public int compararFechas(LocalDateTime fecha1, LocalDateTime fecha2) {

		Duration diff = Duration.between(fecha1, fecha2);
		int diffDays = (int) diff.toDays();
		return diffDays;
	}

	@Override
	public Cobro calculoCobro(int dias, BigDecimal valor) {

		Cobro cobro = new Cobro();

		BigDecimal subtotal = valor.multiply(new BigDecimal(dias));

		BigDecimal valorIva = subtotal.multiply(new BigDecimal(12));
		valorIva = valorIva.divide(new BigDecimal(100));

		cobro.setValorSubtotal(subtotal);
		cobro.setValorIva(valorIva);
		cobro.setValorTotalAPagar(valor.add(valorIva));

		return cobro;
	}

	Function<LocalDateTime, String> convertir = t -> {
		StringBuilder fechaCod = new StringBuilder();
		fechaCod.append(t.getYear()).append(t.getMonthValue()).append(t.getDayOfMonth()).append(t.getHour())
				.append(t.getMinute());

		return fechaCod.toString();
	};

}
