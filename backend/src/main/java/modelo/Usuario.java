package modelo;

import javax.persistence.*;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  String userName;
    private String password;
    @OneToMany
    private List<Nota> notas;
    @OneToMany
    private List<Tarea> tareas;
    @ManyToMany
    private List<Grupo> grupos;

    /*public Usuario(String password, String userName, List<Nota> notas, List<Tarea> tareas, List<Grupo> grupos) {
        this.password = password;
        this.userName = userName;
        this.notas = notas;
        this.tareas = tareas;
        this.grupos = grupos;
    }*/

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
}
