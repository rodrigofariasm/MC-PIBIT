package controllers;

import java.sql.Connection;
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

	public static Result tentaLogin(){
		Form<User> filledForm = userForm.bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(
					views.html.index.render(filledForm)
					);
		} else {
			if(sistemaL.autenticaLogin(filledForm.get())){
				return ok(views.html.selCaronas.render(filledForm.get()));
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
	public static Result selecionarAcao(){
		return ok(views.html.selCaronas.render(sistemaL.getUsers().get(0)));
	}

	public static Result selecionadaAcao(){
		DynamicForm option = Form.form().bindFromRequest();
		String selecao = option.get("typeAction");
		System.out.println(selecao);
		return redirect(routes.Application.especifica(selecao));
	}

	public static Result especifica(String selecao){
		return ok(views.html.especificacao.render(selecao));
	}

	public static Result especificado(String kind){
		DynamicForm info = Form.form().bindFromRequest();
		String bairro = info.get("Bairro");
		String vagas = info.get("vagas");
		return ok(views.html.especificacao.render(kind));

	}

}
