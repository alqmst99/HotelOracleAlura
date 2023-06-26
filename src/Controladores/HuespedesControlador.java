package Controladores;

import java.sql.Connection;

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
}
