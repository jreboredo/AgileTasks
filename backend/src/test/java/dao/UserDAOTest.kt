package dao

import Elementos.Ing.AgileTasks.Controllers.Rest.UsuarioValidacion
import Elementos.Ing.AgileTasks.excepciones.NotFoundException
import Elementos.Ing.AgileTasks.modelo.Usuario
import Elementos.Ing.AgileTasks.persistencia.runner.TransactionRunner.runTrx
import Elementos.Ing.AgileTasks.persistencia.runner.dao.UserDAO
import org.junit.Assert

import org.junit.jupiter.api.*
import utils.DataServiceImpl
import javax.persistence.NoResultException

class UserDAOTest {

    val userDAO : UserDAO = UserDAO()
    val usuario : Usuario = Usuario()
    val dataService : DataServiceImpl = DataServiceImpl()

    @BeforeEach
    fun beforeEach(){
        usuario.userName = "name"
        usuario.password = "1234"
        usuario.email = "email"

        runTrx {
            userDAO.guardar(usuario)
        }
    }

    @Test
    fun guardarTest(){
        val newUsuario = Usuario()
        newUsuario.userName = "new"
        newUsuario.password = "1234"
        newUsuario.email = "email"

        val usuarioRecuperado = runTrx {
            userDAO.guardar(newUsuario)
            userDAO.recuperar(newUsuario.userName)
        }


        Assert.assertEquals(newUsuario.password, usuarioRecuperado.password)
        Assert.assertEquals(newUsuario.id, usuarioRecuperado.id)
        Assert.assertEquals(newUsuario.userName, usuarioRecuperado.userName)
        Assert.assertEquals(newUsuario.email, usuarioRecuperado.email)

    }

    @Test
    fun recuperarTest(){
        val usuarioRecuperado = runTrx {
            userDAO.recuperar(usuario.userName)
        }

        Assert.assertEquals(usuario.password, usuarioRecuperado.password)
        Assert.assertEquals(usuario.id, usuarioRecuperado.id)
        Assert.assertEquals(usuario.userName, usuarioRecuperado.userName)
        Assert.assertEquals(usuario.email, usuarioRecuperado.email)
    }

    @Test
    fun actualizarTest(){

        usuario.email = "cambiado"
        usuario.password = "5555"

        val usuarioRecuperado = runTrx{
            userDAO.actualizar(usuario)
            userDAO.recuperar(usuario.userName)
        }

        Assert.assertEquals(usuario.password, usuarioRecuperado.password)
        Assert.assertEquals(usuario.id, usuarioRecuperado.id)
        Assert.assertEquals(usuario.userName, usuarioRecuperado.userName)
        Assert.assertEquals(usuario.email, usuarioRecuperado.email)
    }

    @Test
    fun eliminarTest(){
        runTrx{
            userDAO.eliminar(usuario)
        }

        Assertions.assertThrows(NoResultException::class.java){
            runTrx {
                userDAO.getUserByName(usuario.userName)
            }
        }
    }

    @Test
    fun recuperarTodosTest(){

        val usuarios = runTrx{
            userDAO.recuperarATodos()
        }

        Assert.assertEquals(usuarios.size, 1)
        Assert.assertEquals(usuario.password, usuarios[0].password)
        Assert.assertEquals(usuario.userName, usuarios[0].userName)
        Assert.assertEquals(usuario.email, usuarios[0].email)
        Assert.assertEquals(usuario.id, usuarios[0].id)

    }

    @Test
    fun validarUsuarioTest(){
        Assert.assertNotNull(runTrx { userDAO.validateUser(usuario.userName,usuario.password) })

        val passwordMock = "mock"

        Assert.assertNull(runTrx {
            userDAO.validateUser(usuario.userName, passwordMock)
        })

        Assert.assertNull(runTrx {
            userDAO.validateUser(usuario.userName,"")
        })
    }

    @Test
    fun recuperarPorNombreTest(){

        val user = runTrx {
            userDAO.getUserByName(usuario.userName)
        }

        Assert.assertEquals(user.id, usuario.id)
        Assert.assertEquals(user.password, usuario.password)
        Assert.assertEquals(user.userName, usuario.userName)
        Assert.assertEquals(user.email, usuario.email)

        Assertions.assertThrows(NoResultException::class.java){
            runTrx {
                userDAO.getUserByName("-1")
            }
        }
    }

    @AfterEach
    fun after(){
        dataService.deleteAll()
    }
}