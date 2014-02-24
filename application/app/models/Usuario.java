package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model;

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

	public static void create(Usuario usuario) {
		 usuario.save();
	 }
	
	public static Usuario authenticate(String email, String password) {
        return find.where().eq("email", email)
            .eq("password", password).findUnique();
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

