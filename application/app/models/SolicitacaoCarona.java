package models;

import java.util.*;

import javax.persistence.*;
import play.db.ebean.*;
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
    		Usuario solicitante, String ponto){
    	this.origem = origem;
    	this.destino = destino;
    	this.data = data;
    	this.user = solicitante;
    	this.ID = ID;
    	this.pontoDeEncontro = ponto;
     }
    
    public static Model.Finder<Long,SolicitacaoCarona> find = new Model.Finder(Long.class, SolicitacaoCarona.class);
    
    public static List<SolicitacaoCarona> findInvolving(String user) {
        return find.where()
            .eq("user.email", user)
            .findList();
    }
    
    public static SolicitacaoCarona create(long ID, String origem, String destino, java.util.Date data, String ponto, String solicitante) {
    	SolicitacaoCarona sCarona = new SolicitacaoCarona(ID, origem, destino, data, Usuario.find.byId(solicitante), ponto);
        sCarona.save();
        sCarona.saveManyToManyAssociations("caronistas");
        return sCarona;
    }
    
    public static SolicitacaoCarona findSolicitante(String user) {
        return find.where()
            .eq("user.email", user)
            .findUnique();
    }

}
