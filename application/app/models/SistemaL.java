package models;

import java.util.Vector;
import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;
import java.text.*;

public class SistemaL {
	
	static Vector<User> users;
	
	public SistemaL(){
		users = new Vector<User>();
	}
	
	public Vector<User> getUsers(){
		return users;
	}
	
	public void addUser(User newUser){
		users.add(newUser);
	}
	
	public boolean autenticaLogin(User u) throws Exception{
		if (users.contains(u)){
			int i = users.indexOf(u);
			if(users.get(i).getPassword().equals(u.getPassword())){
				return true;
			}
		}
		return false;
	}
}
