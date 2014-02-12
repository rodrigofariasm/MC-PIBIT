package models;

import java.util.*;
import play.data.format.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class Usuario {
	
	private Long id;
	
	@Constraints.Email
	private String email;
	
	@Constraints.Required
	private String password;
	
	private boolean sessaoAtiva = false;
	private List<Carona> caronas;
	private List<SolicitacaoCarona> solicitacoes;
	
	public Usuario(){
		
	}
	public Usuario(Long id, String email, String password){
		this.email = email; this.password = password;
		this.id = id;
		caronas = new ArrayList<Carona>();
		solicitacoes = new ArrayList<SolicitacaoCarona>();
	}
	
	public void iniciarSessao(){
		sessaoAtiva = true;
	}
	
	public static void create(Usuario user){
	//	user.save();
	}
	
	public void criarCarona(Carona nova){
		caronas.add(nova);
	}
	public void criarSolicitacao(SolicitacaoCarona nova){
		solicitacoes.add(nova);
	}
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	} 
	
	public boolean equals(Object obj){ 
	       if(obj instanceof Usuario){ 
	           Usuario compara = (Usuario)obj; 
	           if(getEmail().equals(compara.getEmail())) 
	               return true; 
	       } 
	       return false; 
	}
	
	
	
	public boolean isSessaoAtiva() {
		return sessaoAtiva;
	}
	public void abreSessao(){
		this.sessaoAtiva = true;
	}
	public void encerraSessao() {
		this.sessaoAtiva = false;
	}
	public String toString(){
		return "" + email;
	}
}
