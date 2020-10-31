package Elementos.Ing.AgileTasks.Controllers.Rest

import Elementos.Ing.AgileTasks.modelo.Usuario
import org.springframework.web.bind.annotation.*
import services.impl.UsuarioServiceImpl
import kotlin.Exception


@RestController
@RequestMapping("/users")
class UsuarioController() {
    private val usuarioServiceImpl: UsuarioServiceImpl = UsuarioServiceImpl()

    @PostMapping("/addUser")
    fun agregarUsario(@RequestBody usuario: Usuario) {
        usuarioServiceImpl.nuevoUsuario(usuario)
    }

    @PutMapping("/modificarUser/{userName}")
    fun ModificarUser(@PathVariable userName: String, @RequestBody userNuevo : Usuario) {
        val userViejo : Usuario = buscarUsuarioPorUserName(userName)
        userViejo.email = userNuevo.email
        userViejo.password = userNuevo.password

        usuarioServiceImpl.modificarUsuario(userViejo)
    }

    @GetMapping("/userById/{id}")
    fun buscarUsuarioPorId(@PathVariable id: Int): Usuario {
        return usuarioServiceImpl.getId(id)
    }

    @GetMapping("/userByUserName/{userName}")
    fun buscarUsuarioPorUserName(@PathVariable userName : String) : Usuario {
        return usuarioServiceImpl.getUserByName(userName)
    }

    @PostMapping("/DeleteUser/{id}")
    fun deleteUser(@PathVariable id: Int) {
        val usuario : Usuario = buscarUsuarioPorId(id)
        usuarioServiceImpl.eliminarUsuario(usuario)
    }

    @GetMapping("/validateUser")
    fun validarUsuario(@RequestBody usuario: Usuario): String {
        if (usuarioServiceImpl.validateUser(usuario)) {
            return usuario.userName
        }
        else {throw Exception("No existe este usuario")}
    }
}