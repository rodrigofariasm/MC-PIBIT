package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;


public class Motorista extends User {

	private String bairro;
	private int vagas;
	@Formats.DateTime(pattern="dd/MM/yyyy")
	private Date horario = new Date(); 
	
	public Motorista(Long id,String name, String password, String bairro, int vagas, Date horario){
		super(id, name, password);
		this.bairro = bairro;
		this.vagas = vagas;
		this.horario = horario;
	}



	



	public static Finder<Long, Motorista> find = new Finder<Long,Motorista >(Long.class, Motorista.class);	

	public static List<Motorista> all(){
		List<Motorista> bdList = find.all();
		return bdList;
	}
	
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

}
