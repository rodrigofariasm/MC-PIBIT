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
     
     public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getVagas() {
		return vagas;
	}

	public void setVagas(String vagas) {
		this.vagas = vagas;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public User getMotorista() {
		return motorista;
	}

	public void setMotorista(User motorista) {
		this.motorista = motorista;
	}

	public List<User> getCaronas() {
		return caronas;
	}

	public void setCaronas(List<User> caronas) {
		this.caronas = caronas;
	}

	public String getPontoDeEncontro() {
		return pontoDeEncontro;
	}

	public void setPontoDeEncontro(String pontoDeEncontro) {
		this.pontoDeEncontro = pontoDeEncontro;
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
