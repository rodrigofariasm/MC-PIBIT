package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;
import java.text.*;

public class SistemaL {

	private static Vector<User> users;
	private static Vector<Carona> caronas;
	private static long IDuser = 0, IDcarona = 0, IDsessao = 0;
	
	public SistemaL(){
		users = new Vector<User>();
	}

	public Vector<User> getUsers(){
		return users;
	}

	public String criaUsuario(String email, String password){
		User cadastrado = new User(++IDuser, email, password);
		for (User u : users) {
			if(u.getName().equals(cadastrado.getName())){
				return "email já cadastrado";
			}
		}
		users.add(cadastrado);
		return "Cadastrado com sucesso";
	}

	public User iniciarSessao(User u) throws Exception{
		if(autenticaLogin(u)){
			return users.get(users.indexOf(u));
		}else{
			throw new Exception("Login Inválido");
		}
	}

	public boolean autenticaLogin(User u){
		if (users.contains(u)){
			int i = users.indexOf(u);
			if(users.get(i).getPassword().equals(u.getPassword())){
				return true;
			}
		}
		return false;
	}
}
