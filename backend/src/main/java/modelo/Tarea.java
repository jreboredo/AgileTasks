package modelo;

import java.time.LocalDateTime;

public class Tarea extends Text{

    public LocalDateTime comienzo;
    public LocalDateTime fin;
    public Prioridad prioridad;

    public Tarea(String _titulo, String _descripcion, LocalDateTime comienzo, LocalDateTime fin, Prioridad prioridad) {
        this.comienzo = comienzo;
        this.fin = fin;
        this.descripcion = _descripcion;
        this.titulo = _titulo;
        this.prioridad = prioridad;
    }
}
