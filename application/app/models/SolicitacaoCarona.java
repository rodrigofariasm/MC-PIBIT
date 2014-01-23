package models;

import java.util.List;

public class SolicitacaoCarona {
	 private String origem, destino, data, hora;
	 private long ID;
     private String pontoDeEncontro;
     private User user;
	
     public SolicitacaoCarona(long ID, String origem, String destino, String data, String hora,
    		 User solicitador){
    	 this.origem = origem;
    	 this.destino = destino;
    	 this.data = data;
    	 this.hora = hora;
    	 this.user = solicitador;
    	 this.ID = ID;
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

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getPontoDeEncontro() {
		return pontoDeEncontro;
	}

	public void setPontoDeEncontro(String pontoDeEncontro) {
		this.pontoDeEncontro = pontoDeEncontro;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
