package services

import Elementos.Ing.AgileTasks.modelo.Nota

interface NotaService {
    fun agregarNota(nota: Nota):Nota
    fun recuperarPorId(id : Int): Nota
    fun recuperarPorUserId(id : Int) : List<Nota>
    fun modificarNota(nota: Nota)
    fun recuperarTodas() : List<Nota>
    fun eliminar(nota : Nota)
    fun recuperarPorUserName(userName: String): List<Nota>
}