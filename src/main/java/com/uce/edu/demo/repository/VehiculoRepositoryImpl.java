package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Vehiculo;
import com.uce.edu.demo.repository.modelo.VehiculoCampo;
import com.uce.edu.demo.repository.modelo.VehiculoLigero;
import com.uce.edu.demo.repository.modelo.VehiculoVipReporte;

@Repository
@Transactional
public class VehiculoRepositoryImpl implements IVehiculoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Vehiculo> buscar(String marca, String modelo) {
		TypedQuery<Vehiculo> miTypedQuery = this.entityManager.createQuery(
				"SELECT v FROM Vehiculo v WHERE v.marca = :datoMarca AND v.modelo = :datoModelo", Vehiculo.class);
		miTypedQuery.setParameter("datoMarca", marca);
		miTypedQuery.setParameter("datoModelo", modelo);

		return miTypedQuery.getResultList();
	}

	@Override
	public List<VehiculoLigero> buscarLigero(String marca, String modelo) {
		TypedQuery<VehiculoLigero> miTypedQuery = this.entityManager.createQuery(
				"SELECT NEW com.uce.edu.demo.repository.modelo.VehiculoLigero(v.placa, v.modelo, v.marca, v.anioFabricacion, v.estado, v.valorDia) FROM Vehiculo v WHERE v.marca = :datoMarca AND v.modelo = :datoModelo",
				VehiculoLigero.class);
		miTypedQuery.setParameter("datoMarca", marca);
		miTypedQuery.setParameter("datoModelo", modelo);

		return miTypedQuery.getResultList();
	}

	@Override
	public Vehiculo buscarPorPlaca(String placa) {
		TypedQuery<Vehiculo> miTypedQuery = this.entityManager
				.createQuery("SELECT v FROM Vehiculo v WHERE v.placa = :datoPlaca", Vehiculo.class);
		miTypedQuery.setParameter("datoPlaca", placa);

		return miTypedQuery.getSingleResult();
	}

	@Override
	public List<Vehiculo> buscarMarca(String marca) {
		// TODO Auto-generated method stub
		TypedQuery<Vehiculo> query = this.entityManager.createQuery("Select v from Vehiculo v where v.marca = :marca",
				Vehiculo.class);
		query.setParameter("marca", marca);
		return query.getResultList();
	}

	@Override
	public int insertar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		try {
			this.entityManager.persist(vehiculo);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Vehiculo buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Vehiculo.class, id);
	}

	@Override
	public void actualizar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.merge(vehiculo);
	}

	@Override
	public void eliminar(Integer id) {
		this.entityManager.remove(this.buscar(id));
	}

	public List<VehiculoVipReporte> buscarVehiculosVip(LocalDateTime fecha, LocalDateTime fechaAux) {
		String SQL = "SELECT NEW com.uce.edu.demo.repository.modelo.VehiculoVipReporte(v.placa, v.modelo, v.marca, co.valorSubtotal, co.valorTotalAPagar) "
				+ "FROM Vehiculo v JOIN v.reservas r JOIN r.cobro co WHERE co.fechaCobro >= :datoFechaCobro AND co.fechaCobro < :datoFechaAux";
		TypedQuery<VehiculoVipReporte> myQuery = this.entityManager.createQuery(SQL, VehiculoVipReporte.class);
		myQuery.setParameter("datoFechaCobro", fecha);
		myQuery.setParameter("datoFechaAux", fechaAux);
		return myQuery.getResultList();
	}

	@Override
	public List<VehiculoCampo> buscarCampos(String campo) {
		TypedQuery<VehiculoCampo> query = this.entityManager.createQuery(
				"Select DISTINCT NEW com.uce.edu.demo.repository.modelo.VehiculoCampo(v."+campo+") from Vehiculo v",
				VehiculoCampo.class);
		return query.getResultList();
	}

}
