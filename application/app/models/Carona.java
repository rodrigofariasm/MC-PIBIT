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
	 public List<Usuario> caronas;
     public String pontoDeEncontro;
     
     public Carona(long ID, String origem, String destino, java.util.Date data,
             String vagas, Usuario motorista) throws Exception {
    	 this.ID = ID;
    	 this.origem = origem;
    	 this.destino = destino;
    	 this.data = data;
    	 this.vagas = vagas;
    	 this.motorista = motorista;
    	 this.caronas = new ArrayList<Usuario>();
     }
     
}
