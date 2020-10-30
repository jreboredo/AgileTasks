package dao

import Elementos.Ing.AgileTasks.excepciones.NotFoundException
import Elementos.Ing.AgileTasks.modelo.Nota
import Elementos.Ing.AgileTasks.modelo.Usuario
import Elementos.Ing.AgileTasks.persistencia.runner.TransactionRunner.runTrx
import Elementos.Ing.AgileTasks.persistencia.runner.dao.NotaDAO
import Elementos.Ing.AgileTasks.persistencia.runner.dao.UserDAO
import org.junit.Assert
import org.junit.jupiter.api.*
import utils.DataServiceImpl

class NotaDAOTest {

    val notaDAO = NotaDAO()
    val userDAO = UserDAO()
    val nota = Nota()
    val usuario = Usuario()
    val dataService : DataServiceImpl = DataServiceImpl()

    @BeforeEach
    fun before(){

        usuario.password = "1234"
        usuario.userName = "name"

        nota.titulo = "titulo"
        nota.setDescripcion("descripcion")
        nota.setUser(usuario)

        runTrx {
            userDAO.guardar(usuario)
            notaDAO.guardar(nota)
        }
    }

    @Test
    fun guardarTest(){

        val nuevaNota = Nota()

        nuevaNota.titulo = "titulo"
        nuevaNota.setDescripcion("descripcion")
        nuevaNota.setUser(usuario)

        val notaRecuperada = runTrx{
            notaDAO.guardar(nuevaNota)
            notaDAO.recuperar(nuevaNota.id)
        }

        Assert.assertEquals(notaRecuperada.descrpicion, nuevaNota.descrpicion)
        Assert.assertEquals(notaRecuperada.titulo, nuevaNota.titulo)
        Assert.assertEquals(notaRecuperada.id, nuevaNota.id)
    }

    @Test
    fun recuperarTest(){
        val notaRecuperada = runTrx{
            notaDAO.recuperar(nota.id)
        }

        Assert.assertEquals(notaRecuperada.descrpicion, nota.descrpicion)
        Assert.assertEquals(notaRecuperada.titulo, nota.titulo)
        Assert.assertEquals(notaRecuperada.id, nota.id)
    }

    @Test
    fun actualizarTest(){
        nota.setDescripcion("nuevaDescripcion")
        nota.titulo = "nuevoTitulo"

        val notaRecuperada = runTrx{
            notaDAO.actualizar(nota)
            notaDAO.recuperar(nota.id)
        }

        Assert.assertEquals(notaRecuperada.descrpicion, nota.descrpicion)
        Assert.assertEquals(notaRecuperada.titulo, nota.titulo)
        Assert.assertEquals(notaRecuperada.id, nota.id)
    }

    @Test
    fun eliminarTest(){
        runTrx {
            notaDAO.eliminar(nota)
        }

        Assertions.assertThrows(NotFoundException::class.java){
            runTrx {
                notaDAO.recuperar(nota.id)
            }
        }
    }

    @Test
    fun recuperarTodoTest(){
        val notas = runTrx{
            notaDAO.recuperarATodos()
        }

        Assert.assertEquals(notas.size, 1)

        Assert.assertEquals(notas[0].descrpicion, nota.descrpicion)
        Assert.assertEquals(notas[0].titulo, nota.titulo)
        Assert.assertEquals(notas[0].id, nota.id)
    }

    @AfterEach
    fun after(){
        dataService.deleteAll()
    }
}