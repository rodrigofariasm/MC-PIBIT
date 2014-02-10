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
	private static List<SolicitacaoCarona>solicitacoes;
	private static long IDuser = 0, IDcarona = 0, IDsolicitacao;
	
	public SistemaL(){
		users = new ArrayList<User>();
		caronas = new ArrayList<Carona>();
	}

	public List<User> getUsers(){
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
	
	public void criarCarona(String origem, String destino, java.util.Date data,
            String vagas, long userID) throws Exception{
		if(getUserPorID(userID).isSessaoAtiva()){
			caronas.add(new Carona(++IDcarona, origem, destino, data, vagas, getUserPorID(userID)));
		}else{
			throw new Exception("Realize o login");
		}
	}
	public void solicitarCarona(String origem, String destino, java.util.Date data,
			  long userID, String pontoEncontro) throws Exception{
		if(getUserPorID(userID).isSessaoAtiva()){
			solicitacoes.add( new SolicitacaoCarona(++IDsolicitacao, origem, destino, data,
					getUserPorID(userID), pontoEncontro));
		}else{
			throw new Exception("Realize o login");
		}
	}
	
	public String getEmailPorID(long ID)throws Exception{
		for (User u : users) {
			if(u.getId() == ID){
				return u.getName();
			}
		}
		throw new Exception("Usuario não encontrado");
	}
	
	public User getUserPorEmail(String email) throws Exception{
		for (User u : users) {
			if(u.getName().equals(email)){
				return u;
			}
		}
		throw new Exception("Usuario não encontrado");
	}
	
	public User getUserPorID(long ID)throws Exception{
		for (User u : users) {
			if(u.getId() == ID){
				return u;
			}
		}
		throw new Exception("Usuario não encontrado");
	}
	
	public List<Carona> getCaronas(){
		return caronas;
	}

	public List<SolicitacaoCarona> getSolicitacoes(){
		return solicitacoes;
	}
	
}
