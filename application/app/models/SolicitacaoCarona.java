package models;

import java.util.*;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model;
@Entity 
public class SolicitacaoCarona extends Model{
	
	@Id
	public long ID;
	public String origem, destino;
	public java.util.Date data;
    public String pontoDeEncontro;
    @OneToOne
    public Usuario user;
	
    public SolicitacaoCarona(long ID, String origem, String destino, java.util.Date data,
    		Usuario solicitador, String ponto){
    	this.origem = origem;
    	this.destino = destino;
    	this.data = data;
    	this.user = solicitador;
    	this.ID = ID;
    	this.pontoDeEncontro = ponto;
     }

}
