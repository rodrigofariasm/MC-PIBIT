package models;

import java.util.List;

public class SolicitacaoCarona {
	 private String origem, destino, data, hora;
	 private long ID;
     private String pontoDeEncontro;
     private User user;
	
     public SolicitacaoCarona(String origem, String destino, String data, String hora, User solicitador, long ID){
    	 this.origem = origem;
    	 this.destino = destino;
    	 this.data = data;
    	 this.hora = hora;
    	 this.user = solicitador;
    	 this.ID = ID;
     }
}
