package services

import modelo.Nota

interface NotaService {
    fun agregarNota(nota: Nota)
    fun recuperarPorId(id : Long)
    fun modificarNota(nota: Nota)
    fun asignarNotaATarea(nota: Nota)
    fun recuperarTodas() : List<Nota>

}