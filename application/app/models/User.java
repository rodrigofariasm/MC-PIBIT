package models;

import java.util.*;
import play.data.format.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.validation.*;

public class User {
	
	
	@Constraints.Email
	private String name;
	
	@Constraints.Required
	private String password;
	
	
	
//	public User(String email, String password){
//		name = email; this.password = password;
//	}
//	
	

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
