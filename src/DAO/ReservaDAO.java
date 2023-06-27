package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import Entity.Reserva;

public class ReservaDAO {
	private Connection con;

	
	//Conexion 
	public ReservaDAO(Connection con) {
		super();
		this.con = con;
	}
	
	//Guarda las reservas
	
	public void guardar(Reserva reserva) {
		try {
			String sql= "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_de_pago)" + "VALUES(?,?,?,?)";
			try (PreparedStatement pstm= con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
				pstm.setObject(1, reserva.getDateE());
				pstm.setObject(2, reserva.getDateS());
				pstm.setObject(3, reserva.getValor());
				pstm.setObject(4, reserva.getFormapago());
				pstm.executeUpdate();
				try(ResultSet rts= pstm.getGeneratedKeys() ){
					while(rts.next()){
						reserva.setId(rts.getInt(1));
					}
				}
				
			}
		}catch(SQLException e) {
			throw new RuntimeException("animal" + e.getMessage(), e);
		}
	}
	
	//Llama a la lista de reservas
	
	public List<Reserva> mostrar(){
		List<Reserva> reservas= new ArrayList<Reserva>();
		try {
			String sql= "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas";
			try(PreparedStatement pstm= con.prepareStatement(sql) ){
				pstm.execute();
				TransResultado(reservas, pstm);
			}
			return reservas;
			
		} catch (SQLException e) {
			throw new RuntimeException("animal" + e.getMessage(), e);
		}
	}
	
	//Parametros de entrada para 
	private void TransResultado(List<Reserva> reservas, PreparedStatement pstm)  throws SQLException{
		try(ResultSet rts= pstm.getResultSet()){
			while (rts.next()) {
				int id =rts.getInt("id");
				LocalDate fechaE = rts.getDate("fecha_entrada").toLocalDate().plusDays(1);
				LocalDate fechaS = rts.getDate("fecha_salida").toLocalDate().plusDays(1);
				String valor= rts.getString("valor");
				String formaPago= rts.getString("forma_de_pago");
			
				Reserva producto = new Reserva(id, fechaE, fechaS, valor, formaPago);
				reservas.add(producto);
			}
				
				
			
		}
		
	}
	
	// Buscar reservas por  id
	public List<Reserva> buscarId(String id){
		
		List<Reserva> reservas= new ArrayList<Reserva>();
		try {
			String sql= "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas WHERE id=?";
			try(PreparedStatement pstm= con.prepareStatement(sql) ){
				pstm.setString(1, id);				
				pstm.execute();
				TransResultado(reservas, pstm);
			}
			return reservas;
			
		} catch (SQLException e) {
			throw new RuntimeException("animal" + e.getMessage(), e);
		}
	}
	
	
	//Actualizar reservas
		public void actualizar(LocalDate dateE, LocalDate dateS, String valor, String formaPago, Integer id) {
			try(PreparedStatement pst= con.prepareStatement("UPDATE reservas SET "+ "fecha_entrada=?, fecha_salida=?, valor=?, forma_de_pago=? WHERE id=?")) {
				pst.setObject(1, java.sql.Date.valueOf(dateE));
				pst.setObject(2, java.sql.Date.valueOf(dateS));
				pst.setString(3, valor);
				pst.setString(4, formaPago);
				pst.setInt(5, id);
				pst.execute();
			} catch (SQLException e) {
				throw new RuntimeException("animal" + e.getMessage(), e);
			}
		
			}
	 //Eliminar
		
		public void eliminar(Integer id) {
			try{
				Statement state = con.createStatement();
				state.execute("SET FOREIGN_KEY_CHECKS=0");
				PreparedStatement pst= con.prepareStatement("DELETE FROM reservas WHERE id=?");
				pst.setInt(1, id);
				pst.execute();
				state.execute("SET FOREIGN_KEY_CHECKS=1");		
		}catch(SQLException e) {
			throw new RuntimeException("animal" + e.getMessage(), e);
		}
		
		}
}
