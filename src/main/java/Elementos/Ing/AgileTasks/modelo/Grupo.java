package Elementos.Ing.AgileTasks.modelo;

import javax.persistence.*;
import java.util.List;

@Entity
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Usuario administrador;
    @ManyToMany
    private List<Usuario> integrantes;

    /*public Grupo(Usuario administrador, List<Usuario> integrantes) {
        this.administrador = administrador;
        this.integrantes = integrantes;
    }*/

    public Grupo() {
    }

//    public long getId() {
//        return id;
//    }

    public Usuario getAdministrador() {
        return administrador;
    }

    public List<Usuario> getIntegrantes() {
        return integrantes;
    }

    public void setAdministrador(Usuario administrador) {
        this.administrador = administrador;
    }

    public void setIntegrantes(List<Usuario> integrantes) {
        this.integrantes = integrantes;
    }
}
