package models;


import java.sql.Connection;
import javax.sql.DataSource;

import controllers.routes;
import play.db.*;
import models.*;
import play.mvc.*;
import play.data.*;
import views.html.*;
import java.util.Vector;
public class Sessao extends Controller {

	static boolean sessaoAtiva;
	static private User user;
	static private long ID;
	public Sessao(long ID, User user){
		this.user = user;
		this.ID = ID;
		this.sessaoAtiva = true;
	}
	public static User getUser() {
		return user;
	}
	public static void setUser(User user) {
		Sessao.user = user;
	}
	public static long getID() {
		return ID;
	}
	public static void setID(long iD) {
		ID = iD;
	}
	
	public Carona criarCarona(long ID, String origem, String destino, String data, String hora,
            String vagas) throws Exception{
		Carona carona = new Carona(ID, origem, destino, data, hora, vagas, user);
		return carona;
	}
	public SolicitacaoCarona solicitarCarona(String origem, String destino, String data, String hora,
			 long ID){
		return new SolicitacaoCarona(ID, origem, destino, data, hora, user);
	}
	
	public static boolean isSessaoAtiva() {
		return sessaoAtiva;
	}
	public static void setSessaoAtiva(boolean sessaoAtiva) {
		Sessao.sessaoAtiva = sessaoAtiva;
	}
}
