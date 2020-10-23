package modelo;

import io.cucumber.java.mk_latn.No;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    void unaNotaInicialmenteNoTieneContenido() {
        Note note = new Note("");

        Assertions.assertTrue(note.contenido() == null);
    }

    @Test
    void unaNotaPuedeLlevarUnTitulo() {
        Note note = new Note("Recordar");

        Assertions.assertEquals(note.titulo(),"Recordar");

    }

    @Test
    void unaNotaPuedeTenerContenido() {
        Note note = new Note("Recordar");
        note.editarContenido("Mi papa se llama carlos");
        Assertions.assertEquals(note.contenido(), "Mi papa se llama carlos");
    }

}
