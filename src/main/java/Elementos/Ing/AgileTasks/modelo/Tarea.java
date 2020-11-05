package Elementos.Ing.AgileTasks.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="tareas")
public class Tarea {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="titulo")
    private String titulo;
    @Column(name="descrip")
    private String descripcion;
    @Column(name="start_date")
    private LocalDateTime comienzo;
    @Column(name="end_date")
    private LocalDateTime fin;
    @Column(name="priority")
    private Integer prioridad;
    @Column(name="vencimiento")
    private LocalDateTime vencimiento;
    @ManyToOne(optional = false)
    private Usuario user;


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
