package models;

import exceptions.*;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import utilidades.BCrypt;
@Entity
public class Usuario extends Model{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public String email;
	public String password;
	public static Finder<String, Usuario> find = new Finder<String, Usuario>(String.class, Usuario.class);
	
	public Usuario(){
		
	}
	public Usuario(String email, String password){
		this.email = email; this.password = password;
	}
	
	public static List<Usuario> all() {
		 return Usuario.find.all();
	}

	/**
	 * Metodo para salvar usuario no BD
	 * @param usuario Usuario a ser salvo no BD
	 * @throws UsuarioJaExisteException se o Usuario já está cadastrado no BD
	 */
	public static String create(Usuario usuario) {
		if(find.where().eq("email", usuario.email).findUnique() == null){
			String senha = BCrypt.hashpw(usuario.password, BCrypt.gensalt());
			usuario = new Usuario(usuario.email, senha);
			usuario.save();
			return null;
		}else{
			return "UsuarioJaExisteException";
		}
	 }
	
	/**
	 * Método que recebe os campos da area de Login, e realiza a autenticação
	 * @param email
	 * @param password
	 * @return se a autenticação foi realizada com sucesso retona null, se não 
	 * retorna uma string dizendo o motivo da falha.
	 */
	public static String authenticate(String email, String password){
        Usuario x = find.where().eq("email", email).findUnique();
        if(x == null){
        	return "Usuario não encontrado";
        }
		if(BCrypt.checkpw(password, x.password)) return null;
		else{
			return "Senha incorreta";
		}
		

	}
	
	public boolean equals(Object obj){ 
	       if(obj instanceof Usuario){ 
	           Usuario compara = (Usuario)obj; 
	           if(email.equals(compara.email)) 
	               return true; 
	       } 
	       return false; 
	}
}

