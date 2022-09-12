package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoCampo;
import com.uce.edu.demo.repository.modelo.VehiculoLigero;
import com.uce.edu.demo.repository.modelo.VehiculoVipReporte;

public interface IVehiculoService {

	public List<Vehiculo> buscar(String marca, String modelo);

	public List<VehiculoLigero> buscarLigero(String marca, String modelo);

	public Vehiculo buscarPorPlaca(String placa);

	public List<VehiculoCampo> buscarCampos(String campo);

	public int insertar(Vehiculo vehiculo);

	public List<Vehiculo> buscarMarca(String marca);

	public void actualizar(Vehiculo vehiculo);

	public Vehiculo buscar(Integer id);

	public void eliminar(Integer id);

	public List<VehiculoVipReporte> buscarVehiculosVip(String fecha);

	public void ingresarVehiculo(String placa, String modelo, String marca, Integer anioFabricacion,
			String paisFabricacion, String cilindraje, BigDecimal avaluo, BigDecimal valorPorDia);

}
