package com.uce.edu.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uce.edu.demo.repository.modelo.ClienteVipReporte;
import com.uce.edu.demo.repository.modelo.ReservaLigeroReporte;
import com.uce.edu.demo.repository.modelo.VehiculoVipReporte;
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.IReservaService;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

	@Autowired
	private IReservaService reservaService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/reservaReporte")
	public String reporteFechas(ReservaLigeroReporte reporte, VehiculoVipReporte vehiculoVipReporte,
			ClienteVipReporte clienteVipReporte, Model modelo) {
		return "menuReportes";
	}

	@GetMapping("/reporteReservas")
	public String reporteReservas(
			@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
			@RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin,
			Model model) {
		List<ReservaLigeroReporte> lista = this.reservaService.reporteReservas(fechaInicio, fechaFin);
		model.addAttribute("reservas", lista);
		return "reservaReporte";
	}

	@GetMapping("/reporteClientes")
	public String reporteClientes(Model model) {
		List<ClienteVipReporte> lista = this.clienteService.buscarClienteReservasPagadas();
		model.addAttribute("clientes", lista);
		return "clienteReporte";
	}

	@GetMapping("/reporteVehiculos")
	public String reporteVehiculos(@RequestParam("fecha") String fecha, Model model) {
		System.out.println("que wea es la fecha..." + fecha.getClass());
		List<VehiculoVipReporte> lista = this.vehiculoService.buscarVehiculosVip(fecha);
		model.addAttribute("vehiculos", lista);
		return "vehiculoReporte";
	}

}
