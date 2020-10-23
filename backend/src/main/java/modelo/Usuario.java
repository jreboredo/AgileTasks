package modelo;

import java.util.List;

public class Usuario {

    private final String password;
    private final String userName;
    private List<Nota> notas;
    private List<Tarea> tareas;
    private List<Grupo> grupos;

    public Usuario(String password, String userName, List<Nota> notas, List<Tarea> tareas, List<Grupo> grupos) {
        this.password = password;
        this.userName = userName;
        this.notas = notas;
        this.tareas = tareas;
        this.grupos = grupos;
    }
}
