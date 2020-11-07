package dao

import Elementos.Ing.AgileTasks.excepciones.NotFoundException
import Elementos.Ing.AgileTasks.modelo.Tarea
import Elementos.Ing.AgileTasks.modelo.Usuario
import Elementos.Ing.AgileTasks.persistencia.runner.TransactionRunner.runTrx
import Elementos.Ing.AgileTasks.persistencia.runner.dao.TareaDAO
import Elementos.Ing.AgileTasks.persistencia.runner.dao.UserDAO
import org.junit.Assert
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import services.impl.UsuarioServiceImpl
import utils.DataServiceImpl
import java.time.LocalDateTime

class TareaDAOTest {
    val tareaDAO = TareaDAO()
    val userService = UsuarioServiceImpl()
    val tarea : Tarea = Tarea()
    val usuario = Usuario()

    @BeforeEach
    fun before(){
        usuario.userName = "admin"
        usuario.email = "email"
        usuario.password = "1234"
        userService.nuevoUsuario(usuario)

        val localDate = LocalDateTime.now()
        tarea.comienzo = localDate
        tarea.fin = localDate
        tarea.prioridad = 1
        tarea.descripcion = "descripcion"
        tarea.titulo = "titulo"
        tarea.user = usuario

        runTrx{
            tareaDAO.guardar(tarea)
        }
    }
    @Test
    fun guardarTest(){

        val nuevaTarea = Tarea()

        nuevaTarea.titulo = "titulo"
        nuevaTarea.descripcion ="descripcion"
        nuevaTarea.user = usuario
        val localDate = LocalDateTime.now()
        nuevaTarea.comienzo = localDate
        nuevaTarea.fin = localDate
        nuevaTarea.prioridad = 1

        val tareaRecuperada = runTrx{
            tareaDAO.guardar(nuevaTarea)
            tareaDAO.recuperar(nuevaTarea.id.toInt())
        }

        Assert.assertEquals(tareaRecuperada.descripcion, nuevaTarea.descripcion)
        Assert.assertEquals(tareaRecuperada.titulo, nuevaTarea.titulo)
        Assert.assertEquals(tareaRecuperada.id, nuevaTarea.id)
        Assert.assertEquals(tareaRecuperada.comienzo, nuevaTarea.comienzo)
        Assert.assertEquals(tareaRecuperada.fin, nuevaTarea.fin)
        Assert.assertEquals(tareaRecuperada.prioridad, nuevaTarea.prioridad)
    }

    @Test
    fun recuperarTest(){
        val tareaRecuperada = runTrx{
            tareaDAO.recuperar(tarea.id.toInt())
        }

        Assert.assertEquals(tareaRecuperada.descripcion, tarea.descripcion)
        Assert.assertEquals(tareaRecuperada.titulo, tarea.titulo)
        Assert.assertEquals(tareaRecuperada.id, tarea.id)
        Assert.assertEquals(tareaRecuperada.comienzo, tarea.comienzo)
        Assert.assertEquals(tareaRecuperada.fin, tarea.fin)
        Assert.assertEquals(tareaRecuperada.prioridad, tarea.prioridad)
    }

    @Test
    fun actualizarTest(){
        tarea.descripcion = "nuevaDescripcion"
        tarea.titulo = "nuevoTitulo"

        val tareaRecuperada = runTrx{
            tareaDAO.actualizar(tarea)
            tareaDAO.recuperar(tarea.id.toInt())
        }

        Assert.assertEquals(tareaRecuperada.descripcion, tarea.descripcion)
        Assert.assertEquals(tareaRecuperada.titulo, tarea.titulo)
        Assert.assertEquals(tareaRecuperada.id, tarea.id)
        Assert.assertEquals(tareaRecuperada.comienzo, tarea.comienzo)
        Assert.assertEquals(tareaRecuperada.fin, tarea.fin)
        Assert.assertEquals(tareaRecuperada.prioridad, tarea.prioridad)
    }

    @Test
    fun eliminarTest(){
        runTrx {
            tareaDAO.eliminar(tarea)
        }

        Assertions.assertThrows(NotFoundException::class.java){
            runTrx {
                tareaDAO.recuperar(tarea.id.toInt())
            }
        }
    }

    @Test
    fun recuperarTodoTest(){
        val tareas = runTrx{
            tareaDAO.recuperarATodos()
        }

        Assert.assertEquals(tareas.size, 1)

        Assert.assertEquals(tareas[0].descripcion, tarea.descripcion)
        Assert.assertEquals(tareas[0].titulo, tarea.titulo)
        Assert.assertEquals(tareas[0].id, tarea.id)
        Assert.assertEquals(tareas[0].comienzo, tarea.comienzo)
        Assert.assertEquals(tareas[0].prioridad, tarea.prioridad)
        Assert.assertEquals(tareas[0].fin, tarea.fin)
    }

    @Test
    fun recuperarPorUserIdTest(){
        val tareas = runTrx {
            tareaDAO.recuperarPorUserId(usuario.id.toInt())
        }

        Assert.assertEquals(tareas.size, 1)
        Assert.assertEquals(tareas[0].descripcion, tarea.descripcion)
        Assert.assertEquals(tareas[0].titulo, tarea.titulo)
        Assert.assertEquals(tareas[0].id, tarea.id)
        Assert.assertEquals(tareas[0].comienzo, tarea.comienzo)
        Assert.assertEquals(tareas[0].prioridad, tarea.prioridad)
        Assert.assertEquals(tareas[0].fin, tarea.fin)
    }

    @Test
    fun recuperarPorUserName(){
        val tareas = runTrx {
            tareaDAO.recuperarPorUserName(usuario.userName)
        }

        Assert.assertEquals(tareas.size, 1)
        Assert.assertEquals(tareas[0].descripcion, tarea.descripcion)
        Assert.assertEquals(tareas[0].titulo, tarea.titulo)
        Assert.assertEquals(tareas[0].id, tarea.id)
        Assert.assertEquals(tareas[0].comienzo, tarea.comienzo)
        Assert.assertEquals(tareas[0].prioridad, tarea.prioridad)
        Assert.assertEquals(tareas[0].fin, tarea.fin)
    }

    @AfterEach
    fun after(){
        DataServiceImpl().deleteAll()
    }
}