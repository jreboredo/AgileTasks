package modelo;

public class Note {
    private String contenido;
    private String titulo;

    public Note(String titulo){
        this.titulo = titulo;
        this.contenido = null;
    }

    public String titulo() {
        return titulo;
    }

    public String contenido(){
        return contenido;
    }

    public void editarContenido(String dato){
        this.contenido = dato;
    }
}


