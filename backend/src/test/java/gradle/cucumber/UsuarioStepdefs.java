package gradle.cucumber;

import Elementos.Ing.AgileTasks.modelo.Usuario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import services.UsuarioService;
import services.impl.UsuarioServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsuarioStepdefs {

    Usuario usuario = new Usuario();
    UsuarioService usuarioService = new UsuarioServiceImpl();

    @Given("un usuario no registrado con userName {string}, password {string} y email {string}")
    public void unUsuarioNoRegistradoConUserNamePasswordYEmail(String userName, String password, String email) {

        usuario.setEmail(email);
        usuario.setUserName(userName);
        usuario.setPassword(password);

    }

    @When("se registra el usuario")
    public void seRegistraElUsuario() {
        usuarioService.nuevoUsuario(usuario);
    }

    @Then("el usuario está registrado")
    public void elUsuarioEstáRegistrado() {

        Usuario usuarioRecuperado = usuarioService.getId((int) usuario.getId());

        assertEquals(usuarioRecuperado.getPassword(), usuario.getPassword());
        assertEquals(usuarioRecuperado.getUserName(), usuario.getUserName());
        assertEquals(usuarioRecuperado.getId(), usuario.getId());
        assertEquals(usuarioRecuperado.getEmail(), usuario.getEmail());
    }

    @Then("el usuario está logeado")
    public void elUsuarioEstáLogeado() {
        assertTrue(usuarioService.validateUser(usuario));
    }

    @Given("un usuario registrado con userName {string}, password {string} y email {string}")
    public void unUsuarioRegistradoConUserNamePasswordYEmail(String userName, String password, String email) {
        usuario.setEmail(email);
        usuario.setUserName(userName);
        usuario.setPassword(password);
        usuarioService.nuevoUsuario(usuario);
    }

    @When("cambio la contraseña del usuario a {string}")
    public void cambioLaContraseñaDelUsuarioA(String nuevaContraseña) {
        usuario.setPassword(nuevaContraseña);
        usuarioService.modificarUsuario(usuario);
    }

    @Then("el usuario tiene como contraseña {string}")
    public void elUsuarioTieneComoContraseña(String nuevaContraseña) {

        assertEquals(usuario.getPassword(), nuevaContraseña);
    }
}
