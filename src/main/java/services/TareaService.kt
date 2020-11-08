package services

import Elementos.Ing.AgileTasks.modelo.Tarea

interface TareaService {
    fun agregarTarea(tarea: Tarea)
    fun recuperarTareaPorId(id : Int): Tarea
    fun recuperarPorUserId(id : Int) : List<Tarea>
    fun modificarTarea(tarea: Tarea)
    fun recuperarTodas() : List<Tarea>
    fun eliminar(tarea : Tarea)
    fun recuperarPorUserName(userName: String): List<Tarea>
}