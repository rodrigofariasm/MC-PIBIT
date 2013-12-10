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

	static Form<NewUser> newUser = Form.form(NewUser.class);
	static Form<User> userForm = Form.form(User.class);
	static Vector<User> listU = new Vector<User>();
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
			if(listU.indexOf(filledForm.get()) != -1){
				User logado = listU.get(listU.indexOf(filledForm.get()));
				if(logado.getPassword().equals(filledForm.get().getPassword())){
					return ok(logado.getName() + " " + logado.getPassword());
				}
			}
				return redirect(routes.Application.login());
		}
	}

	public static Result cadastro() {

		return ok(views.html.cadastro.render(newUser));
	}

	public static Result cadastramento(){
		Form <NewUser> filledForm = newUser.bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(
					views.html.cadastro.render(filledForm)
					);
		} else {
			NewUser newU = filledForm.get();
			if(newU.getPassword().equals(newU.getRePassword())){
				User Us = new User(newU.getName(), newU.getPassword());
				if(!listU.contains(Us)){
					listU.add(Us);
					return redirect(routes.Application.login());
				}else return redirect(routes.Application.cadastro());
			}else return(badRequest(views.html.cadastro.render(filledForm)));
			
		}
	}


}