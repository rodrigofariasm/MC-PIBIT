package models;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;

@Entity
public class Carona extends Model{
	
	 @Id
	 public long ID;
	 public String origem, destino; 
	 public int vagas;
	 public java.util.Date data;

	 @ManyToOne
	 public Usuario motorista;

	 public List<String> caronistas;
     
     public Carona( String origem, String destino, java.util.Date data,
             int vagas, Usuario motorista) {
    	 this.origem = origem;
    	 this.destino = destino;
    	 this.data = data;
    	 this.vagas = vagas;
    	 this.motorista = motorista;
    	 this.caronistas = new ArrayList<String>();
     }
     
     public static Model.Finder<Long,Carona> find = new Model.Finder(Long.class, Carona.class);
     
     public static Carona create(String origem, String destino, java.util.Date data, int vagas, String motorista) {
         Carona carona = new Carona(origem, destino, data, vagas, Usuario.find.byId(motorista));
         carona.save();
         return carona;
     }
     
     public static List<Carona> findInvolving(String user) {
         return find.where()
             .eq("motorista", Usuario.find.byId(user)).findList();
     }
     
     public static List<Carona> findNotInvolving(String user) {
         List<Carona> caronas = Carona.find.all();
         List<Carona> resp = Carona.find.all();
         for (Carona carona : caronas) {
			if(carona.motorista.email.equals(user) && carona.vagas>0){
				resp.remove(carona);
			}
         }
         return resp;
     }
     
     public static Carona findCarona(String user) {
         return find.where()
             .eq("motorista.email", user)
             .findUnique();
     }
     
     public void addCaronista(String email){
    	 caronistas.add(email);
    	 vagas--;
     }
     
     public static java.util.Date formataData(String data, String horario){
    	 System.out.println(data);
    	 System.out.println(horario);
    	 SimpleDateFormat sdf1= new SimpleDateFormat("dd/MM/yyyy hh:mm");
    	 try{
    		 Date dataUsuario=sdf1.parse(data);
    	 }catch(Exception e){}
    	 int ano = Integer.parseInt(data.substring(0, 4));
    	 int mes = Integer.parseInt(data.substring(5, 7));
    	 int dia = Integer.parseInt(data.substring(8, 10));
    	 int hora = Integer.parseInt(horario.substring(0, 2));
    	 int min = Integer.parseInt(horario.substring(3, 5));
    	 Date date = new Date();
    	 return new Date(ano, mes, dia, hora, min);	
     }
}
