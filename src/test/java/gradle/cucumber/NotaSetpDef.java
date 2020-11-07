package gradle.cucumber;

import Elementos.Ing.AgileTasks.excepciones.NotFoundException;
import Elementos.Ing.AgileTasks.modelo.Nota;
import Elementos.Ing.AgileTasks.modelo.Usuario;
import Elementos.Ing.AgileTasks.persistencia.runner.dao.UserDAO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import services.NotaService;
import services.UsuarioService;
import services.impl.NotaServiceImpl;
import services.impl.UsuarioServiceImpl;

public class NotaSetpDef {
    private Nota nota;
    private NotaService notaService = new NotaServiceImpl();
    private UsuarioService userService = new UsuarioServiceImpl();
    private UserDAO userDAO = new UserDAO();
    private Usuario user = new Usuario();

    @Given("Nota vacia")
    public void notaVacia() {
        nota = new Nota();
        nota.setUser(user);
    }

    @When("Seteo el titulo con {string}")
    public void seteoElTituloCon(String titulo) {
        nota.setTitulo(titulo);
    }

    @When("Seteo la descripcion con {string}")
    public void seteoLaDescripcionCon(String descripcion) {
        nota.setDescripcion(descripcion);
    }

    @Given("una nota con titulo {string}, descripcion {string}")
    public void unaNotaConTituloDescripcion(String titulo, String descripcion) {
        nota = new Nota();
        nota.setTitulo(titulo);
        nota.setDescripcion(descripcion);
        nota.setUser(user);
        notaService.agregarNota(nota);
    }

    @When("Guardo la nota")
    public void guardoLaNota() {
        notaService.agregarNota(nota);
    }

    @When("Actualizo la descripcion con {string}")
    public void actualizoLaDescripcionCon(String nuevaDescripcion) {
        Nota notaRecuperada = notaService.recuperarPorId(nota.getId());
        notaRecuperada.setDescripcion(nuevaDescripcion);
        notaService.modificarNota(notaRecuperada);
    }

    @And("Actualizo el titulo con {string}")
    public void actualizoElTituloCon(String nuevoTitulo) {
        Nota notaRecuperada = notaService.recuperarPorId(nota.getId());
        notaRecuperada.setTitulo(nuevoTitulo);
        notaService.modificarNota(notaRecuperada);
    }

    @When("elimino la nota")
    public void eliminoLaNota() {
        notaService.eliminar(nota);
    }

    @Then("la nota se eliminó")
    public void laNotaSeEliminó() {
        Assertions.assertThrows(NotFoundException.class, () -> notaService.recuperarPorId(nota.getId()));
    }

    @Then("La nota se guardó con el titulo {string} y la descripcion {string}")
    public void laNotaSeGuardóConElTituloYDescripcionLaDescripcion(String titulo, String descripcion) {
        Nota notarecuperada = notaService.recuperarPorId(nota.getId());

        Assert.assertEquals(notarecuperada.getTitulo(), titulo);
        Assert.assertEquals(notarecuperada.getDescrpicion(), descripcion);
    }
    @Given("Un usuario logeado con contraseña {string} y userName {string}")
    public void unUsuarioLogeadoConContraseñaYUserName(String contraseña, String userName) {
        user.setUserName(userName);
        user.setPassword(contraseña);
        user.setEmail("email@gmail.com");
        userService.nuevoUsuario(user);
    }

    @And("Actualizo la nota")
    public void actualizoLaNota() {
        notaService.modificarNota(nota);
    }

}
