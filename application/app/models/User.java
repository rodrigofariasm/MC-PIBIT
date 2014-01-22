package models;

import java.util.*;
import play.data.format.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class User {
	
	@Id
	private Long id;
	
	@Constraints.Email
	private String name;
	
	@Constraints.Required
	private String password;
	
	private boolean sessaoAtiva = false;
	
	public User(){
		
	}
	public User(Long id, String email, String password){
		name = email; this.password = password;
		this.id = id;
	}
	
	public void iniciarSessao(){
		sessaoAtiva = true;
	}
	
	public static void create(User user){
	//	user.save();
	}
	
	public long getId(){
		return this.id;
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
	           if(getName().equals(compara.getName()) && getId() == compara.getId()) 
	               return true; 
	       } 
	       return false; 
	}
	
	
	
	public String toString(){
		return "" + name;
	}
}
