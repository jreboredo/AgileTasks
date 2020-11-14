package gradle.cucumber;

import Elementos.Ing.AgileTasks.excepciones.NotFoundException;
import Elementos.Ing.AgileTasks.modelo.Tarea;
import Elementos.Ing.AgileTasks.modelo.Usuario;
import Elementos.Ing.AgileTasks.persistencia.runner.dao.UserDAO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import services.UsuarioService;
import services.impl.TareaServiceImpl;
import services.impl.UsuarioServiceImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @When("Seteo la prioridad de la tarea en {string}")
    public void seteoLaPrioridadDeLaTareaEn(String prioridad) {
        if(prioridad.equals("Alta")){
            tarea.setPrioridad(1);
        } else if (prioridad.equals("Media")){
            tarea.setPrioridad(2);
        } else {
            tarea.setPrioridad(3);
        }
    }

    @Then("La tarea se guardó con la prioridad Alta")
    public void laTareaSeGuardóConLaPrioridad() {
        Tarea tareaRecuperada = tareaService.recuperarTareaPorId((int) tarea.getId());
        Assert.assertEquals(tareaRecuperada.getPrioridad().intValue(), 1);
    }

    @Then("La tarea se guardó con la prioridad Media")
    public void laTareaSeGuardóConLaPrioridadMedia() {
        Tarea tareaRecuperada = tareaService.recuperarTareaPorId((int) tarea.getId());
        Assert.assertEquals(tareaRecuperada.getPrioridad().intValue(), 2);
    }

    @Then("La tarea se guardó con la prioridad Baja")
    public void laTareaSeGuardóConLaPrioridadBaja() {
        Tarea tareaRecuperada = tareaService.recuperarTareaPorId((int) tarea.getId());
        Assert.assertEquals(tareaRecuperada.getPrioridad().intValue(), 3);
    }

    @When("Seteo el estado de la tarea a {string}")
    public void seteoElEstadoDeLaTareaA(String estado) {
        if(estado.equals("Hecho")){
            tarea.setCompletada(true);
        } else {
            tarea.setCompletada(false);
        }
    }

    @Then("La tarea está completada")
    public void laTareaEstáCompletada() {
        Assert.assertTrue(tareaService.recuperarTareaPorId((int) tarea.getId()).isCompletada());
    }

    @Then("La tarea no está completada")
    public void laTareaNoEstáCompletada() {
        Assert.assertFalse(tareaService.recuperarTareaPorId((int) tarea.getId()).isCompletada());
    }

    @And("Seteo el comienzo de la tarea en {string}")
    public void seteoElComienzoDeLaTareaEn(String fecha) {
        tarea.setComienzo(LocalDateTime.parse(fecha));
    }

    @And("Seteo el vencimiento de la tarea en {string}")
    public void seteoElVencimientoDeLaTareaEn(String fecha) {
        tarea.setVencimiento(LocalDateTime.parse(fecha));
    }

    @And("La tarea se guardó con el comienzo {string}")
    public void laTareaSeGuardóConElComienzo(String fecha) {
        Assert.assertEquals(tareaService.recuperarTareaPorId((int) tarea.getId()).getComienzo(), LocalDateTime.parse(fecha));
    }

    @And("La tarea se guardó con el vencimiento {string}")
    public void laTareaSeGuardóConElVencimiento(String fecha) {
        Assert.assertEquals(tareaService.recuperarTareaPorId((int) tarea.getId()).getVencimiento(), LocalDateTime.parse(fecha));

    }
}
