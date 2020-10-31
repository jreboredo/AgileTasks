package services

import Elementos.Ing.AgileTasks.excepciones.NotFoundException
import Elementos.Ing.AgileTasks.modelo.Nota
import Elementos.Ing.AgileTasks.modelo.Usuario
import Elementos.Ing.AgileTasks.persistencia.runner.TransactionRunner.runTrx
import org.junit.Assert
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import services.impl.UsuarioServiceImpl

import org.junit.jupiter.api.Test
import services.impl.NotaServiceImpl

import utils.DataServiceImpl

class NotaServiceImplTest{
    private var dataServiceImpl: DataServiceImpl = DataServiceImpl()
    private var notaService: NotaService = NotaServiceImpl()
    private var userService : UsuarioService = UsuarioServiceImpl()

    private var nota1 = Nota()
    private var nota2 = Nota()
    private var nota3 = Nota()

    private var user : Usuario = Usuario()

    @BeforeEach
    fun beforeEach(){

        nota1.titulo = "Titulo1"
        nota2.titulo = "Titulo2"
        nota3.titulo = "Titulo3"

        nota1.setDescripcion("Descripcion1")
        nota2.setDescripcion("Descripcion2")
        nota3.setDescripcion("Descripcion3")

        userService.nuevoUsuario(user)

        nota1.setUser(user)
        nota2.setUser(user)
        nota3.setUser(user)

        notaService.agregarNota(nota1)
        notaService.agregarNota(nota2)
        notaService.agregarNota(nota3)
    }

    @Test
    fun DatosDummy() {
        runTrx{
            dataServiceImpl.crearDatosDummy()
        }
    }

    @Test
    fun getAllNotas(){
        val notas = notaService.recuperarTodas()

        Assert.assertEquals(notas.size, 3)

        Assert.assertEquals(notas[0].titulo, nota1.titulo)
        Assert.assertEquals(notas[0].descrpicion, nota1.descrpicion)

        Assert.assertEquals(notas[1].titulo, nota2.titulo)
        Assert.assertEquals(notas[1].descrpicion, nota2.descrpicion)

        Assert.assertEquals(notas[2].titulo, nota3.titulo)
        Assert.assertEquals(notas[2].descrpicion, nota3.descrpicion)
    }

    @Test
    fun getNotaPorId(){
        val notaRecuperada : Nota = notaService.recuperarPorId(nota1.id)

        Assert.assertEquals(notaRecuperada.titulo, nota1.titulo)
        Assert.assertEquals(notaRecuperada.id, nota1.id)
        Assert.assertEquals(notaRecuperada.descrpicion, nota1.descrpicion)

        Assertions.assertThrows(NotFoundException::class.java){
            notaService.recuperarPorId(-1)
        }

    }

    @Test
    fun modificarNotaTest(){
        nota1.titulo = "TituloModificado"
        nota1.setDescripcion("DescripcionModificada")

        notaService.modificarNota(nota1)

        val notaRecuperada : Nota = notaService.recuperarPorId(nota1.id)

        Assert.assertEquals(notaRecuperada.titulo, nota1.titulo)
        Assert.assertEquals(notaRecuperada.descrpicion, nota1.descrpicion)

    }

    @Test
    fun agregarNotaTest(){
        val nuevaNota = Nota()
        nuevaNota.setDescripcion( "descripcion")
        nuevaNota.titulo = "titulo"
        nuevaNota.setUser(user)

        notaService.agregarNota(nuevaNota)

        val notaRecuperada = notaService.recuperarPorId(nuevaNota.id)

        Assert.assertEquals(notaRecuperada.titulo, nuevaNota.titulo)
        Assert.assertEquals(notaRecuperada.descrpicion, nuevaNota.descrpicion)
        Assert.assertEquals(notaRecuperada.id, nuevaNota.id)
    }

    @Test

    fun eliminarNotaTest(){

        notaService.eliminar(nota1)
        notaService.eliminar(nota2)

        val notas = notaService.recuperarTodas()

        Assert.assertEquals(notas.size, 1)

        Assertions.assertThrows(NotFoundException::class.java){
            notaService.recuperarPorId(nota1.id)
        }
    }

    @Test
    fun recuperarPorUserIdTest(){
        val notas = notaService.recuperarPorUserId(user.id.toInt())

        Assert.assertEquals(notas.size, 3)
        Assert.assertEquals(notas[0].descrpicion, nota1.descrpicion)
        Assert.assertEquals(notas[0].titulo, nota1.titulo)
        Assert.assertEquals(notas[0].id, nota1.id)
    }

    @AfterEach
    fun afterEach(){
        dataServiceImpl.deleteAll()
    }
}