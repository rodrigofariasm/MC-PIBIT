package controllers;

import java.sql.Connection;
import javax.sql.DataSource;
import play.db.*;
import models.*;
import play.mvc.*;
import play.data.*;
import views.html.*;
import java.util.List;

public class Application extends Controller {
	Connection connection = DB.getConnection();
    DataSource ds = DB.getDataSource();
	
	static Form<NewUser> newUser = Form.form(NewUser.class);
    static Form<User> userForm = Form.form(User.class);
    List<User> users;
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
                return ok("Login Deu certo em cabron");  
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
            	  return redirect(routes.Application.login());
              }
    }
    
    
}