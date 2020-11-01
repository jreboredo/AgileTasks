package Elementos.Ing.AgileTasks.modelo;

import javax.persistence.*;

@Entity
@Table(name="notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="titulo")
    private String titulo;
    @Column(name="descr")
    private String descripcion;
    @Column(name="color")
    private String color;
    @ManyToOne(optional = false)
    private Usuario user;
    //private String task;

    public Nota(){}

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descrpicion) {
        this.descripcion = descrpicion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescrpicion() {
        return descripcion;
    }

    public void setUser(Usuario user) {this.user = user;}

    public int getId() { return id; }

    public String getColor() {return color; }

    public void setColor(String color) {this.color = color; }
}
