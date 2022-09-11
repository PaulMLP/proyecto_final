package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Vehiculo;

public interface IGestorEmpleadoService {

	public List<Vehiculo> verPorMarca(String marca);

	public String retirarVehiculoReservado(String numeroReserva);
}
