package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model;
import utilidades.BCrypt;
@Entity
public class Usuario extends Model{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public String email;
	
	public String password;
	
	
	public Usuario(){
		
	}
	public Usuario(String email, String password){
		this.email = email; this.password = password;
	}
	
	
	
	public static Finder<String, Usuario> find = new Finder<String, Usuario>(String.class, Usuario.class);
	
	
	public static List<Usuario> all() {
		 return Usuario.find.all();
	}

	public static void create(Usuario usuario) throws Exception{
		if(find.where().eq("email", usuario.email).findUnique() == null){
			BCrypt crip = new BCrypt();
			String senha = crip.hashpw(usuario.password, crip.gensalt());
			usuario.password = senha;
			usuario.save();
		}else throw new Exception("Tente outro e-mail");
	 }
	
	public static Usuario authenticate(String email, String password) throws Exception {
		BCrypt crip = new BCrypt();
        Usuario x = find.where().eq("email", email).findUnique();
		if(crip.checkpw(password, x.password)) return x;
		else{
			throw new Exception("Senhas não conferem");
		}
		

	}
	
	
	
	public boolean equals(Object obj){ 
	       if(obj instanceof Usuario){ 
	           Usuario compara = (Usuario)obj; 
	           if(email.equals(compara.email)) 
	               return true; 
	       } 
	       return false; 
	}
}

