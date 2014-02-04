package controllers;

import java.sql.Connection;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.sql.DataSource;
import play.db.*;
import models.*;
import play.mvc.*;
import play.data.*;
import views.html.*;
import java.util.Vector;;

public class Application extends Controller {
	Connection connection = DB.getConnection();
	DataSource ds = DB.getDataSource();
	static Form<User> userForm = Form.form(User.class);
	private static SistemaL sistemaL = new SistemaL();

	public static Result index() {
		return redirect(routes.Application.login());
	}

	public static Result login() {
		return ok(views.html.index.render(userForm));
	}

	public static Result tentaLogin() throws Exception{
		Form<User> filledForm = userForm.bindFromRequest();
		
		if(filledForm.hasErrors()) {
			return badRequest(
					views.html.index.render(filledForm)
					);
		} else {
			if(sistemaL.autenticaLogin(filledForm.get())){
				long id = sistemaL.iniciarSessao(filledForm.get());
				return selecionarAcao(id);
			}
			return redirect(routes.Application.login());
		}
	}

	public static Result cadastro() {
		return ok(views.html.cadastro.render());
	}

	public static Result cadastramento(){
		DynamicForm newUser = Form.form().bindFromRequest();
		String email = newUser.get("name");
		String password = newUser.get("password");
		String rePassword = newUser.get("rePassword");
		if(newUser.hasErrors()) {
			return badRequest(
					views.html.cadastro.render()
					);
		} else {
			if(password.equals(rePassword)){
				String st = sistemaL.criaUsuario(email, rePassword);
				if(st.equals("email já cadastrado")){
					return ok(views.html.cadastro.render());
				}
				return ok(views.html.index.render(userForm));
			}else{
				return ok(views.html.cadastro.render());
			}
		}

	}
	public static Result selecionarAcao(long id){
		return ok(views.html.selCaronas.render(id));
	}

	public static Result selecionadaAcao(long id){
		DynamicForm option = Form.form().bindFromRequest();
		String selecao = option.get("typeAction");
		System.out.println(selecao);
		return redirect(routes.Application.especifica(id, selecao));
	}

	public static Result especifica(long id, String selecao){
		return ok(views.html.especificacao.render(id, selecao));
	}

	public static Result especificado(long id, String kind) throws Exception{
		DynamicForm info = Form.form().bindFromRequest();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy kk:mm");
		String bairro = info.get("Bairro");
		String vagas = info.get("vagas");
		String day = info.get("day"), month = info.get("month"), hour = info.get("hour"), min = info.get("min");
		java.util.Date date = (java.util.Date) format.parse(day +"/"+month+"/2014"+ " " + hour + ":" + min);
	    sistemaL.criarCarona(id, bairro, "UFCG", date, vagas, "castanha@gmail.com");
		return ok(views.html.especificacao.render(id, kind));

	}

}