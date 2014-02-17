package models;

import java.util.*;
import play.data.format.*;
import javax.persistence.*;
import play.db.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.*;

@Entity
@Table(name="USUARIO")
public class Usuario extends Model{
	
	@Id
	private Long id;
	
	@Email
	@Column
	private String email;
	
	@Required
	@Column
	private String password;
	
	@Column
	private boolean sessaoAtiva = false;
	
	public Usuario(){
		
	}
	public Usuario(Long id, String email, String password){
		this.email = email; this.password = password;
		this.id = id;
	}
	
	
	
	// 
	public static Finder<Long, Usuario> find = new Finder(Long.class, Usuario.class);
	
	
	public static List<Usuario> all() {
		 return Usuario.find.all();
	}

	public static void create(Usuario usuario) {
		 usuario.save();
	 }
	 
	public static Usuario findById(long id){
		return Usuario.find.byId(id);
		
	}
	 public static void delete(Long id) {
		 find.ref(id).delete();
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
	public void encerraSessao() {
		this.sessaoAtiva = false;
	}
	public void iniciarSessao(){
		this.sessaoAtiva = true;
	}
	public String toString(){
		return "" + email;
	}
}
