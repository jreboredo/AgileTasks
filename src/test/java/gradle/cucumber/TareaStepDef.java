package gradle.cucumber;

import Elementos.Ing.AgileTasks.excepciones.NotFoundException;
import Elementos.Ing.AgileTasks.modelo.Nota;
import Elementos.Ing.AgileTasks.modelo.Tarea;
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
import services.impl.TareaServiceImpl;
import services.impl.UsuarioServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TareaStepDef {

    private Tarea tarea;
    private TareaServiceImpl tareaService = new TareaServiceImpl();
    private UsuarioService userService = new UsuarioServiceImpl();
    private UserDAO userDAO = new UserDAO();
    private Usuario user = new Usuario();

    @And("una tarea vacia")
    public void unaTareaVacia() {
        tarea = new Tarea();
        tarea.setUser(user);
        LocalDateTime localDate = LocalDateTime.now();
        tarea.setFin(localDate);
        tarea.setVencimiento(localDate);
        tarea.setComienzo(localDate);
        tarea.setPrioridad(1);
    }

    @Given("Un usuario logeado con contraseña {string}, userName {string}")
    public void unUsuarioLogeadoConContraseñaYUserName(String contraseña, String userName) {
        user.setUserName(userName);
        user.setPassword(contraseña);
        user.setEmail("email@gmail.com");
        userService.nuevoUsuario(user);
    }

    @When("Seteo el titulo de la tarea con {string}")
    public void seteoElTituloDeLaTareaCon(String titulo) {
        tarea.setTitulo(titulo);
    }

    @And("Seteo la descripcion de la tarea con {string}")
    public void seteoLaDescripcionDeLaTareaCon(String descripcion) {
        tarea.setDescripcion(descripcion);
    }

    @And("Guardo la tarea")
    public void guardoLaTarea() {
        tareaService.agregarTarea(tarea);
    }

    @Then("La tarea se guardó con el titulo {string} y la descripcion {string}")
    public void laTareaSeGuardóConElTituloYLaDescripcion(String titulo, String descripcion) {
        Tarea tareaRecuperada = tareaService.recuperarTareaPorId((int) tarea.getId());

        Assert.assertEquals(tareaRecuperada.getTitulo(), titulo);
        Assert.assertEquals(tareaRecuperada.getDescripcion(), descripcion);
    }

    @And("Una tarea con titulo {string}, descripcion {string}")
    public void unaTareaConTituloDescripcion(String titulo, String descripcion) {
        tarea = new Tarea();
        tarea.setUser(user);
        LocalDateTime localDate = LocalDateTime.now();
        tarea.setFin(localDate);
        tarea.setVencimiento(localDate);
        tarea.setComienzo(localDate);
        tarea.setPrioridad(1);
        tarea.setTitulo(titulo);
        tarea.setDescripcion(descripcion);
        tareaService.agregarTarea(tarea);
    }

    @And("Actualizo la tarea")
    public void actualizoLaTarea() {
        tareaService.modificarTarea(tarea);
    }

    @When("elimino la tarea")
    public void eliminoLaTarea() {
        tareaService.eliminar(tarea);
    }

    @Then("la tarea se eliminó")
    public void laTareaSeEliminó() {
        Assertions.assertThrows(NotFoundException.class, () -> tareaService.recuperarTareaPorId((int) tarea.getId()));

    }
}
