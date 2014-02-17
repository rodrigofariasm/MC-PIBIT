import static org.junit.Assert.*;

import models.Usuario;

import org.junit.Before;
import org.junit.Test;

import controllers.SistemaL;


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
		sistema.criarCarona("Centro", "UFCG", 10, 30, "4", 1);
		assertEquals(1, sistema.getCaronas().size());
		assertEquals(sistema.getCaronas().get(0).getDestino(), "UFCG");
		assertEquals(sistema.getCaronas().get(0).getData().getDate(), 9);
	}

}
