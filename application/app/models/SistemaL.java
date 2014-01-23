package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;
import java.text.*;

public class SistemaL {

	private static List<User> users;
	private static List<Carona> caronas;
	private static long IDuser = 0, IDcarona = 0;
	
	public SistemaL(){
		users = new ArrayList<User>();
		caronas = new ArrayList<Carona>();
	}

	public List<User> getUsers(){
		return users;
	}
	
	public User getUserPorEmail(String email)throws Exception{
		for (User u : users) {
			if(u.getName().equals(email)){
				return u;
			}
		}
		throw new Exception("Usuario não encontrado");
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

	public long iniciarSessao(User u) throws Exception{
		if(autenticaLogin(u)){
			User logado = getUserPorEmail(u.getName());
			logado.iniciarSessao();
			return logado.getId();
		}else{
			throw new Exception("Login Inválido");
		}
	}

	public boolean autenticaLogin(User u) throws Exception{
		if (users.contains(u)){
			User log = getUserPorEmail(u.getName());
			if(log.getPassword().equals(u.getPassword())){
				return true;
			}
		}
		return false;
	}
	
	public Carona criarCarona(long ID, String origem, String destino, String data, String hora,
            String vagas, String email) throws Exception{
		if(getUserPorEmail(email).isSessaoAtiva()){
			return new Carona(ID, origem, destino, data, hora, vagas, getUserPorEmail(email));
		}else{
			throw new Exception("Realize o login");
		}
	}
	public SolicitacaoCarona solicitarCarona(String origem, String destino, String data, String hora,
			 long ID, String email) throws Exception{
		if(getUserPorEmail(email).isSessaoAtiva()){
			return new SolicitacaoCarona(ID, origem, destino, data, hora, getUserPorEmail(email));
		}else{
			throw new Exception("Realize o login");
		}
	}
	
}
