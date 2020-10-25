package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Se refactorea la clase para que sea aplicable a hibernate
 */

@Entity
public class Tarea extends Text{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime comienzo;
    private LocalDateTime fin;
   // private Prioridad prioridad;
    private Integer prioridad;


    /*public Tarea(String _titulo, String _descripcion, LocalDateTime comienzo, LocalDateTime fin, Prioridad prioridad) {
        this.comienzo = comienzo;
        this.fin = fin;
        this.descripcion = _descripcion;
        this.titulo = _titulo;
        this.prioridad = prioridad;
    }*/

    public Tarea() {
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getComienzo() {
        return comienzo;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setComienzo(LocalDateTime comienzo) {
        this.comienzo = comienzo;
    }

    public void setFin(LocalDateTime fin) {
        this.fin = fin;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }
}
