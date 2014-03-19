package testes;
import java.util.*;
import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import models.*;
import models.Usuario;

import org.junit.Before;
import org.junit.Test;

import controllers.SistemaL;


public class TestCriacaoCaronasESolicitações {

	
	@Before
	public void setUp() throws Exception{
		start(fakeApplication(inMemoryDatabase()));
		Usuario.create(new Usuario("joao@email.com", "joao", "12341234"));
	}
	
	
	@Test
	public void deveCriarCaronaESalvarNoBD() {
		Carona carona = new Carona("Centro", "UFCG", new java.util.Date(System.currentTimeMillis()), "3", 
				Usuario.find.byId("joao@email.com"));
		carona.save();
		assertTrue(Carona.find.byId((long) 1).destino.equals("UFCG"));
		Carona aCarona = Carona.findCarona("joao@email.com");
		assertNotNull(aCarona);
	}
	@Test
	public void deveCriarSolicitacaoESalvarNoBD() {
		SolicitacaoCarona SolicitacaoCarona = new SolicitacaoCarona("Centro", "UFCG", new java.util.Date(System.currentTimeMillis()), 
				Usuario.find.byId("joao@email.com"), "Praça da Bandeira");
		SolicitacaoCarona.save();
		assertTrue(SolicitacaoCarona.find.byId((long) 1).destino.equals("UFCG"));
		SolicitacaoCarona aSolicitacaoCarona = SolicitacaoCarona.findSolicitacao("joao@email.com");
		assertNotNull(aSolicitacaoCarona);
	}
	
	@Test
	public void recuperandoVariasCaronasDeUmUsuário(){
		Carona carona = new Carona("Centro", "UFCG", new java.util.Date(System.currentTimeMillis()), "3", 
				Usuario.find.byId("joao@email.com"));
		carona.save();
		carona = new Carona("Alto Branco", "UFCG", new java.util.Date(System.currentTimeMillis()), "3", 
				Usuario.find.byId("joao@email.com"));
		carona.save();
		carona = new Carona("Bairro Universitário", "UFCG", new java.util.Date(System.currentTimeMillis()), "3", 
				Usuario.find.byId("joao@email.com"));
		carona.save();
		
		List<Carona> caronas = Carona.findInvolving("jogoa@email.com");
		assertTrue(caronas.size() == 3);
	}

}
