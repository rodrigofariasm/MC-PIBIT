package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;

import java.sql.Date;
import java.text.*;

public class SistemaL {

	private static List<Usuario> users;
	private static List<Carona> caronas;
	private static List<SolicitacaoCarona>solicitacoes;
	private static long IDuser = 0, IDcarona = 0, IDsolicitacao;
	
	// Codificador de Senha
	BCrypt crip = new BCrypt();
	
	public SistemaL(){
		users = new ArrayList<Usuario>();
		caronas = new ArrayList<Carona>();
	}

	public List<Usuario> getUsers(){
		return users;
	}

	public String criaUsuario(String email, String password){
		String senha = crip.hashpw(password, crip.gensalt());
		Usuario cadastrado = new Usuario(++IDuser, email, senha);
		for (Usuario u : users) {
			if(u.getEmail().equals(cadastrado.getEmail())){
				return "email já cadastrado";
			}
		}
		users.add(cadastrado);
		return "Cadastrado com sucesso";
	}

	public long iniciarSessao(Usuario u) throws Exception{
		if(autenticaLogin(u)){
			Usuario logado = getUserPorEmail(u.getEmail());
			logado.iniciarSessao();
			return logado.getId();
		}else{
			throw new Exception("Login Inválido");
		}
	}
	
	public void logoff(long id) throws Exception{
		getUserPorID(id).encerraSessao();
	}

	public boolean autenticaLogin(Usuario u) throws Exception{
		String senha = u.getPassword();
		if (users.contains(u)){
			Usuario log = getUserPorEmail(u.getEmail());
			if(crip.checkpw(senha, log.getPassword())){
				return true;
			}
		}
		return false;
	}
	
	public void criarCarona(String origem, String destino, int hora, int min,
            String vagas, long userID) throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.HOUR_OF_DAY, 24);
		java.util.Date data = calendar.getTime();
		System.out.println(data.getDay() + "/" + data.getMonth());
		if(getUserPorID(userID).isSessaoAtiva()){
			caronas.add(new Carona(++IDcarona, origem, destino, data, vagas, getUserPorID(userID)));
		}else{
			throw new Exception("Realize o login");
		}
	}
	public void solicitarCarona(String origem, String destino, int hora, int min,
			  long userID, String pontoEncontro) throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.HOUR_OF_DAY, 24);
		java.util.Date data = calendar.getTime();
		System.out.println(data.getDay() + "/" + data.getMonth());
		if(getUserPorID(userID).isSessaoAtiva()){
			solicitacoes.add( new SolicitacaoCarona(++IDsolicitacao, origem, destino, data,
					getUserPorID(userID), pontoEncontro));
		}else{
			throw new Exception("Realize o login");
		}
	}
	
	public String getEmailPorID(long ID)throws Exception{
		for (Usuario u : users) {
			if(u.getId() == ID){
				return u.getEmail();
			}
		}
		throw new Exception("Usuario não encontrado");
	}
	
	public Usuario getUserPorEmail(String email) throws Exception{
		for (Usuario u : users) {
			if(u.getEmail().equals(email)){
				return u;
			}
		}
		throw new Exception("Usuario não encontrado");
	}
	
	public Usuario getUserPorID(long ID)throws Exception{
		for (Usuario u : users) {
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
