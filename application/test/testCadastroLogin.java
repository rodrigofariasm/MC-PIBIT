import static org.junit.Assert.*;
import models.*;

import org.junit.Test;
import org.junit.Before;

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
		User u = new User((long)2, "castanha@gmail.com", "123456");
		assertEquals(sistema.getUsers().size(), 2);
		assertEquals(u, sistema.getUsers().get(1));
		assertEquals(retorno, "Cadastrado com sucesso");
		retorno = sistema.criaUsuario("castanha@gmail.com", "12346");
		assertEquals(retorno, "email já cadastrado");
	}
	
	@Test
	public void aoFazerLoginSessaoDeveSerIniciada() throws Exception{
		sistema.iniciarSessao(sistema.getUserPorEmail("abacate@gmail.com"));
		assertTrue(sistema.getUserPorEmail("abacate@gmail.com").isSessaoAtiva());
	}

}