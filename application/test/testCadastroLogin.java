import static org.junit.Assert.*;
import models.*;

import org.junit.Test;
import org.junit.Before;

import controllers.SistemaL;

public class testCadastroLogin {

	private SistemaL sistema;
	
	@Before
	public void setUp(){
		sistema = new SistemaL();
		sistema.criaUsuario("abacate@gmail.com", "123456");
		
	}
	
	
	@Test
	public void deveCadastrarNovoUsuario() {
		String retorno = sistema.criaUsuario("castanha@gmail.com", "123456");
		Usuario u = new Usuario( "castanha@gmail.com", "123456");
		assertEquals(sistema.getUsers().size(), 2);
		assertEquals(u, sistema.getUsers().get(1));
		assertEquals(retorno, "Cadastrado com sucesso");
		retorno = sistema.criaUsuario("castanha@gmail.com", "12346");
		assertEquals(retorno, "email já cadastrado");
	}
	
	@Test
	public void aoFazerLoginSessaoDeveSerIniciada() throws Exception{
		Usuario temp = new Usuario("abacate@gmail.com", "123456");
		sistema.iniciarSessao(temp);
		assertTrue(sistema.getUserPorEmail("abacate@gmail.com").isSessaoAtiva());
	}
	
	@Test
	public void aoFazerLogoffSessaoDeveSerEncerrada() throws Exception{
		Usuario temp = new Usuario("abacate@gmail.com", "123456");
		sistema.iniciarSessao(temp);
		sistema.logoff(sistema.getUserPorEmail("abacate@gmail.com").getId());
		assertFalse(sistema.getUserPorEmail("abacate@gmail.com").isSessaoAtiva());
	}

}
