package services

import Elementos.Ing.AgileTasks.modelo.Usuario


interface UsuarioService {
    fun nuevoUsuario(user: Usuario)
    fun modificarUsuario(user: Usuario)
    fun eliminarUsuario(user: Usuario)
    fun getId(id : Int): Usuario
    fun validateUser(userName: String, password: String): Usuario?
    fun getUserByName(userName: String): Usuario
}