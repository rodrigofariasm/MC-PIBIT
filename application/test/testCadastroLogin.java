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
		User v = new User((long) 3, "castanha@gmail.com", "12346");
		retorno = sistema.criaUsuario("castanha@gmail.com", "12346");
		assertEquals(retorno, "email já cadastrado");
		assertFalse(sistema.getUsers().contains(v));
	}
	
	@Test
	public void aoFazerLoginSessaoDeveSerIniciada(){
		
	}

}
