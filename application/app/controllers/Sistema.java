package controllers;

import models.*;
import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;
import utilidades.BCrypt;
import java.sql.Date;
import java.text.*;

public class SistemaL {

	
	// Codificador de Senha
	BCrypt crip = new BCrypt();
	
	public SistemaL(){
	}
}
