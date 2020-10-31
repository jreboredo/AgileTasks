package gradle.cucumber;

import Elementos.Ing.AgileTasks.excepciones.NotFoundException;
import Elementos.Ing.AgileTasks.modelo.Nota;
import Elementos.Ing.AgileTasks.modelo.Usuario;
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
    private Usuario user = new Usuario();

    @Given("Nota vacia")
    public void notaVacia() {
        nota = new Nota();
    }

    @When("Seteo el titulo con {string}")
    public void seteoElTituloCon(String titulo) {
        nota.setTitulo(titulo);
    }

    @Then("El titulo tiene {string}")
    public void elTituloTiene(String titulo) {
        Assert.assertEquals(nota.getTitulo(), titulo);
    }

    @When("Seteo la descripcion con {string}")
    public void seteoLaDescripcionCon(String descripcion) {
        nota.setDescripcion(descripcion);
    }

    @Then("La descripcion tiene {string}")
    public void laDescripcionTiene(String descripcion) {
        Assert.assertEquals(nota.getDescrpicion(), descripcion);
    }

    @Given("una nota con titulo {string}, descripcion {string}")
    public void unaNotaConTituloDescripcion(String titulo, String descripcion) {
        nota = new Nota();
        userService.nuevoUsuario(user);
        nota.setTitulo(titulo);
        nota.setDescripcion(descripcion);
        nota.setUser(user);
        notaService.agregarNota(nota);
    }

    @When("Guardo la nota")
    public void guardoLaNota() {
        notaService.agregarNota(nota);
    }

    @Then("La nota se guardó con su titulo y descripcion")
    public void laNotaSeGuardóConSuTituloYDescripcion() {
        Nota notarecuperada = notaService.recuperarPorId(nota.getId());

        Assert.assertEquals(notarecuperada.getTitulo(), nota.getTitulo());
        Assert.assertEquals(notarecuperada.getDescrpicion(), nota.getDescrpicion());
        Assert.assertEquals(notarecuperada.getId(), nota.getId());
    }

    @When("Actualizo la descripcion con {string}")
    public void actualizoLaDescripcionCon(String nuevaDescripcion) {
        nota.setDescripcion(nuevaDescripcion);
        notaService.modificarNota(nota);
    }

    @When("Actualizo el titulo con {string}")
    public void actualizoElTituloCon(String nuevoTitulo) {
        nota.setTitulo(nuevoTitulo);
        notaService.modificarNota(nota);
    }

    @Then("Se actualizo la descripcion con {string}")
    public void seActualizoLaDescripcionCon(String descripcion) {
        Nota notaRecuperada = notaService.recuperarPorId(nota.getId());
        Assert.assertEquals(notaRecuperada.getDescrpicion(), descripcion);
    }

    @Then("Se actualizo el titulo con {string}")
    public void seActualizoElTituloCon(String titulo) {
        Nota notaRecuperada = notaService.recuperarPorId(nota.getId());
        Assert.assertEquals(notaRecuperada.getTitulo(), titulo);
    }

    @When("elimino la nota")
    public void eliminoLaNota() {
        notaService.eliminar(nota);
    }

    @Then("la nota se eliminó")
    public void laNotaSeEliminó() {
        Assertions.assertThrows(NotFoundException.class, () -> notaService.recuperarPorId(nota.getId()));
    }
}
