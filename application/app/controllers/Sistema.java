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
		if(kind == "motorista"){
			user = new Motorista(user.getId(),user.getName(), user.getPassword(), null, 0, null);
		}
		return redirect(routes.Sistema.especifica(kind));
	}
	
	public static Result especifica(String kind){
		return ok(views.html.especificacao.render(kind));
	}
	
	public static Result especificado(String kind){
		DynamicForm info = Form.form().bindFromRequest();
		String bairro = info.get("Bairro");
		String vagas = info.get("vagas");
		return ok(views.html.especificacao.render(kind));
		
	}
}
