package api

import Elementos.Ing.AgileTasks.Controllers.Rest.NotaController
import Elementos.Ing.AgileTasks.excepciones.NotFoundException
import Elementos.Ing.AgileTasks.modelo.Nota
import Elementos.Ing.AgileTasks.modelo.Usuario
import org.junit.Assert
import org.junit.jupiter.api.*
import services.impl.UsuarioServiceImpl
import utils.DataServiceImpl

class NotaControllerTest {

    val notaController = NotaController()
    val nota : Nota = Nota()
    val usuario = Usuario()
    val userService = UsuarioServiceImpl()

    @BeforeEach
    fun before(){

        usuario.userName = "name"
        usuario.password = "1234"

        userService.nuevoUsuario(usuario)

        nota.titulo = "titulo"
        nota.setDescripcion("descripcion")
        nota.setUser(usuario)

        notaController.agregarNota(nota)

    }

    @Test
    fun agregarNotaTest(){
        val notaNueva = Nota()
        notaNueva.titulo = "titulo"
        notaNueva.setDescripcion("descripcion")
        notaNueva.setUser(usuario)

        notaController.agregarNota(notaNueva)

        val notaRecuperada = notaController.getNotaId(notaNueva.id)

        Assert.assertEquals(notaRecuperada.titulo, notaNueva.titulo)
        Assert.assertEquals(notaRecuperada.descrpicion, notaNueva.descrpicion)
        Assert.assertEquals(notaRecuperada.id, notaNueva.id)

    }

    @Test
    fun editarNotaTest(){
        val notaNueva = Nota()

        notaNueva.titulo = "otroTitulo"
        notaNueva.setDescripcion("otraDescripcion")

        notaController.editarNota(nota.id, notaNueva)

        val notaRecuperada = notaController.getNotaId(nota.id)

        Assert.assertEquals(notaRecuperada.titulo, notaNueva.titulo)
        Assert.assertEquals(notaRecuperada.descrpicion, notaNueva.descrpicion)

        Assertions.assertThrows(NotFoundException::class.java){
            notaController.editarNota(-1, notaNueva)
        }
    }

    @Test
    fun eliminarNotaTest(){
        notaController.borrarNota(nota.id)

        Assertions.assertThrows(NotFoundException::class.java){
            notaController.getNotaId(nota.id)
        }
    }

    @Test
    fun getNotaByUserId(){
        var notas = notaController.buscarPorUsuario(usuario.id.toInt())

        Assert.assertEquals(notas[0].titulo, nota.titulo)
        Assert.assertEquals(notas[0].descrpicion, nota.descrpicion)
        Assert.assertEquals(notas[0].id, nota.id)

        notas = notaController.buscarPorUsuario(-1)

        Assert.assertTrue(notas.isEmpty())
    }

    @Test
    fun getNotaById(){
        val notaRecuperada = notaController.getNotaId(nota.id)

        Assert.assertEquals(notaRecuperada.titulo, nota.titulo)
        Assert.assertEquals(notaRecuperada.descrpicion, nota.descrpicion)
        Assert.assertEquals(notaRecuperada.id, nota.id)

        Assertions.assertThrows(NotFoundException::class.java){
            notaController.getNotaId(-1)
        }
    }

    @Test
    fun getAllNotasTest(){
        val notas = notaController.getAllNotas()

        Assert.assertEquals(notas.size, 1)

        Assert.assertEquals(notas[0].titulo, nota.titulo)
        Assert.assertEquals(notas[0].descrpicion, nota.descrpicion)
        Assert.assertEquals(notas[0].id, nota.id)
    }

    @Test
    fun recuperarPorNombreTest(){
        val notas = notaController.buscarPorNombreUsuario(usuario.userName)
        Assert.assertEquals(notas.size, 1)
        Assert.assertEquals(notas[0].descrpicion, nota.descrpicion)
        Assert.assertEquals(notas[0].titulo, nota.titulo)
        Assert.assertEquals(notas[0].id, nota.id)
    }

    @AfterEach
    fun after(){
        DataServiceImpl().deleteAll()
    }
}