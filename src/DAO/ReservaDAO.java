package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Reserva;

public class ReservaDAO {
	private Connection con;

	public ReservaDAO(Connection con) {
		super();
		this.con = con;
	}
	
	
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
}
