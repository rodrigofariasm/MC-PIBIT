package controllers;

import java.sql.Connection;
import java.sql.Date;

import javax.sql.DataSource;
import play.db.*;
import models.*;
import play.mvc.*;
import play.data.*;
import play.data.Form.*;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;

public class Application extends Controller {
	Connection connection = DB.getConnection();
	DataSource ds = DB.getDataSource();
	
	
	static Form<Usuario> userForm = Form.form(Usuario.class);
	private static SistemaL sistemaL = new SistemaL();

	public static Result index() {
		return redirect(routes.Application.login());
	}

	public static Result login() {
		return ok(views.html.index.render(userForm));
	}

	public static Result tentaLogin() throws Exception{
		Form<Usuario> filledForm = userForm.bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(
					views.html.index.render(filledForm)
					);
		} else {
			try{
				long id = sistemaL.iniciarSessao(filledForm.get());
				return redirect(routes.Application.selecionarAcao(id));
			}catch(Exception e){
				return redirect(routes.Application.login());
			}
		}
	}

	public static Result cadastro() {
		return ok(views.html.cadastro.render(Form.form(Cadastro.class)));
	}

	public static Result cadastramento(){
		Form<Cadastro> cadastroForm = Form.form(Cadastro.class).bindFromRequest();
		if(cadastroForm.hasErrors()) {
			return badRequest(
					views.html.cadastro.render(Form.form(Cadastro.class))
					);
		} else {
				return ok(views.html.index.render(userForm));
		}

	}
	
	public static Result logoff(long id)throws Exception{
		sistemaL.logoff(id);
		return redirect(routes.Application.login());
	}
	
	public static Result selecionarAcao(long id){
		return ok(views.html.selCaronas.render(id));
	}

	public static Result selecionadaAcao(long id){
		DynamicForm option = Form.form().bindFromRequest();
		String selecao = option.get("typeAction");
		return redirect(routes.Application.especifica(id, selecao));
	}

	public static Result especifica(long id, String selecao){
		return ok(views.html.especificacao.render(id, selecao));
	}

	public static Result especificado(long id, String kind) throws Exception{
		DynamicForm info = Form.form().bindFromRequest();
		String bairro = info.get("Bairro");
		int hour = Integer.valueOf(info.get("hour")), min = Integer.valueOf(info.get("min"));
		if(kind == "criarCarona"){
			String vagas = info.get("vagas");
			sistemaL.criarCarona(bairro, "UFCG", hour, min, vagas, id);
			return redirect(routes.Application.mostrarCaronas(id, kind));
		}else if(kind == "solicitarCarona"){
			String pontoEncontro = info.get("point");
			sistemaL.solicitarCarona(bairro, "UFCG", hour, min, id, pontoEncontro);
			return redirect(routes.Application.mostrarCaronas(id, kind));
		}else{
			return redirect(routes.Application.mostrarCaronas(id, kind));
		}
		
	}
	
	public static Result mostrarCaronas(long id, String kind){
		return ok(views.html.viewCaronas.render(id, sistemaL.getCaronas()));
		
	}
	
	public static class Cadastro{
		@Required
	    public String email;
		@Required
		@Min(6)
	    public String password;
		@Min(6)
	    public String repassword;
		public String validate() {
		    if (!password.equals(repassword)) {
		      return "As senhas estão diferentes";
		    }
		    return null;
		}
	}
	
	public static class Login{
		public String email;
		
		public String password;
		
		public String validate() {
		    if (Usuario.authenticate(email, password) == null) {
		      return "Invalid user or password";
		    }
		    return null;
		}
	}
	
}