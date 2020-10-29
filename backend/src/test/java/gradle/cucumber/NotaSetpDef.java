package gradle.cucumber;

import Elementos.Ing.AgileTasks.modelo.Nota;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class NotaSetpDef {
    private Nota nota;

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
}
