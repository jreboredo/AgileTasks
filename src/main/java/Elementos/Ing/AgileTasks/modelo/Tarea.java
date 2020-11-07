package Elementos.Ing.AgileTasks.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="tareas")
public class Tarea {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String prioridad;
    @ManyToOne(optional = false)
    private Usuario user;


    public Tarea() {
    }

    public LocalDateTime getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(LocalDateTime vencimiento) {
        this.vencimiento = vencimiento;
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

    public String getPrioridad() {
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

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
