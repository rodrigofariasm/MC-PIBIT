package models;

import java.util.*;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model;

@Entity
public class Carona extends Model{
	
	 @Id
	 long ID;
	 public String origem, destino,  vagas;
	 public java.util.Date data;
	 
	 @OneToOne
	 public Usuario motorista;
	 @ManyToOne
	 public List<Usuario> caronistas;
     public String pontoDeEncontro;
     
     public Carona(long ID, String origem, String destino, java.util.Date data,
             String vagas, Usuario motorista) {
    	 this.ID = ID;
    	 this.origem = origem;
    	 this.destino = destino;
    	 this.data = data;
    	 this.vagas = vagas;
    	 this.motorista = motorista;
    	 this.caronistas = new ArrayList<Usuario>();
     }
     
     public static Model.Finder<Long,Carona> find = new Model.Finder(Long.class, Carona.class);
     
     public static Carona create(long ID, String origem, String destino, java.util.Date data, String vagas, String motorista) {
         Carona carona = new Carona(ID, origem, destino, data, vagas, Usuario.find.byId(motorista));
         carona.save();
         carona.saveManyToManyAssociations("caronistas");
         return carona;
     }
     
     public static List<Carona> findInvolving(String user) {
         return find.where()
             .eq("motorista.email", user)
             .findList();
     }
     
     public static Carona findCarona(String user) {
         return find.where()
             .eq("motorista.email", user)
             .findUnique();
     }
}
