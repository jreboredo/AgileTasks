package modelo;

import Elementos.Ing.AgileTasks.modelo.Nota;
import Elementos.Ing.AgileTasks.modelo.Usuario;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotaTest {
    Nota nota = new Nota();
    Usuario usuario = new Usuario();

    @Test
    public void notaTest(){
        nota.setDescripcion("Descripcion");
        nota.setTitulo("Titulo");
        nota.setUser(usuario);

        assertEquals(nota.getDescrpicion(), "Descripcion");
        assertEquals(nota.getTitulo(), "Titulo");
    }
}
