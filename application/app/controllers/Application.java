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
	static Long id = (long) 0;
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
					return ok(views.html.selCaronas.render(logado));
				}
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
				User cadastrado = new User(++id, email, password);
				if(listU.contains(cadastrado)){
					return ok(views.html.cadastro.render());
				}else{
					listU.add(cadastrado);
				}
				return ok(views.html.index.render(userForm));
			}else{
				return ok(views.html.cadastro.render());
			}
		}
		
	}


}