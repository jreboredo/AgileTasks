package modelo;

import javax.persistence.*;

@Entity
@Table(name="notas")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;
    @Column(name="titulo")
    private String titulo;
    @Column(name="descr")
    private String descrpicion;
    @Column(name="user_id")
    @JoinTable(name = "usuarios", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private int user_id;
    //private String task;

    public Nota(){}

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descrpicion) {
        this.descrpicion = descrpicion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescrpicion() {
        return descrpicion;
    }

    public void setUser(int id) {this.user_id = id;}
}
