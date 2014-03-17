package testes;
import models.Usuario;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import play.mvc.*;
import play.data.Form;
import play.libs.*;
import play.test.*;
import static play.test.Helpers.*;
import com.avaje.ebean.Ebean;
import com.google.common.collect.ImmutableMap;
import controllers.*;

public class LoginTest extends WithApplication {
    @Before
    public void setUp() throws Exception{
        start(fakeApplication(inMemoryDatabase(), fakeGlobal()));
    }
    
    @Test
    public void renderCadastro() {
      Content html = views.html.cadastro.render(Form<Application.Cadastro.class>);
      assertThat(contentType(html)).isEqualTo("text/html");
      assertThat(contentAsString(html)).contains("Realize seu cadastro");
    }
    
    @Test
    public void authenticateSuccess() {
        Result result = callAction(
            controllers.routes.ref.Application.authenticate(),
            fakeRequest().withFormUrlEncodedBody(ImmutableMap.of(
                "email", "bob@example.com",
                "password", "secret"))
        );
        assertEquals(303, status(result));
        assertEquals("bob@example.com", session(result).get("email"));
    }
    
    @Test
    public void authenticateFailure() {
        Result result = callAction(
            controllers.routes.ref.Application.authenticate(),
            fakeRequest().withFormUrlEncodedBody(ImmutableMap.of(
                "email", "bob@example.com",
                "password", "badpassword"))
        );
        assertEquals(400, status(result));
        assertNull(session(result).get("email"));
    }
    
    @Test
    public void authenticated() {
        Result result = callAction(
            controllers.routes.ref.Application.index(),
            fakeRequest().withSession("email", "bob@example.com")
        );
        assertEquals(200, status(result));
    }   
    @Test
    public void notAuthenticated() {
        Result result = callAction(
            controllers.routes.ref.Application.index(),
            fakeRequest()
        );
        assertEquals(303, status(result));
        assertEquals("/login", header("Location", result));
    }

}