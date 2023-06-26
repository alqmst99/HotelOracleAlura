package Factory;

import java.sql.Connection;
import java.sql.SQLException;

public class Testconexion {

	
	public static void main(String[] args) throws SQLException {
		ConexionBase con = new ConexionBase();
		Connection cone = con.conectarBase();
		System.out.println("Conecto bien");
		cone.close();
		System.out.println("Cerro bien");
	}
}
