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

	
	static private User user;
	static private long ID;
	public Sessao(long ID, User user){
		this.user = user;
		this.ID = ID;
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
	
	
}
