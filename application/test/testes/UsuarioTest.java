package testes;
import static org.junit.Assert.*;

import org.junit.Test;

import models.*;
import exceptions.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

public class UsuarioTest extends WithApplication {
    @Before
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }
    
    @Test
    public void criarEpegarUsuario()throws Exception {
        Usuario.create(new Usuario("bob@gmail.com","Bob", "secret"));
        Usuario bob = Usuario.find.where().eq("email", "bob@gmail.com").findUnique();
        assertNotNull(bob);
        assertEquals("bob@gmail.com", bob.email);
    }
    
    @Test
    public void tryAuthenticateUsuario() throws Exception{
    	Usuario.create(new Usuario("bob@gmail.com", "Bob", "secret"));
        assertNull(Usuario.authenticate("bob@gmail.com", "secret"));
        try{ Usuario.authenticate("bob@gmail.com", "badpassword");
        }catch(Exception e){
        	assertTrue(e.getClass().equals(SenhaIncorretaException.class));
        }
        try{
        assertNotNull(Usuario.authenticate("tom@gmail.com", "secret"));
        }catch(Exception e){
        	assertTrue(e.getClass().equals(UsuarioNaoEncontradoException.class));
        }
    }
}
