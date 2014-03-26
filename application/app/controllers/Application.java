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

	@Security.Authenticated(Secured.class)
	public static Result index() {
		return ok(index.render(
		        Carona.findInvolving(request().username()), 
		        SolicitacaoCarona.findInvolving(request().username()),
		        Usuario.find.byId(request().username())
		    )); 
	}
	
	@Security.Authenticated(Secured.class)
	public static Result daCarona() {
		 return ok(darCarona.render(Usuario.find.byId(request().username())));
	}
	
	@Security.Authenticated(Secured.class)
	public static Result criaCarona() {
		 return ok(darCarona.render(Usuario.find.byId(request().username())));
	}
	
	@Security.Authenticated(Secured.class)
	public static Result verCaronas(){
		return ok(visualizarCaronas.render(
		        Carona.find.all(), 
		        SolicitacaoCarona.findInvolving(request().username()),
		        Usuario.find.byId(request().username())
		    ));
	}
	
	@Security.Authenticated(Secured.class)
	public static Result verSolicitacoes(){
		return ok(visualizarSolicitacoes.render(
		        Carona.find.all(), 
		        SolicitacaoCarona.find.all(),
		        Usuario.find.byId(request().username())
		    ));
	}
}