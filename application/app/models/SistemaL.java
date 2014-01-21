package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;
import java.text.*;

public class SistemaL {
	
	static Vector<User> users;
	private static long IDuser = 0;
	public SistemaL(){
		users = new Vector<User>();
	}
	
	public Vector<User> getUsers(){
		return users;
	}
	
	public String criaUsuario(String email, String password){
		User cadastrado = new User(++IDuser, email, password);
		if(users.contains(cadastrado)){
			return "email já cadastrado";
		}else{
			users.add(cadastrado);
			return "Cadastrado com sucesso";
		}
	}
	
	public Sessao abrirSessao(User u){
		
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
