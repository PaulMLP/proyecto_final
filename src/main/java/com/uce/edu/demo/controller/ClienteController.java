package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.VehiculoCampo;
import com.uce.edu.demo.repository.modelo.VehiculoLigero;
import com.uce.edu.demo.service.ClienteGestorServiceImpl;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteGestorServiceImpl clienteGestorServiceImpl;

	@Autowired
	private IVehiculoService vehiculoService;

	@GetMapping("/home")
	public String principal(Model modelo) {
		List<VehiculoCampo> vehiculosMarca = this.vehiculoService.buscarCampos("marca");
		List<VehiculoCampo> vehiculosModelo = this.vehiculoService.buscarCampos("modelo");
		modelo.addAttribute("vehiculosMarca", vehiculosMarca);
		modelo.addAttribute("vehiculosModelo", vehiculosModelo);
		return "home";
	}
	
	@GetMapping("/buscarVehiculos/{marca}/{modelo}")
	public String principal(@PathVariable("marca") String marca, @PathVariable("modelo") String modelo, VehiculoCampo vehiculoCampo , Model model) {
		List<VehiculoLigero> lista = this.vehiculoService.buscarLigero(marca, modelo);
		model.addAttribute("vehiculos",lista);
		return "vehiculos";
	}
}
