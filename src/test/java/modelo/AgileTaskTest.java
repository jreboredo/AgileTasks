package modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AgileTaskTest {


    @Test
    void unaAgileTaskInicialmenteNoTieneNotas() {
        AgileTask ak = new AgileTask();
        Assertions.assertTrue(ak.notas().isEmpty());
    }

    @Test
    void sePuedeAgregarUnaNotaAUnAgileTask() {
        AgileTask ak = new AgileTask();
        Note note = new Note("Recordar");
        ak.agregarNota(note);

        Assertions.assertFalse(ak.notas().isEmpty());
        Assertions.assertEquals(ak.notas().get(0),note);
    }

    @Test
    void sePuedeBuscarUnaNotaPorTitulo() {
        AgileTask ak = new AgileTask();
        Note note1 = new Note("Recordar");
        Note note2 = new Note("Telefonos casa");
        ak.agregarNota(note1);
        ak.agregarNota(note2);

        Note notaRecuperada = ak.buscarNota(note1.titulo());

        Assertions.assertEquals(note1,notaRecuperada);

    }

    @Test
    void noSePuedePedirUnaNotaAUnaAgileTaskVacia() {
        AgileTask ak = new AgileTask();

        Exception exception = Assertions.assertThrows(RuntimeException.class,(() -> {ak.buscarNota("Algo");}));
        Assertions.assertEquals(exception.getMessage(),"La lista de notas esta vacia");
    }

    @Test
    void sePuedeBorrarUnaNotaEnUnAgileTask() {
        AgileTask ak = new AgileTask();
        Note note1 = new Note("Recordar");
        Note note2 = new Note("Telefonos casa");
        ak.agregarNota(note1);
        ak.agregarNota(note2);

        ak.borrarNota(note1);

        Assertions.assertEquals(1,ak.notas().size());

    }

    @Test
    void noSePuedeBorrarUnaNotaEnUnaAgileTaskVacia() {
        AgileTask ak = new AgileTask();
        Note nota = new Note("Recordar");

        Exception exception = Assertions.assertThrows(RuntimeException.class,(() -> {ak.borrarNota(nota);}));
        Assertions.assertEquals(exception.getMessage(),"La lista de notas esta vacia");
    }
}
