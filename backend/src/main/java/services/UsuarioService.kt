package services

import Elementos.Ing.AgileTasks.modelo.Usuario


interface UsuarioService {
    fun nuevoUsuario(user: Usuario)
    fun modificarUsuario(user: Usuario)
    fun getId(userName: String): Long
    fun validateUser(user: Usuario): Boolean
}