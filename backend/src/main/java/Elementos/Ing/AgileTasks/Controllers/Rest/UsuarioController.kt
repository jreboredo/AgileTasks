package Elementos.Ing.AgileTasks.Controllers.Rest

import Elementos.Ing.AgileTasks.modelo.Usuario
import org.springframework.web.bind.annotation.*
import services.impl.UsuarioServiceImpl


@RestController
@RequestMapping("/users")
class UsuarioController() {
    private val usuarioServiceImpl: UsuarioServiceImpl = UsuarioServiceImpl()

    @PostMapping("/addUser")
    fun agregarUsario(@RequestBody usuario: Usuario) {
        usuarioServiceImpl.nuevoUsuario(usuario)
    }

    @PutMapping("/modificarUser")
    fun ModificarUser(@RequestBody userName: String, @RequestBody password : String) {
        var userId = usuarioServiceImpl.getId(userName)
        var usuario : Usuario = Usuario()
        usuario.userName = userName
        usuario.password = password
        usuarioServiceImpl.modificarUsuario(usuario)
    }

    @GetMapping("/userName")
    fun buscarUsuarioPorNombre(@RequestBody nombre: String): Long {
        return usuarioServiceImpl.getId(nombre)
    }

    @GetMapping("/validateUser")
    fun validarUsuario(@RequestBody usuario: Usuario): Boolean {
        return usuarioServiceImpl.validateUser(usuario)
    }
}