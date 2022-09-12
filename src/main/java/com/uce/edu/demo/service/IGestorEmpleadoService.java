package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.Vehiculo;

public interface IGestorEmpleadoService {

	public List<Vehiculo> verPorMarca(String marca);

	public String retirarVehiculoReservado(String numeroReserva);
	
	public void insertarCliente(Cliente cliente);
	
	public String retirarVehiculoSinReserva(String placa, String cedula, LocalDateTime fechaInicio,
			LocalDateTime fechaFin, String tarjeta);
}
