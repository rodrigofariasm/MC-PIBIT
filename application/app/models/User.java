package models;

import java.util.*;
import play.data.format.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class User {
	
	private Long id;
	
	@Constraints.Email
	private String name;
	
	@Constraints.Required
	private String password;
	
	private boolean sessaoAtiva = false;
	private List<Carona> caronas;
	private Carona ultimaCarona;
	private List<SolicitacaoCarona> solicitacoes;
	private SolicitacaoCarona ultimaSolicitacao;
	
	public User(){
		
	}
	public User(Long id, String email, String password){
		name = email; this.password = password;
		this.id = id;
		caronas = new ArrayList<Carona>();
		solicitacoes = new ArrayList<SolicitacaoCarona>();
	}
	
	public void iniciarSessao(){
		sessaoAtiva = true;
	}
	
	public static void create(User user){
	//	user.save();
	}
	
	public void criarCarona(Carona nova){
		caronas.add(nova);
		ultimaCarona = nova;
	}
	public void criarSolicitacao(SolicitacaoCarona nova){
		solicitacoes.add(nova);
		ultimaSolicitacao = nova;
	}
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	} 
	
	public boolean equals(Object obj){ 
	       if(obj instanceof User){ 
	           User compara = (User)obj; 
	           if(getName().equals(compara.getName())) 
	               return true; 
	       } 
	       return false; 
	}
	
	
	
	public boolean isSessaoAtiva() {
		return sessaoAtiva;
	}
	public void setSessaoAtiva(boolean sessaoAtiva) {
		this.sessaoAtiva = sessaoAtiva;
	}
	public String toString(){
		return "" + name;
	}
}
