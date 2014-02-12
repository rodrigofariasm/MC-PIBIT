package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;
import java.text.*;

public class Carona {
	 long ID;
	 public String origem, destino,  vagas;
	 public java.util.Date data;
	 public Usuario motorista;
     public List<Usuario> caronas;
     public String pontoDeEncontro;
     
     public Carona(long ID, String origem, String destino, java.util.Date data,
             String vagas, Usuario motorista) throws Exception {
    	 this.ID = ID;
    	 this.origem = origem;
    	 this.destino = destino;
    	 this.data = data;
    	 this.vagas = vagas;
    	 this.motorista = motorista;
    	 this.caronas = new ArrayList<Usuario>();
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

	public java.util.Date getData() {
		return data;
	}

	public void setData(java.util.Date data) {
		this.data = data;
	}


	public String getVagas() {
		return vagas;
	}

	public void setVagas(String vagas) {
		this.vagas = vagas;
	}

	public long getID() {
		return ID;
	}

	public Usuario getMotorista() {
		return motorista;
	}

	public List<Usuario> getCaronas() {
		return caronas;
	}

	public void setCaronas(List<Usuario> caronas) {
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
