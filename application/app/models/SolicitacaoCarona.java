package models;

import java.util.List;

public class SolicitacaoCarona {
	 private String origem, destino;
	 private java.util.Date data;
	 private long ID;
     private String pontoDeEncontro;
     private User user;
	
     public SolicitacaoCarona(long ID, String origem, String destino, java.util.Date data,
    		 User solicitador, String ponto){
    	 this.origem = origem;
    	 this.destino = destino;
    	 this.data = data;
    	 this.user = solicitador;
    	 this.ID = ID;
    	 this.pontoDeEncontro = ponto;
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
