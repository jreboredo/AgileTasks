package api

import Elementos.Ing.AgileTasks.Controllers.Rest.UsuarioController
import Elementos.Ing.AgileTasks.excepciones.NotFoundException
import Elementos.Ing.AgileTasks.modelo.Usuario
import org.junit.Assert
import org.junit.jupiter.api.*
import utils.DataServiceImpl
import javax.persistence.NoResultException

class UsuarioControllerTest {

    val userController = UsuarioController()
    val usuario = Usuario()

    @BeforeEach
    fun before(){
        usuario.password = "1234"
        usuario.userName = "name"

        userController.agregarUsario(usuario)
    }

    @Test
    fun agregarUsuarioTest(){
        val nuevoUsuario = Usuario()
        nuevoUsuario.password = "1234"
        nuevoUsuario.userName = "admin"

        userController.agregarUsario(nuevoUsuario)

        val usuarioRecuperado = userController.buscarUsuarioPorId(nuevoUsuario.id.toInt())

        Assert.assertEquals(nuevoUsuario.id, usuarioRecuperado.id)
        Assert.assertEquals(nuevoUsuario.password, usuarioRecuperado.password)
        Assert.assertEquals(nuevoUsuario.userName, usuarioRecuperado.userName)

    }

    @Test
    fun actualizarUsuarioTest(){
        val nuevoUsuario = Usuario()
        nuevoUsuario.password = "otraPassword"
        nuevoUsuario.userName = "otroUserName"

        userController.ModificarUser(usuario.userName, nuevoUsuario)

        val usuarioRecuperado = userController.buscarUsuarioPorId(usuario.id.toInt())

        Assert.assertEquals(usuarioRecuperado.id, usuarioRecuperado.id)
        Assert.assertEquals(usuarioRecuperado.password, usuarioRecuperado.password)
        Assert.assertEquals(usuarioRecuperado.userName, usuarioRecuperado.userName)
    }

    @Test
    fun eliminarUsuarioTest(){
        userController.deleteUser(usuario.id.toInt())

        Assertions.assertThrows(NoResultException::class.java){
            userController.buscarUsuarioPorId(usuario.id.toInt())
        }
    }

    @Test
    fun validadUsuarioTest(){
        Assert.assertEquals(userController.validarUsuario(usuario), usuario.userName)
        Assertions.assertThrows(NoResultException::class.java){
            userController.validarUsuario(Usuario())
        }
    }

    @Test
    fun recuperarPorIdTest(){
        val usuarioRecuperado = userController.buscarUsuarioPorId(usuario.id.toInt())

        Assert.assertEquals(usuario.id, usuarioRecuperado.id)
        Assert.assertEquals(usuario.password, usuarioRecuperado.password)
        Assert.assertEquals(usuario.userName, usuarioRecuperado.userName)

        Assertions.assertThrows(NoResultException::class.java){
            userController.buscarUsuarioPorId(-1)
        }
    }

    @AfterEach
    fun after(){
        DataServiceImpl().deleteAll()
    }
}