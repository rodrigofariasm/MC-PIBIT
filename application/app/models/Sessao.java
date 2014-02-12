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
	static private Usuario user;
	static private long ID;
	public Sessao(long ID, Usuario user){
		this.user = user;
		this.ID = ID;
		this.sessaoAtiva = true;
	}
	public static Usuario getUser() {
		return user;
	}
	public static void setUser(Usuario user) {
		Sessao.user = user;
	}
	public static long getID() {
		return ID;
	}
	public static void setID(long iD) {
		ID = iD;
	}
	
	public Carona criarCarona(long ID, String origem, String destino, java.util.Date data, String hora,
            String vagas) throws Exception{
		Carona carona = new Carona(ID, origem, destino, data, vagas, user);
		return carona;
	}
	
	public static boolean isSessaoAtiva() {
		return sessaoAtiva;
	}
	public static void setSessaoAtiva(boolean sessaoAtiva) {
		Sessao.sessaoAtiva = sessaoAtiva;
	}
}
