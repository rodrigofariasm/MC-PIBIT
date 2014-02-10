import static org.junit.Assert.*;

import models.SistemaL;
import models.User;

import org.junit.Before;
import org.junit.Test;


public class testCriacaoCaronasESolicitações {

private SistemaL sistema;
	
	@Before
	public void setUp() throws Exception{
		sistema = new SistemaL();
		sistema.criaUsuario("abacate@gmail.com", "123456");
		sistema.iniciarSessao(sistema.getUserPorEmail("abacate@gmail.com"));
		
	}
	
	
	@Test
	public void deveCriarCarona() throws Exception {
		sistema.criarCarona("Centro", "UFCG", new java.util.Date(2014, 02, 9, 15, 30), "4", 1);
		assertEquals(1, sistema.getCaronas().size());
		assertEquals(sistema.getCaronas().get(0).getDestino(), "UFCG");
		assertEquals(sistema.getCaronas().get(0).getData().getDate(), 9);
	}

}
