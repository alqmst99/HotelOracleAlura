package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Entity.Usuario;
import views.Login;
import views.MenuUsuario;

public class UsuarioControlador implements ActionListener{

	private Login vista;
	public  UsuarioControlador(Login vista) {
		this.vista = vista;
		
	}
	@Override
	public void actionPerformed( ActionEvent e) {
		String nombre = vista.getNombre();
		String contraseña = vista.getContraseña();
		if(Usuario.validarUsuari(nombre, contraseña)) {
			MenuUsuario menu= new MenuUsuario();
			menu.setVisible(true);
			vista.dispose();
		}else {
			JOptionPane.showMessageDialog(vista, "el usuariio o la contraseña, es incorrecta");
		}
	}
}
