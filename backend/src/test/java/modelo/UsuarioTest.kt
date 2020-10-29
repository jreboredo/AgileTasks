package modelo

import Elementos.Ing.AgileTasks.modelo.Usuario
import org.junit.Assert
import org.junit.jupiter.api.Test

class UsuarioTest {

    var usuario : Usuario = Usuario()

    @Test
    fun usuarioTest(){
        usuario.password = "1234"
        usuario.userName = "admin"

        Assert.assertEquals(usuario.userName, "admin")
        Assert.assertEquals(usuario.password, "1234")
    }
}