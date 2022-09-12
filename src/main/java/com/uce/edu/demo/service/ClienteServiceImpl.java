package com.uce.edu.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IClienteRepository;
import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.ClienteVipReporte;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository clienteRepository;

	@Override
	public Cliente buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.clienteRepository.buscarPorId(id);
	}

	@Override
	public boolean insertar(Cliente cliente) {
		// TODO Auto-generated method stub
		cliente.setRegistro("C");
		return this.clienteRepository.insertar(cliente);
	}

	@Override
	public Cliente buscarClientePorCedula(String cedula) {
		// TODO Auto-generated method stub
		return this.clienteRepository.buscarClientePorCedula(cedula);
	}

	// 3.b
	@Override
	public List<ClienteVipReporte> buscarClienteReservasPagadas() {
		// TODO Auto-generated method stub
		return this.clienteRepository.buscarClienteReservasPagadas();
	}

	// 2.b Buscar Cliente
	@Override
	public List<Cliente> buscarClientePorApellido(String apellido) {
		// TODO Auto-generated method stub
		return this.clienteRepository.buscarClientePorApellido(apellido);
	}

	// 2.b Actualizar Cliente
	@Override
	public void actualizar(Cliente cliente) {
		// TODO Auto-generated method stub
		this.clienteRepository.actualizar(cliente);

	}

	// 2.b Eliminar Cliente
	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.clienteRepository.eliminar(id);
	}

}
