package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Caronista extends User {

	private String bairro;
	@Formats.DateTime(pattern="dd/MM/yyyy")
	private Date horario = new Date(); 
	
	public Caronista(Long id, String name, String password, String bairro, Date horario){
		super(id, name, password);
		this.bairro = bairro;
		this.horario = horario;
	}


	public static Finder<Long, Caronista> find = new Finder<Long,Caronista>(Long.class, Caronista.class);	

	public static void create(Caronista caronista){
	//	caronista.save();
	}
	
	public static void delete(Long id){
		find.ref(id);
	}
	
	public static List<Caronista> all(){
		List<Caronista> bdList = find.all();
		return bdList;
	}
	
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

}
