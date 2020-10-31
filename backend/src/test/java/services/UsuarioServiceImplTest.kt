package services

import Elementos.Ing.AgileTasks.excepciones.NotFoundException
import Elementos.Ing.AgileTasks.modelo.Usuario
import Elementos.Ing.AgileTasks.persistencia.runner.TransactionRunner.runTrx
import Elementos.Ing.AgileTasks.persistencia.runner.dao.UserDAO
import org.junit.Assert
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import services.impl.UsuarioServiceImpl
import utils.DataServiceImpl
import javax.persistence.NoResultException

class UsuarioServiceImplTest {

    val userService : UsuarioService = UsuarioServiceImpl()
    val userDAO : UserDAO = UserDAO()
    val usuario : Usuario = Usuario()

    @BeforeEach
    fun before(){
        usuario.password = "1234"
        usuario.userName = "user"
        usuario.email = "email"

        userService.nuevoUsuario(usuario)
    }

    @Test
    fun nuevoUsuarioTest(){
        val usuarioNuevo = Usuario()
        usuarioNuevo.password = "1234"
        usuarioNuevo.userName = "usuario"
        usuarioNuevo.email = "email"


        userService.nuevoUsuario(usuarioNuevo)

        val usuarioRecuperado = runTrx{
            userDAO.recuperar(usuarioNuevo.userName)
        }

        Assert.assertEquals(usuarioNuevo.userName, usuarioRecuperado.userName)
        Assert.assertEquals(usuarioNuevo.password, usuarioRecuperado.password)
        Assert.assertEquals(usuarioNuevo.email, usuarioRecuperado.email)
        Assert.assertEquals(usuarioNuevo.id, usuarioRecuperado.id)
    }

    @Test
    fun modificarUsuarioTest(){
        usuario.password = "otraPassword"
        usuario.email = "otro"

        userService.modificarUsuario(usuario)

        val usuarioRecuperado = runTrx{
            userDAO.recuperar(usuario.userName)
        }

        Assert.assertEquals(usuario.userName, usuarioRecuperado.userName)
        Assert.assertEquals(usuario.password, usuarioRecuperado.password)
        Assert.assertEquals(usuario.email, usuarioRecuperado.email)
        Assert.assertEquals(usuario.id, usuarioRecuperado.id)
    }

    @Test
    fun getUserByIdTest(){
        val user = userService.getId(usuario.id.toInt())

        Assert.assertEquals(user.id, usuario.id)
        Assert.assertEquals(user.userName, usuario.userName)
        Assert.assertEquals(user.password, usuario.password)
        Assert.assertEquals(user.email, usuario.email)

        Assertions.assertThrows(NoResultException::class.java){
            userService.getId(-1)
        }

    }

    @Test
    fun validateUserTest(){

        Assert.assertTrue(userService.validateUser(usuario))

        val usuarioNoPersistido = Usuario()
        usuarioNoPersistido.userName = "random"
        usuarioNoPersistido.password = "1234"
        usuarioNoPersistido.email = "email"

        Assertions.assertThrows(NoResultException::class.java){
            userService.validateUser(usuarioNoPersistido)
        }

        Assertions.assertThrows(NoResultException::class.java){
            userService.validateUser(Usuario())
        }
    }

    @Test
    fun eliminarUsuarioTest(){
        userService.eliminarUsuario(usuario)

        Assertions.assertThrows(NoResultException::class.java){
            userService.getId(usuario.id.toInt())
        }
    }

    @AfterEach
    fun after(){
        DataServiceImpl().deleteAll()
    }
}