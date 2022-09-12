package com.uce.edu.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoCampo;
import com.uce.edu.demo.repository.modelo.VehiculoLigero;
import com.uce.edu.demo.service.ClienteGestorServiceImpl;
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.IGestorEmpleadoService;
import com.uce.edu.demo.service.IReservaService;
import com.uce.edu.demo.service.IVehiculoService;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

	private Vehiculo ve;
	private Cliente cliente;
	private Reserva reserva;

	@Autowired
	private ClienteGestorServiceImpl clienteGestorServiceImpl;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IGestorEmpleadoService gestorEmpleadoService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IReservaService reservaService;

	@GetMapping("/home")
	public String principal(Model modelo) {
		return "inicio";
	}

	// Redirecciones
	@GetMapping("/administrarCliente")
	public String adminClientes(Model modelo) {
		return "administrarCliente";
	}

	@GetMapping("/administrarVehiculo")
	public String adminVehiculos(Model modelo) {
		return "administrarVehiculo";
	}

	@GetMapping("/administrarRetiro")
	public String adminRetiros(Model modelo) {
		return "administrarRetiro";
	}
	
	// Gestion Clientes
	@GetMapping("/registra")
	public String registrarCliente(Model modelo) {
		Cliente c = new Cliente();
		modelo.addAttribute("cliente", c);
		return "registroCE";
	}

	@PostMapping("/registrarCliente")
	public String insertarCliente(Cliente cliente) {
		this.gestorEmpleadoService.insertarCliente(cliente);
		return "redirect:/empleados/administrarCliente";
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
		this.cliente = c;
		model.addAttribute("cliente", c);
		return "actualizarCliente";
	}

	@GetMapping("/actualizarCliente")
	public String actualizarCliente(Cliente cliente) {
		cliente.setId(this.cliente.getId());
		cliente.setRegistro(this.cliente.getRegistro());
		this.clienteService.actualizar(cliente);
		return "redirect:/empleados/buscarClientes";
	}

	// Eliminar Cliente
	@GetMapping("/eliminar/{idCliente}")
	public String eliminarClientesFormulario(@PathVariable("idCliente") Integer idCliente, Model model) {
		this.clienteService.eliminar(idCliente);
		return "redirect:/empleados/buscarClientes";
	}

	//Gestion Vehiculo *****
	@GetMapping("/buscarVehiculoMarca")
	public String buscarMarca(Model modelo) {
		List<VehiculoCampo> vehiculosMarca = this.vehiculoService.buscarCampos("marca");
		modelo.addAttribute("vehiculosMarca", vehiculosMarca);
		return "vistVehiculo";
	}

	@GetMapping("/listarMarca")
	public String listarVehiculoMarca(Model modelo, @RequestParam("marca") String marca) {
		List<Vehiculo> vehiculos = this.vehiculoService.buscarMarca(marca);
		modelo.addAttribute("listaVehiculos", vehiculos);
		return "listaVehiculos";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model modelo) {
		modelo.addAttribute("ingresarVehiculo", new Vehiculo());
		return "crearVehiculo";
	}
	
	@PostMapping("/ingresar/vehiculo")
	public String insertarVehiculo(Vehiculo vehiculo, Model modelo) {
		System.out.println("*********"+vehiculo.toString());
		this.vehiculoService.insertar(vehiculo);
		return "redirect:/empleados/administrarVehiculo";
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
	public String actualizarVehiculos(@PathVariable("placaVehiculo") String placa, Vehiculo vehiculo, Model modelo) {
		vehiculo.setPlaca(placa);
		vehiculo.setId(this.ve.getId());
		this.vehiculoService.actualizar(vehiculo);
		return "redirect:/empleados/buscarVehiculoMarca";

	}

	@GetMapping("/borrar/{id}")
	public String borrarVehiculoId(@PathVariable("id") Integer id) {
		this.vehiculoService.eliminar(id);
		return "redirect:/empleados/listaVehiculos";
	}

	//Retiros Vehiculos
	//Retirar con Reserva
	@GetMapping("/vehiculo/con/reserva")
	public String vehiculoReservado(Model modelo) {
		return "retirarConReserva";
	}

	@GetMapping("/info/vehiculo/con/reserva")
	public String infoVehiculoReservado(@RequestParam("numero") String numero, Model modelo) {
		Reserva r = this.reservaService.buscarPorNumero(numero);
		this.reserva = r;
		modelo.addAttribute("reserva", this.reserva);
		return "vehiculoReservado";
	}

	@GetMapping("/retirar/vehiculo/con/reserva")
	public String retirarVehiculoReservado(Model modelo) {
		this.gestorEmpleadoService.retirarVehiculoReservado(this.reserva.getNumero());
		return "redirect:/empleados/vehiculo/con/reserva";
	}

	// Retirar sin Reserva
	@GetMapping("/buscar/vehiculos/marca/modelo")
	public String buscarMarcaModelo(Model modelo) {
		List<VehiculoCampo> vehiculosMarca = this.vehiculoService.buscarCampos("marca");
		List<VehiculoCampo> vehiculosModelo = this.vehiculoService.buscarCampos("modelo");
		modelo.addAttribute("vehiculosMarca", vehiculosMarca);
		modelo.addAttribute("vehiculosModelo", vehiculosModelo);
		return "buscarVehiculosEmpleado";
	}

	@GetMapping("/buscar/vehiculos")
	public String resultados(@RequestParam("marca") String marca, @RequestParam("modelo") String modelo, Model model) {
		List<VehiculoLigero> lista = this.vehiculoService.buscarLigero(marca, modelo);
		model.addAttribute("vehiculos", lista);
		return "vehiculosListaEmpleado";
	}

	@GetMapping("/buscar/disponible")
	public String buscarDisponible(@RequestParam("placa") String placa, @RequestParam("cedula") String cedula,
			@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
			@RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin,
			Model modelo) {
		// verificar que exista el cliente y el vehiculo
		if (this.clienteGestorServiceImpl.datosValidos(placa, cedula)) {
			Reserva reserva = this.clienteGestorServiceImpl.disponibilidad(placa, cedula, fechaInicio, fechaFin);
			modelo.addAttribute("reserva", reserva);
			this.ve = this.vehiculoService.buscarPorPlaca(placa);
			this.cliente = this.clienteService.buscarClientePorCedula(cedula);
			this.reserva = reserva;
			return "cobroEmpleado";
		} // si no existe el cliente o el vehiculo
		else {
			modelo.addAttribute("reserva", null);
			return "redirect:/empleados/buscar/disponible";
		}

	}

	@GetMapping("/buscar/reserva")
	public String buscarReserva(Model modelo) {
		return "reservaEmpleado";
	}

	@PostMapping("/cobro/sin/reserva")
	public String realizarReserva(@RequestParam("tarjeta") String tarjeta) {
		this.clienteGestorServiceImpl.registrarReserva(this.reserva, tarjeta);
		this.gestorEmpleadoService.retirarVehiculoSinReserva(this.cliente.getCedula(), this.ve.getPlaca(),
				this.reserva.getFechaInicio(), this.reserva.getFechaFin(), tarjeta);
		return "redirect:/empleados/home";
	}

}
