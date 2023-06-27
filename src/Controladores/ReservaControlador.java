package Controladores;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import DAO.ReservaDAO;
import Entity.Reserva;
import Factory.ConexionBase;

public class ReservaControlador {

	
	private ReservaDAO reservaD;

	public ReservaControlador() {
		Connection con = new ConexionBase().conectarBase();
		this.reservaD = new ReservaDAO(con);
	}
	public void guardar(Reserva reserva) {
		this.reservaD.guardar(reserva);
	}
	public List<Reserva>mostrar() {
		return this.reservaD.mostrar();
	}
	public List<Reserva>buscar(String id) {
		return this.reservaD.buscarId(id);
	}
	public void actualizar(LocalDate dateE, LocalDate dateS, String valor, String formaPago, Integer id) {
	
		this.reservaD.actualizar(dateE,  dateS, valor, formaPago, id);		
	}
	public void eliminar(Integer id) {
		this.reservaD.eliminar(id);
	}
	
}
