package controllers;

import views.html.*;
import java.sql.Connection;
import java.sql.Date;

import javax.sql.DataSource;
import play.db.*;
import models.*;
import play.mvc.*;
import play.data.*;
import play.data.Form.*;

public class Application extends Controller {
	
	public static class Cadastro{
	    public String email;
	    public String password;
		public String repassword;

		public String validate() {
		    if (!password.equals(repassword)) {
		    	return "";
		    }
		    return null;
		}
	}
	public static class Login{
		public String email;
		
		public String password;
		
		public String validate() throws Exception {
			if (Usuario.authenticate(email, password) != null) {
		      return "Invalid user or password";
		    }
		    return null;
		}
	}
	
	
	Connection connection = DB.getConnection();
	DataSource ds = DB.getDataSource();
	
	
	static Form<Usuario> userForm = Form.form(Usuario.class);

	@Security.Authenticated(Secured.class)
	public static Result index() {
		return ok(index.render(
		        Carona.findInvolving(request().username()), 
		        SolicitacaoCarona.findInvolving(request().username()),
		        Usuario.find.byId(request().username())
		    )); 
	}

	public static Result login() {
		return ok(login.render(Form.form(Login.class)));
	}
	
	public static Result authenticate() {
	    Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
	    if (loginForm.hasErrors()) {
	        return badRequest(login.render(loginForm));
	    } else {
	        session().clear();
	        session("email", loginForm.get().email);
	        return redirect(
	            routes.Application.index()
	        );
	    }
	}
	
	
	public static Result logout() {
	    session().clear();
	    flash("success", "You've been logged out");
	    return redirect(
	        routes.Application.login()
	    );
	}

	public static Result cadastro() {
		return ok(cadastro.render(Form.form(Cadastro.class)));
	}

	public static Result efetuaCadastro()throws Exception{
		Form<Cadastro> cadastroForm = Form.form(Cadastro.class).bindFromRequest();
		Cadastro novoC = cadastroForm.get();
		if(cadastroForm.hasErrors()) {
			return badRequest(
					cadastro.render(Form.form(Cadastro.class))
					);
		} else {
			try{
			if(cadastroForm.get().validate()== null){
				Usuario.create(new Usuario(novoC.email, novoC.password));
			}}catch (Exception e){
				System.out.println(e);
			}
			return redirect(routes.Application.login());
		}

	}
	

	

	
	
}