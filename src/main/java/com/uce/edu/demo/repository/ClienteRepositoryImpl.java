package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Cliente;
import com.uce.edu.demo.repository.modelo.ClienteVipReporte;

@Repository
@Transactional
public class ClienteRepositoryImpl implements IClienteRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Cliente buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Cliente.class, id);
	}

	@Override
	public boolean insertar(Cliente cliente) {
		// TODO Auto-generated method stub
		try {
			this.entityManager.persist(cliente);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void actualizar(Cliente cliente) {
		// TODO Auto-generated method stub
		this.entityManager.merge(cliente);

	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		Cliente cliente = this.buscarPorId(id);
		this.entityManager.remove(cliente);

	}

	@Override
	public Cliente buscarClientePorCedula(String cedula) {
		// TODO Auto-generated method stub
		TypedQuery<Cliente> myQuery = this.entityManager.createQuery("select c from Cliente c where c.cedula=:cedula",
				Cliente.class);
		myQuery.setParameter("cedula", cedula);
		try {
			return myQuery.getSingleResult();
		} catch (NoResultException notException) {
			return null;
		}
	}

	@Override
	public List<Cliente> buscarClientePorApellido(String apellido) {
		// TODO Auto-generated method stub
		TypedQuery<Cliente> myQuery = this.entityManager
				.createQuery("select c from Cliente c where c.apellido=:apellido", Cliente.class);
		myQuery.setParameter("apellido", apellido);

		try {
			return myQuery.getResultList();
		} catch (NoResultException notException) {
			return null;
		}
	}

	@Override
	public List<ClienteVipReporte> buscarClienteReservasPagadas() {
		String SQL = "SELECT NEW com.uce.edu.demo.repository.modelo.ClienteVipReporte(c.cedula, c.nombre, r.numero, co.valorIva, co.valorTotalAPagar) "
				+ "FROM Cliente c JOIN c.reservas r JOIN r.cobro co " + "ORDER BY co.valorTotalAPagar DESC";
		TypedQuery<ClienteVipReporte> myQuery = this.entityManager.createQuery(SQL, ClienteVipReporte.class);

		return myQuery.getResultList();
	}

}
