package modelo;


import java.util.ArrayList;

public class AgileTask {
    private ArrayList<Note> notas = new ArrayList<Note>();

    public ArrayList<Note> notas() {
        return notas;
    }

    public void agregarNota(Note note){
        notas.add(note);
    }

    public Note buscarNota(String titulo){
        if(notas.isEmpty()) throw new RuntimeException("La lista de notas esta vacia");
        return notas.stream().filter((it) -> it.titulo() == titulo).findFirst().get();
    }

    public void borrarNota(Note nota){
        if(notas.isEmpty()) throw new RuntimeException("La lista de notas esta vacia");
        notas.remove(nota);
    }
}
