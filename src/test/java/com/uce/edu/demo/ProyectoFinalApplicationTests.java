package com.uce.edu.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.uce.edu.demo.repository.IClienteRepository;
import com.uce.edu.demo.repository.IReservaRepository;
import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.ClienteVipReporte;
import com.uce.edu.demo.repository.modelo.Cobro;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.ReservaLigeroReporte;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoVipReporte;
import com.uce.edu.demo.service.IClienteGestorService;
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.IVehiculoService;

@SpringBootTest
@Transactional
class ProyectoFinalApplicationTests {

	private static Logger LOG = Logger.getLogger(ProyectoFinalApplicationTests.class);

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IClienteGestorService clienteGestorService;

	@Autowired
	private IReservaRepository reservaRepository;

	@Autowired
	private IClienteRepository clienteRepository;

	@Test
	@Rollback(value = false)
	void insertarVehiculoTest() {
		Vehiculo v = new Vehiculo();
		v.setAnioFabricacion("2007");
		v.setAvaluo(new BigDecimal(15000));
		v.setCilindraje("2000");
		v.setEstado("D");
		v.setMarca("Ford");
		v.setModelo("EcoSport");
		v.setPaisFabricacion("Brasil");
		v.setPlaca("PIT-012");
		v.setReservas(null);
		v.setValorDia(new BigDecimal(50));

		assertEquals(1, this.vehiculoService.insertar(v));
	}

	@Test
	@Rollback(value = false)
	void insertarClienteTest() {

		Cliente c = new Cliente();
		c.setApellido("Parker");
		c.setCedula("123456101");
		c.setFechaNacimiento(LocalDateTime.of(2000, 2, 4, 0, 0));
		c.setGenero("Fluido");
		c.setNombre("Ryuzaki");
		c.setRegistro("E");
		c.setReservas(null);
		assertTrue(this.clienteService.insertar(c));
	}

	@Test
	void vehiculoNuloTest() {
		Vehiculo v = new Vehiculo();
		v.setMarca("Ford");
		boolean a = v != null;
		assertEquals(true, a);
	}

	@Test
	@Rollback(value = true)
	void buscarDisponiblesTest() {
		assertNotEquals(this.clienteGestorService.disponibilidad("PIT-012", "123456101",
				LocalDateTime.of(2022, 10, 1, 0, 0), LocalDateTime.of(2022, 10, 9, 0, 0)), null);
	}

	@Test
	@Rollback(value = true)
	void buscarRegistrarReservaTest() {
		Reserva r = this.clienteGestorService.disponibilidad("PIT-012", "123456101",
				LocalDateTime.of(2022, 10, 1, 0, 0), LocalDateTime.of(2022, 10, 9, 0, 0));
		this.clienteGestorService.registrarReserva(r, "1234654912351458");

	}

	@Test
	@Rollback(value = true)
	void calculoTest() {
		Vehiculo vehiculo = this.vehiculoService.buscarPorPlaca("PIT-012");

		Cobro cobro = this.clienteGestorService.calculoCobro(this.clienteGestorService.compararFechas(
				LocalDateTime.of(2022, 10, 1, 0, 0), LocalDateTime.of(2022, 10, 9, 0, 0)), vehiculo.getValorDia());

		BigDecimal valor = cobro.getValorTotalAPagar();
		LOG.info("Valor calculado " + valor);
		assertNotEquals(valor, null);
	}

	// CASO NO HAY CONFLICTO
	// Fecha consultada antes del rango de reserva
	@Test
	void fechaAntesReservaTest() {
		Reserva reser = new Reserva();
		reser.setFechaInicio(LocalDateTime.of(2022, 8, 10, 0, 0));
		reser.setFechaFin(LocalDateTime.of(2022, 9, 10, 0, 0));
		LocalDateTime fecha1 = LocalDateTime.of(2022, 7, 10, 0, 0);
		LocalDateTime fecha2 = LocalDateTime.of(2022, 8, 9, 0, 0);
		// Se espera que "no haya conflicto" conflicto = false
		assertFalse(this.clienteGestorService.filtroFecha(reser, fecha1, fecha2));
	}

	// CASO SI HAY CONFLICTO
	// Fecha consultada entre del rango de reserva
	@Test
	void fechaEntreReservaTest() {
		Reserva reser = new Reserva();
		reser.setFechaInicio(LocalDateTime.of(2022, 8, 10, 0, 0));
		reser.setFechaFin(LocalDateTime.of(2022, 9, 10, 0, 0));
		LocalDateTime fecha1 = LocalDateTime.of(2022, 7, 10, 0, 0);
		LocalDateTime fecha2 = LocalDateTime.of(2022, 9, 1, 0, 0);
		// Se espera que "haya conflicto" conflicto = true
		assertTrue(this.clienteGestorService.filtroFecha(reser, fecha1, fecha2));
	}

	// CASO NO HAY CONFLICTO
	// Fecha consultada despues del rango de reserva
	@Test
	void fechaDespuesReservaTest() {
		Reserva reser = new Reserva();
		reser.setFechaInicio(LocalDateTime.of(2022, 8, 10, 0, 0));
		reser.setFechaFin(LocalDateTime.of(2022, 9, 10, 0, 0));
		LocalDateTime fecha1 = LocalDateTime.of(2022, 9, 11, 0, 0);
		LocalDateTime fecha2 = LocalDateTime.of(2022, 10, 1, 0, 0);
		// Se espera que "no haya conflicto" conflicto = false
		assertFalse(this.clienteGestorService.filtroFecha(reser, fecha1, fecha2));
	}

	// CASO SI HAY CONFLICTO
	// Fecha final consultada es la misma que la fecha Inicial de reserva
	@Test
	void fechaFinalIgualInicialReservaTest() {
		Reserva reser = new Reserva();
		reser.setFechaInicio(LocalDateTime.of(2022, 8, 10, 0, 0));
		reser.setFechaFin(LocalDateTime.of(2022, 9, 10, 0, 0));
		LocalDateTime fecha1 = LocalDateTime.of(2022, 7, 10, 0, 0);
		LocalDateTime fecha2 = LocalDateTime.of(2022, 8, 10, 0, 0);
		// Se espera que "haya conflicto" conflicto = true
		assertTrue(this.clienteGestorService.filtroFecha(reser, fecha1, fecha2));
	}

	// CASO SI HAY CONFLICTO
	// Fecha inicial consultada es la misma que la fecha final de reserva
	@Test
	void fechaInicialIgualFinalReservaTest() {
		Reserva reser = new Reserva();
		reser.setFechaInicio(LocalDateTime.of(2022, 8, 10, 0, 0));
		reser.setFechaFin(LocalDateTime.of(2022, 9, 10, 0, 0));
		LocalDateTime fecha1 = LocalDateTime.of(2022, 9, 10, 0, 0);
		LocalDateTime fecha2 = LocalDateTime.of(2022, 10, 10, 0, 0);
		// Se espera que "haya conflicto" conflicto = true
		assertTrue(this.clienteGestorService.filtroFecha(reser, fecha1, fecha2));
	}

	@Test
	void reporteReservaTest() {
		LocalDateTime fechaReporte1 = LocalDateTime.of(2022, 9, 1, 0, 0);
		LocalDateTime fechaReport2 = LocalDateTime.of(2022, 10, 30, 0, 0);
		List<ReservaLigeroReporte> listaReservaReporte = this.reservaRepository.reporteReservas(fechaReporte1,
				fechaReport2);
		listaReservaReporte.forEach(r -> LOG.info(r));
		assertNotEquals(listaReservaReporte.size(), 0);
	}

	@Test
	void reporteClienteVIPTest() {
		// Reporte Clientes VIP
		List<ClienteVipReporte> listaClientesVip = this.clienteRepository.buscarClienteReservasPagadas();
		listaClientesVip.forEach(c -> LOG.info(c));
		assertNotEquals(listaClientesVip.size(), 0);
	}

	@Test
	void reporteVehiculosVIPTest() {
		// Reporte Vehiculos VIP
		List<VehiculoVipReporte> listaVehiculosVip = this.vehiculoService.buscarVehiculosVip(10, 2022);
		listaVehiculosVip.forEach(c -> LOG.info(c));

		assertNotEquals(listaVehiculosVip.size(), 0);
	}

	@Test
	void proximaFechaDisponible() {
		Vehiculo v = this.vehiculoService.buscarPorPlaca("PIT-012");
		String a = this.clienteGestorService.fechaDisponible(v, LocalDateTime.of(2022, 10, 2, 0, 0),
				LocalDateTime.of(2022, 10, 8, 0, 0));
		LOG.info(a);

		assertNotNull(a);
	}
	
	
}
