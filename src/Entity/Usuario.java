package Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.PreparableStatement;

import Factory.ConexionBase;

public class Usuario {
	private String nombre;
	private String contraseña;
	
	public Usuario(String nombre, String contraseña) {
		this.nombre= nombre;
		this.contraseña= contraseña;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
 public static boolean validarUsuari(String nombre, String contraseña) {
	 ConexionBase cn= new ConexionBase();
	 Connection connec= null;
	 PreparedStatement state= null;
	 ResultSet result = null;
	 try {
		 connec= cn.conectarBase();
		 state= connec.prepareStatement("SELECT * FROM usuarios WHERE nombre=? AND contraseña =?");
		 state.setString(1, nombre);
		 state.setString(2, contraseña);
		result = state.executeQuery();
		return result.next();
				
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}finally {
		try {
			if (result !=null) {
				result.close();
				
			}
			if (state !=null) {
				state.close();
			}
			if(connec != null) {
				connec.close();
			}
		}catch (SQLException e2) {
			e2.printStackTrace();
			// TODO: handle exception
		}
	}
 }
}
