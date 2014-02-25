package controllers;

import models.*;
import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;
import utilidades.BCrypt;
import java.sql.Date;
import java.text.*;

public class SistemaL {

	
	// Codificador de Senha
	BCrypt crip = new BCrypt();
	
	public SistemaL(){
	}


	
	public void logoff(long id) throws Exception{
		//getUserPorID(id).encerraSessao();
	}

//	public boolean autenticaLogin(Usuario u) throws Exception{
//		String senha = u.password;
//		if (Usuario.all().contains(u)){
//			Usuario log = getUserPorEmail(u.email);
//			if(crip.checkpw(senha, log.password)){
//				return true;
//			}
//		}
//		return false;
//	}
//	
	public void criarCarona(String origem, String destino, int hora, int min,
            String vagas, long userID) throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.HOUR_OF_DAY, 24);
		java.util.Date data = calendar.getTime();
		System.out.println(data.getDay() + "/" + data.getMonth());
//		if(getUserPorID(userID).isSessaoAtiva()){
//			caronas.add(new Carona(++IDcarona, origem, destino, data, vagas, getUserPorID(userID)));
//		}else{
//			throw new Exception("Realize o login");
//		}
	}
	public void solicitarCarona(String origem, String destino, int hora, int min,
			  long userID, String pontoEncontro) throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.HOUR_OF_DAY, 24);
		java.util.Date data = calendar.getTime();
		System.out.println(data.getDay() + "/" + data.getMonth());

	}
	
//	public Usuario getUserPorEmail(String email) throws Exception{
//		for (Usuario u : users) {
//			if(u.email.equals(email)){
//				return u;
//			}
//		}
//		throw new Exception("Usuario não encontrado");
//	}
//	
	
	
}
