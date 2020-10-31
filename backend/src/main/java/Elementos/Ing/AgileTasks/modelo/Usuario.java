package Elementos.Ing.AgileTasks.modelo;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    //@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Id
    @Column(name = "username")
    private  String userName;
    @Column(name="pass")
    private String password;
    @Column(name="email")
    private String email;


/*@OneToMany
    private List<Tarea> tareas;
    @ManyToMany
    private List<Grupo> grupos;*/

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id; }


    /*
    public List<Tarea> getTareas() {
        return tareas;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }
*/
    public void setUserName(String userName) { this.userName = userName; }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

/*
    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }*/
}
