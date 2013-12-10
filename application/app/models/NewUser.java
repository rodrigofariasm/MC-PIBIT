package models;

import java.util.*;
import play.data.format.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.data.validation.*;

public class NewUser {
	
	
	@Constraints.Email
	private String name;
	
	@Constraints.Required
	private String password;
	
	@Constraints.Required
	private String rePassword;
	public NewUser(){
		
	}
	public NewUser(String email, String password, String repassword){
		name = email; this.password = password; rePassword = repassword;
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
	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String password) {
		this.rePassword = password;
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
