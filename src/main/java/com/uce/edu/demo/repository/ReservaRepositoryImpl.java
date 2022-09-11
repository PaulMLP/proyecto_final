package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Reserva;
import com.uce.edu.demo.repository.modelo.ReservaLigeroReporte;

@Repository
@Transactional
public class ReservaRepositoryImpl implements IReservaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Reserva reserva) {
		this.entityManager.persist(reserva);
	}

	@Override
	public Reserva buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Reserva.class, id);
	}

	@Override
	public List<ReservaLigeroReporte> reporteReservas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		String SQL = "SELECT NEW com.uce.edu.demo.repository.modelo.ReservaLigeroReporte(r.numero, r.estado, r.fechaInicio, r.fechaFin, r.cliente.cedula, r.cliente.nombre,"
				+ "r.vehiculo.placa, r.vehiculo.modelo, r.vehiculo.marca) FROM Reserva r WHERE r.fechaInicio >= :fechaInicio AND r.fechaFin <= :fechaFin";
		TypedQuery<ReservaLigeroReporte> myQuery = this.entityManager.createQuery(SQL, ReservaLigeroReporte.class);
		myQuery.setParameter("fechaInicio", fechaInicio);
		myQuery.setParameter("fechaFin", fechaFin);
		return myQuery.getResultList();
	}

	@Override
	public void actualizarReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		this.entityManager.merge(reserva);
	}

	@Override
	public Reserva buscarPorNumero(String numeroReserva) {
		TypedQuery<Reserva> myQuery = this.entityManager
				.createQuery("select r from Reserva r where r.numero=:numeroReserva", Reserva.class);
		myQuery.setParameter("numeroReserva", numeroReserva);

		try {
			return myQuery.getSingleResult();
		} catch (NoResultException notException) {
			return null;
		}
	}

	@Override
	public void eliminarReservaPorId(Integer id) {
		// TODO Auto-generated method stub
		Reserva reserva = this.buscar(id);
		this.entityManager.remove(reserva);
	}


}
