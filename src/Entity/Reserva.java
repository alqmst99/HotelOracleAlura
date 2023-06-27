package Entity;

import java.time.LocalDate;

public class Reserva {
private Integer id;
private LocalDate dateE;
private LocalDate dateS;
private String valor;
private String formapago;



public Reserva( LocalDate dateE, LocalDate dateS, String valor, String formapago) {
	super();
	
	this.dateE = dateE;
	this.dateS = dateS;
	this.valor = valor;
	this.formapago = formapago;
}




public Reserva(Integer id, LocalDate dateE, LocalDate dateS, String valor, String formapago) {
	super();
	this.id = id;
	this.dateE = dateE;
	this.dateS = dateS;
	this.valor = valor;
	this.formapago = formapago;
}




public Integer getId() {
	return id;
}


public void setId(Integer id) {
	this.id = id;
}


public LocalDate getDateE() {
	return dateE;
}


public void setDateE(LocalDate dateE) {
	this.dateE = dateE;
}


public LocalDate getDateS() {
	return dateS;
}


public void setDateS(LocalDate dateS) {
	this.dateS = dateS;
}


public String getValor() {
	return valor;
}


public void setValor(String valor) {
	this.valor = valor;
}


public String getFormapago() {
	return formapago;
}


public void setFormapago(String formapago) {
	this.formapago = formapago;
}



}
