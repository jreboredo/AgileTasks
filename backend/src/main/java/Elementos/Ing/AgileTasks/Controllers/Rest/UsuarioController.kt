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

    @PutMapping("/modificarUser/{id}")
    fun ModificarUser(@PathVariable id: Int, @RequestBody userNuevo : Usuario) {
        val userViejo : Usuario = buscarUsuarioPorId(id)
        userViejo.userName = userNuevo.userName
        userViejo.password = userNuevo.password

        usuarioServiceImpl.modificarUsuario(userViejo)
    }

    @GetMapping("/userById/{id}")
    fun buscarUsuarioPorId(@PathVariable id: Int): Usuario {
        return usuarioServiceImpl.getUsuarioById(id)
    }

    @PostMapping("/DeleteUser/{id}")
    fun deleteUser(@PathVariable id: Int) {
        val usuario : Usuario = buscarUsuarioPorId(id)
        usuarioServiceImpl.eliminarUsuario(usuario)
    }

    @GetMapping("/validateUser")
    fun validarUsuario(@RequestBody usuario: Usuario): Boolean {
        return usuarioServiceImpl.validateUser(usuario)
    }
}