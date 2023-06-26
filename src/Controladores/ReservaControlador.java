package Controladores;

import java.sql.Connection;

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
}
