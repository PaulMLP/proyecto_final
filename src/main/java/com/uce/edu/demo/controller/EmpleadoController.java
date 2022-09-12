package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.IGestorEmpleadoService;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

	private Vehiculo ve;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IGestorEmpleadoService gestorEmpleadoService;

	@Autowired
	private IVehiculoService vehiculoService;

	@GetMapping("/home")
	public String principal(Model modelo) {
		return "inicio";
	}

	@GetMapping("/registra")
	public String registrarCliente(Model modelo) {
		Cliente c = new Cliente();
		modelo.addAttribute("cliente", c);
		return "registroCE";
	}

	@PostMapping("/registrarCliente")
	public String insertarCliente(Cliente cliente) {
		this.gestorEmpleadoService.insertarCliente(cliente);
		return "redirect:/empleados/home";
	}

	@GetMapping("/buscarClientes")
	public String buscarClienteFormulario(Model model) {
		return "busquedaApellido";
	}

	@GetMapping("/cliente")
	public String buscarClientes(@RequestParam String apellido, Model model) {
		List<Cliente> clientes = this.clienteService.buscarClientePorApellido(apellido);
		model.addAttribute("clientes", clientes);
		return "empleadoBuscarClientes";
	}

	// Buscar Cliente
	@GetMapping("/infoCliente/{idCliente}")
	public String traerCliente(@PathVariable("idCliente") Integer idCliente, Model model) {
		Cliente c = this.clienteService.buscarPorId(idCliente);
		model.addAttribute("cliente", c);
		return "infoCliente";
	}

	// Actualizar Cliente
	@GetMapping("/actualizar/{idCliente}")
	public String actualizarClientesFormulario(@PathVariable("idCliente") Integer idCliente, Model model) {
		Cliente c = this.clienteService.buscarPorId(idCliente);
		model.addAttribute("cliente", c);
		return "actualizarCliente";
	}

	@GetMapping("/actualizarCliente/{idCliente}")
	public String actualizarCliente(@PathVariable("idCliente") Integer idCliente, Cliente cliente, Model model) {
		cliente.setId(idCliente);
		this.clienteService.actualizar(cliente);
		return "redirect:/empleados/buscarClientes";
	}

	// Eliminar Cliente
	@GetMapping("/eliminar/{idCliente}")
	public String eliminarClientesFormulario(@PathVariable("idCliente") Integer idCliente, Model model) {
		this.clienteService.eliminar(idCliente);
		return "redirect:/empleados/buscarClientes";
	}

	//////
	@GetMapping("/listarMarca")
	public String listarVehiculoMarca(Model modelo, @RequestParam("marca") String marca) {
		List<Vehiculo> vehiculos = this.vehiculoService.buscarMarca(marca);
		modelo.addAttribute("listaVehiculos", vehiculos);
		return "listaVehiculos";
	}

	@PostMapping
	public String insertarVehiculo(Vehiculo vehiculo, Model modelo) {
		this.vehiculoService.insertar(vehiculo);
		modelo.addAttribute("ingresarVehiculo", new Vehiculo());
		return "redirect:/empleados/buscarVehiculoMarca";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model modelo) {
		modelo.addAttribute("ingresarVehiculo", new Vehiculo());
		return "crearVehiculo";
	}


	@GetMapping("/actualizar/vehiculo/{placaVehiculo}")
	public String actualizarVehiculo(@PathVariable("placaVehiculo") String placa, Model modelo) {
		Vehiculo vehiculo = this.vehiculoService.buscarPorPlaca(placa);
		this.ve = vehiculo;
		modelo.addAttribute("vehiculoEncontrado", this.ve);
		modelo.addAttribute("actualizarV", new Vehiculo());

		return "actualizarVehiculos";
	}

	@GetMapping("/{placaVehiculo}")
	public String actualizarVehiculos(@PathVariable("placaVehiculo") String placa, Vehiculo vehiculo) {
		vehiculo.setPlaca(placa);
		vehiculo.setId(this.ve.getId());
		this.vehiculoService.actualizar(vehiculo);
		return "redirect:/empleados/buscarVehiculoMarca";

	}
	
	@GetMapping("/buscarVehiculoMarca")
	public String buscarMarca(Model modelo) {
		return "vistVehiculo";
	}

	@GetMapping("/borrar/{id}")
	public String borrarVehiculoId(@PathVariable("id") Integer id) {
		this.vehiculoService.eliminar(id);
		return "redirect:/empleados/listaVehiculos";
	}
}
