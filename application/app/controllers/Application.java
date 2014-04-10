package controllers;

import views.html.*;
import views.html.helper.form;

import java.sql.Connection;
import java.sql.Date;

import javax.sql.DataSource;

import play.db.*;
import models.*;
import play.mvc.*;
import play.data.*;
import play.data.Form.*;

public class Application extends Controller {

	private static Usuario usuarioAtual = Usuario.find.byId(request().username());
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
		DynamicForm caronaForm = Form.form().bindFromRequest(); 
		String motorista = request().username();
		String origem = caronaForm.get("origem");
		String destino = caronaForm.get("destino");
		String data = caronaForm.get("data");
		String horario = caronaForm.get("hora");
		if(horario.trim().equals("") || data.trim().equals("") || origem.trim().equals("") || destino.trim().equals("") ){
			flash("erro", "Erro ao preencher campos");
			return redirect(routes.Application.daCarona());
		}
		System.out.println(data + " asdasd " + horario);
		java.util.Date date = Carona.formataData(data, horario);
		int vagas = Integer.parseInt(caronaForm.get("vagas"));
		Carona.create(origem, destino, date, vagas, motorista);
		return redirect(routes.Application.verSolicitacoes());
	}
	
	@Security.Authenticated(Secured.class)
	public static Result verCaronas(){
		return ok(visualizarCaronas.render(
		        Carona.findNotInvolving(request().username()), 
		        SolicitacaoCarona.findInvolving(request().username()),
		        Usuario.find.byId(request().username())
		    ));
	}
	
	@Security.Authenticated(Secured.class)
	public static Result criarSolicitacao(){
		return ok(darCarona.render(
		        Usuario.find.byId(request().username())
		    ));
	}
	
	@Security.Authenticated(Secured.class)
	public static Result pegarCarona(long id){
		Carona carona = Carona.find.byId(id);
		carona.addCaronista(usuarioAtual.email);
		flash("success", "Você está nessa carona");
		return redirect(routes.Application.index());
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