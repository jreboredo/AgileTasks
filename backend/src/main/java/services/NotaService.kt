package services

import Elementos.Ing.AgileTasks.modelo.Nota

interface NotaService {
    fun agregarNota(nota: Nota)
    fun recuperarPorId(id : Int): Nota
    fun modificarNota(nota: Nota)
    fun asignarNotaATarea(nota: Nota)
    fun recuperarTodas() : List<Nota>

}