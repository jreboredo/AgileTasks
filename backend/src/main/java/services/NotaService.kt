package services

import modelo.Nota

interface NotaService {
    fun agregarNota(nota: Nota)
    fun modificarNota(nota: Nota)
    fun asignarNotaAUser(nota: Nota)
    fun asignarNotaATarea(nota: Nota)
    fun recuperarTodas() : List<Nota>

}