package services.Rest

import modelo.Usuario
import org.springframework.web.bind.annotation.*
import services.impl.UsuarioServiceImpl

@ServiceRest
@RequestMapping("/users")
class UsuarioController(private val usuarioServiceImpl: UsuarioServiceImpl) {

    @PostMapping("/addUser")
    fun agregarUsario(usuario: Usuario) {
        usuarioServiceImpl.nuevoUsuario(usuario)
    }

    @PutMapping("/modificarUser")
    fun ModificarUser(usuario: Usuario) {
        usuarioServiceImpl.modificarUsuario(usuario)
    }

    @GetMapping("/userName")
    fun buscarUsuarioPorNombre(nombre: String): Long {
        return usuarioServiceImpl.getId(nombre)
    }

    @GetMapping("/validateUser")
    fun validarUsuario(usuario: Usuario): Boolean {
        return usuarioServiceImpl.validateUser(usuario)
    }
}