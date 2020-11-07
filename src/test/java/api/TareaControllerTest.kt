package api

import Elementos.Ing.AgileTasks.Controllers.Rest.TareaController
import Elementos.Ing.AgileTasks.excepciones.NotFoundException
import Elementos.Ing.AgileTasks.modelo.Tarea
import Elementos.Ing.AgileTasks.modelo.Usuario
import org.junit.Assert
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import services.impl.TareaServiceImpl
import services.impl.UsuarioServiceImpl
import utils.DataServiceImpl
import java.time.LocalDateTime

class TareaControllerTest {

    val tareaController = TareaController()
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

        tareaController.agregarTarea(tarea)
    }

    @Test
    fun getAllTareas(){
        val tareas = tareaController.getAllTareas()

        Assert.assertEquals(tareas.size, 1)

        Assert.assertEquals(tareas[0].descripcion, tarea.descripcion)
        Assert.assertEquals(tareas[0].titulo, tarea.titulo)
        Assert.assertEquals(tareas[0].id, tarea.id)
        Assert.assertEquals(tareas[0].comienzo, tarea.comienzo)
        Assert.assertEquals(tareas[0].prioridad, tarea.prioridad)
        Assert.assertEquals(tareas[0].fin, tarea.fin)

    }

    @Test
    fun modificarTareaTest(){

    }

    @Test
    fun agregarTareaTest(){
        val nuevaTarea = Tarea()
        nuevaTarea.descripcion = "descripcion"
        nuevaTarea.titulo = "titulo"
        nuevaTarea.user = usuario
        val localDate = LocalDateTime.now()
        nuevaTarea.comienzo = localDate
        nuevaTarea.fin = localDate
        nuevaTarea.prioridad = 1

        tareaController.agregarTarea(nuevaTarea)

        val tareaRecuperada = tareaController.getTareaId(nuevaTarea.id.toInt())

        Assert.assertEquals(tareaRecuperada.titulo, nuevaTarea.titulo)
        Assert.assertEquals(tareaRecuperada.descripcion, nuevaTarea.descripcion)
        Assert.assertEquals(tareaRecuperada.id, nuevaTarea.id)
        Assert.assertEquals(tareaRecuperada.comienzo, nuevaTarea.comienzo)
        Assert.assertEquals(tareaRecuperada.fin, nuevaTarea.fin)
        Assert.assertEquals(tareaRecuperada.prioridad, nuevaTarea.prioridad)

    }

    @Test

    fun eliminarTareaTest(){

        tareaController.borrarTarea(tarea.id.toInt())

        val tareas = tareaController.getAllTareas()

        Assert.assertEquals(tareas.size, 0)

        Assertions.assertThrows(NotFoundException::class.java){
            tareaController.getTareaId(tarea.id.toInt())
        }
    }

    @Test
    fun recuperarPorUserIdTest(){
        val tareas = tareaController.buscarPorUsuario(usuario.id.toInt())

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
        val tareas = tareaController.buscarPorNombreUsuario(usuario.userName)


        Assert.assertEquals(tareas.size, 1)
        Assert.assertEquals(tareas[0].descripcion, tarea.descripcion)
        Assert.assertEquals(tareas[0].titulo, tarea.titulo)
        Assert.assertEquals(tareas[0].id, tarea.id)
        Assert.assertEquals(tareas[0].comienzo, tarea.comienzo)
        Assert.assertEquals(tareas[0].prioridad, tarea.prioridad)
        Assert.assertEquals(tareas[0].fin, tarea.fin)
    }

    fun getTareaPorId(){
        val tareaRecuperada : Tarea = tareaController.getTareaId(tarea.id.toInt())

        Assert.assertEquals(tareaRecuperada.titulo, tarea.titulo)
        Assert.assertEquals(tareaRecuperada.descripcion, tarea.descripcion)
        Assert.assertEquals(tareaRecuperada.id, tarea.id)
        Assert.assertEquals(tareaRecuperada.comienzo, tarea.comienzo)
        Assert.assertEquals(tareaRecuperada.fin, tarea.fin)
        Assert.assertEquals(tareaRecuperada.prioridad, tarea.prioridad)

        Assertions.assertThrows(NotFoundException::class.java){
            tareaController.getTareaId(-1)
        }

    }

    @AfterEach
    fun after(){
        DataServiceImpl().deleteAll()
    }
}