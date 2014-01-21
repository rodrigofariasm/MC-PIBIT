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
	
	public Sessao(User user){
		this.user = user;
	}
	
	
}
