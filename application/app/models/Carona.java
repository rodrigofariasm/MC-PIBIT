package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;
import java.text.*;

public class Carona {
	 private String origem, destino, data, hora, vagas, ID;
	 private User motorista;
     private List<User> caronas;
     private String pontoDeEncontro;
     
     public Carona(String origem, String destino, String data, String hora,
             String vagas, User motorista) throws Exception {
    	 this.origem = origem;
    	 this.destino = destino;
    	 this.data = data;
    	 this.hora = hora;
    	 this.vagas = vagas;
    	 this.motorista = motorista;
    	 this.caronas = new ArrayList<User>();
     }
     
     private boolean verificaDataValida(String data) {
         boolean dataValida = (data != null && !data.equals(""));

         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
         format.setLenient(false);
         try {
                 Date minhaData = format.parse(data);

                 if (minhaData.before(new Date()))
                         dataValida = false;
         } catch (Exception ex) {
                 dataValida = false;
         }
         return dataValida;

 }
     
}
