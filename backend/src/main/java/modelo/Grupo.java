package modelo;

import java.util.List;

public class Grupo {

    public Usuario administrador;
    public List<Usuario> integrantes;

    public Grupo(Usuario administrador, List<Usuario> integrantes) {
        this.administrador = administrador;
        this.integrantes = integrantes;
    }
}
