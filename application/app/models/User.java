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
	
	
	public User(){
		
	}
	public User(Long id, String email, String password){
		name = email; this.password = password;
		this.id = id;
	}
	
	public static void create(User user){
	//	user.save();
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
	
	public String toString(){
		return "" + name;
	}
}
