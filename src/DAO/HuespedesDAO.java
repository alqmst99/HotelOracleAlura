package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Entity.Huespedes;
import Entity.Reserva;

public class HuespedesDAO {
	private Connection con;

	//conexion
	public HuespedesDAO(Connection con) {
		super();
		this.con = con;
	}
	
	
	//Guardar huespedes
	public void guardar(Huespedes huespedes) {
		try {
			String sql="INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad" + ", telefono, id_reserva) VALUES(?, ? , ?, ?, ?, ?)";
		try (PreparedStatement pstm= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			pstm.setString(1, huespedes.getNombre());
			pstm.setString(2, huespedes.getApellido());
			pstm.setObject(3, huespedes.getFechaNacimiento());
			pstm.setString(4, huespedes.getNacionalidad());
			pstm.setString(5, huespedes.getTelefono());
			pstm.setInt(6, huespedes.getIdReserva());
			pstm.execute();
			try (ResultSet rst = pstm.getGeneratedKeys()){
				while(rst.next()) {
				huespedes.setId(rst.getInt(1));
				}
			}
			
		}
			
		} catch (SQLException e) {
			throw new RuntimeException("animal :" + e.getMessage(), e);
		}
		
	}
	
	//Llama a la lista de reservas
	
		public List<Huespedes> mostrar(){
			List<Huespedes> huespedes= new ArrayList<Huespedes>();
			try {
				String sql= "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes";
				try(PreparedStatement pstm= con.prepareStatement(sql) ){
					pstm.execute();
					TransResultado(huespedes, pstm);
				}
				return huespedes;
				
			} catch (SQLException e) {
				throw new RuntimeException("animal" + e.getMessage(), e);
			}
		}
		
		//Parametros de huesped para 
		private void TransResultado(List<Huespedes> huespedes, PreparedStatement pstm)  throws SQLException{
			try(ResultSet rts= pstm.getResultSet()){
				while (rts.next()) {
					int id =rts.getInt("id");
					String nombre = rts.getString("nombre");
					String apellido = rts.getString("apellido");
					LocalDate fechaNacimiento = rts.getDate("fecha_nacimiento").toLocalDate().plusDays(1);
					String nacionalidad= rts.getString("nacionalidad");
					String telefono= rts.getString("telefono");
					int idR= rts.getInt("id_reserva");
					Huespedes cliente = new Huespedes(id, nombre, apellido, fechaNacimiento, telefono, nacionalidad, idR);
					huespedes.add(cliente);
				}
					
					
				
			}
			
		}
		
		// Buscar huesped por  id
		public List<Huespedes> buscarId(String id){
			
			List<Huespedes> huespedes= new ArrayList<Huespedes>();
			try {
				String sql= "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes WHERE id=?";
				try(PreparedStatement pstm= con.prepareStatement(sql) ){
					pstm.setString(1, id);				
					pstm.execute();
					TransResultado(huespedes, pstm);
				}
				return huespedes;
				
			} catch (SQLException e) {
				throw new RuntimeException("animal" + e.getMessage(), e);
			}
		}
		
		
		//Actualizar huespedes
			public void actualizar( String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad,
					String telefono, Integer idReserva, Integer id) {
				try(PreparedStatement pst= con.prepareStatement("" + "UPDATE huespedes SET "+ "nombre=?, apellido=?, fecha_nacimiento=?, nacionalidad=?, telefono=?, id_reserva=? WHERE id=?")) {
					pst.setString(1, nombre);
					pst.setString(2, apellido);
					pst.setObject(3, fechaNacimiento);
					pst.setString(4, nacionalidad);
					pst.setString(5, telefono);
					pst.setInt(6, idReserva);
					pst.setInt(7, id);
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
					PreparedStatement pst= con.prepareStatement("DELETE FROM huespedes WHERE id=?");
					pst.setInt(1, id);
					pst.execute();
					state.execute("SET FOREIGN_KEY_CHECKS=1");		
			}catch(SQLException e) {
				throw new RuntimeException("animal" + e.getMessage(), e);
			}
			
			}
	
	
	
}
