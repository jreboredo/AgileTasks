package modelo;

import Elementos.Ing.AgileTasks.modelo.Tarea;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TareaTest {

    Tarea tarea = new Tarea();

    @Test
    public void tareaTest(){
        LocalDateTime localDate = LocalDateTime.now();
        tarea.setComienzo(localDate);
        tarea.setFin(localDate);
        tarea.setDescripcion("descripcion");
        tarea.setTitulo("titulo");
        tarea.setPrioridad("high");

        assertEquals(tarea.getDescripcion(), "descripcion");
        assertEquals(tarea.getTitulo(), "titulo");
        assertEquals(tarea.getPrioridad(), "high");
        assertEquals(tarea.getComienzo(), localDate);
        assertEquals(tarea.getFin(), localDate);
    }

}
