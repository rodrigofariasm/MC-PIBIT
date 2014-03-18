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

public class Autenticador extends Controller {
	
	public static class Cadastro{
	    public String email;
	    public String nome;
	    public String password;
		public String repassword;

		public String validate() {
			String erro = null;
		    if (!password.equals(repassword)) {
		    	erro = "Senha incorreta";
		    }
		    if (password.length() < 8){
		    	erro = "Senha deve ter no mínimo 8 caracteres";
		    }
		    if(email == null || email.trim().equals("")) {
		    	return "Tente algum email";
		    }else if(Usuario.find.where().eq("email", this.email).findUnique() != null){
					erro = "Usuario já existe";
		    }
		    if(erro  != null) {
				 flash("erro", erro);
		    }
		    return erro;
		}
	}
	public static class Login{
		public String email;
		public String password;
		
		public String validate(){
			String erro = Usuario.authenticate(email, password);
			if (erro  != null) {
				 flash("erro", erro);
		      return "Invalid user or password";
		    }
		    return null;
		}
	}
	
	
	Connection connection = DB.getConnection();
	DataSource ds = DB.getDataSource();
	
	
	static Form<Usuario> userForm = Form.form(Usuario.class);

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
	    flash("success", "Obrigado, por usar nosso app.");
	    return redirect(
	        routes.Autenticador.login()
	    );
	}

	public static Result cadastro() {
		return ok(cadastro.render(Form.form(Cadastro.class)));
	}

	public static Result efetuaCadastro(){
		Form<Cadastro> cadastroForm = Form.form(Cadastro.class).bindFromRequest();
		if(cadastroForm.hasErrors()) {
			return badRequest(
					cadastro.render(Form.form(Cadastro.class))
					);
		} else {
			String validacao = cadastroForm.get().validate();
			if( validacao== null){
				Usuario.create(new Usuario(cadastroForm.get().email, cadastroForm.get().nome, cadastroForm.get().password));
			}else{
				flash("erro", validacao);
				return redirect(routes.Autenticador.cadastro());
			}
			flash("success", "Cadastro realizado");
			return redirect(routes.Autenticador.login());
		}

	}
}