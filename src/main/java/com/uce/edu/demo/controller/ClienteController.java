package com.uce.edu.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoCampo;
import com.uce.edu.demo.repository.modelo.VehiculoLigero;
import com.uce.edu.demo.service.ClienteGestorServiceImpl;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	private static Logger LOG = Logger.getLogger(ClienteController.class);

	@Autowired
	private ClienteGestorServiceImpl clienteGestorServiceImpl;

	@Autowired
	private IVehiculoService vehiculoService;

	private Reserva reserva;
	
	@GetMapping("/home")
	public String principal(Model modelo) {
		List<VehiculoCampo> vehiculosMarca = this.vehiculoService.buscarCampos("marca");
		List<VehiculoCampo> vehiculosModelo = this.vehiculoService.buscarCampos("modelo");
		modelo.addAttribute("vehiculosMarca", vehiculosMarca);
		modelo.addAttribute("vehiculosModelo", vehiculosModelo);
		return "home";
	}

	@GetMapping("/buscar/vehiculos")
	public String resultados(@RequestParam("marca") String marca, @RequestParam("modelo") String modelo, Model model) {
		List<VehiculoLigero> lista = this.vehiculoService.buscarLigero(marca, modelo);
		model.addAttribute("vehiculos", lista);
		return "vehiculos";
	}

	@GetMapping("/buscar/reserva")
	public String buscarReserva(Model modelo) {
		return "reserva";
	}

	@GetMapping("/buscar/disponible")
	public String buscarDisponible(@RequestParam("placa") String placa, @RequestParam("cedula") String cedula,
			@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
			@RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin,
			Model modelo) {
		// verificar que exista el cliente y el vehiculo
		if (this.clienteGestorServiceImpl.datosValidos(placa, cedula)) {
			Vehiculo v = this.vehiculoService.buscarPorPlaca(placa);
			// verificar que exista una fecha disponible del vehiculo y calcular una
			String mns = this.clienteGestorServiceImpl.fechaDisponible(v, fechaInicio, fechaFin);
			Reserva reserva = this.clienteGestorServiceImpl.disponibilidad(placa, cedula, fechaInicio, fechaFin);
			modelo.addAttribute("reserva",reserva);
			this.reserva = reserva;
			return "registrar";
		} // si no existe el cliente o el vehiculo
		else {
			modelo.addAttribute("reserva", null);
			return "redirect:/clientes/buscar/disponible";
		}

	}

	@PostMapping("/cobro/reserva")
	public String realizarReserva(@RequestParam("tarjeta") String tarjeta) {
		this.clienteGestorServiceImpl.registrarReserva(this.reserva, tarjeta);
		return "redirect:/clientes/home";
	}

}
