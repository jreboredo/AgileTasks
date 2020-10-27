package services

import modelo.Usuario


interface UsuarioService {
    fun nuevoUsuario(user: Usuario)
    fun modificarUsuario(user: Usuario)
    fun getId(userName: String): Long
    fun validateUser(user: Usuario): Boolean
}