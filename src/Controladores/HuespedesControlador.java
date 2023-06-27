package Controladores;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import DAO.HuespedesDAO;
import Entity.Huespedes;

import Factory.ConexionBase;

public class HuespedesControlador {
	private HuespedesDAO huespedesDao;
	
	
	public HuespedesControlador() {
		Connection con= new ConexionBase().conectarBase();
		this.huespedesDao= new HuespedesDAO(con);
	}
	
	public void guardar(Huespedes huespedes) {
		this.huespedesDao.guardar(huespedes);
	}
	public List<Huespedes>mostrar() {
		return this.huespedesDao.mostrar();
	}
	public List<Huespedes>buscar(String id) {
		return this.huespedesDao.buscarId(id);
	}
	public void actualizar(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad,
			String telefono, Integer idReserva, Integer id ) {
		
		this.huespedesDao.actualizar(nombre, apellido,fechaNacimiento,nacionalidad,
				telefono, idReserva, id);		
	}
	public void eliminar(Integer idReserva) {
		this.huespedesDao.eliminar(idReserva);
	}
}
