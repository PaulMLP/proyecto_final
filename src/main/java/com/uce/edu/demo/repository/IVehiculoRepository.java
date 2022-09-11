package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoCampo;
import com.uce.edu.demo.repository.modelo.VehiculoLigero;
import com.uce.edu.demo.repository.modelo.VehiculoVipReporte;

public interface IVehiculoRepository {

	public List<Vehiculo> buscar(String marca, String modelo);

	public List<VehiculoLigero> buscarLigero(String marca, String modelo);
	
	public List<VehiculoCampo> buscarCampos(String campo);

	public Vehiculo buscarPorPlaca(String placa);
	

	public int insertar(Vehiculo vehiculo);

	public List<Vehiculo> buscarMarca(String marca);

	public void actualizar(Vehiculo vehiculo);
	
	
	public Vehiculo buscar(Integer id);

	public void eliminar(Integer id);
	
	public List<VehiculoVipReporte> buscarVehiculosVip(LocalDateTime fecha, LocalDateTime fechaAux);
	
	
}
