package com.uce.edu.demo.repository;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.ClienteVipReporte;

public interface IClienteRepository {
	public Cliente buscarPorId(Integer id);

	public boolean insertar(Cliente cliente);

	public void actualizar(Cliente cliente);

	public void eliminar(Integer id);

	public Cliente buscarClientePorCedula(String cedula);

	public List<Cliente> buscarClientePorApellido(String apellido);
	
	public List<ClienteVipReporte> buscarClienteReservasPagadas();
}
