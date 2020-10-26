package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String titulo;
    private String descrpicion;

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
}
