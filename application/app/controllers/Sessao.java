package controllers;


import java.sql.Connection;
import javax.sql.DataSource;
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
	
	public static Result selecionarTipo(){
		return ok(views.html.selCaronas.render(user));
	}
	
	public static Result selecionadoTipo(){
		
		return redirect(routes.Sessao.especifica());
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
