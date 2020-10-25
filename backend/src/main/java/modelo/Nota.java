package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Refactoreo la clase para poder settearla como una entidad de hibernate, que se genere de esta manera
 * el id automaticamente y por otro lado poder utilizar setters para los updates
 *
 */

@Entity
public class Nota extends Text{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String titulo;
    private String descrpicion;

    /*
    public Nota(String _titulo, String _descripcion){
        this.titulo = _titulo;
        this.descripcion = _descripcion;
    }*/
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
