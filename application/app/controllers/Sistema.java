package controllers;

import java.sql.Connection;
import javax.sql.DataSource;


import play.db.*;
import models.*;
import play.mvc.*;
import play.data.*;
import views.html.*;
import java.util.Vector;
public class Sistema extends Controller {

	
	static private User user;
	
	public static Result selecionarTipo(){
		return ok(views.html.selCaronas.render(user));
	}
	
	public static Result selecionadoTipo(){
		DynamicForm tipo = Form.form().bindFromRequest();
		String kind = tipo.get("typeUser");
		System.out.println(kind);
		return ok(views.html.selCaronas.render(user));
	}
}
