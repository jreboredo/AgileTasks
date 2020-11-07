package services

import Elementos.Ing.AgileTasks.excepciones.NotFoundException
import Elementos.Ing.AgileTasks.modelo.Tarea
import Elementos.Ing.AgileTasks.modelo.Usuario
import org.junit.Assert
import org.junit.jupiter.api.*
import services.impl.TareaServiceImpl
import services.impl.UsuarioServiceImpl
import utils.DataServiceImpl
import java.time.LocalDateTime

class TareaServiceImplTest {

    val tareaService = TareaServiceImpl()
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

        tareaService.agregarTarea(tarea)
    }

    @Test
    fun getAllTareas(){
        val tareas = tareaService.recuperarTodas()

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
        tarea.titulo = "TituloModificado"
        tarea.descripcion = "DescripcionModificada"

        tareaService.modificarTarea(tarea)

        val tareaRecuperada : Tarea = tareaService.recuperarTareaPorId(tarea.id.toInt())

        Assert.assertEquals(tareaRecuperada.titulo, tarea.titulo)
        Assert.assertEquals(tareaRecuperada.descripcion, tarea.descripcion)
        Assert.assertEquals(tareaRecuperada.id, tarea.id)
        Assert.assertEquals(tareaRecuperada.comienzo, tarea.comienzo)
        Assert.assertEquals(tareaRecuperada.fin, tarea.fin)
        Assert.assertEquals(tareaRecuperada.prioridad, tarea.prioridad)

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

        tareaService.agregarTarea(nuevaTarea)

        val tareaRecuperada = tareaService.recuperarTareaPorId(nuevaTarea.id.toInt())

        Assert.assertEquals(tareaRecuperada.titulo, nuevaTarea.titulo)
        Assert.assertEquals(tareaRecuperada.descripcion, nuevaTarea.descripcion)
        Assert.assertEquals(tareaRecuperada.id, nuevaTarea.id)
        Assert.assertEquals(tareaRecuperada.comienzo, nuevaTarea.comienzo)
        Assert.assertEquals(tareaRecuperada.fin, nuevaTarea.fin)
        Assert.assertEquals(tareaRecuperada.prioridad, nuevaTarea.prioridad)

    }

    @Test

    fun eliminarTareaTest(){

        tareaService.eliminar(tarea)

        val tareas = tareaService.recuperarTodas()

        Assert.assertEquals(tareas.size, 0)

        Assertions.assertThrows(NotFoundException::class.java){
            tareaService.recuperarTareaPorId(tarea.id.toInt())
        }
    }

    @Test
    fun recuperarPorUserIdTest(){
        val tareas = tareaService.recuperarPorUserId(usuario.id.toInt())

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
        val tareas = tareaService.recuperarPorUserName(usuario.userName)


        Assert.assertEquals(tareas.size, 1)
        Assert.assertEquals(tareas[0].descripcion, tarea.descripcion)
        Assert.assertEquals(tareas[0].titulo, tarea.titulo)
        Assert.assertEquals(tareas[0].id, tarea.id)
        Assert.assertEquals(tareas[0].comienzo, tarea.comienzo)
        Assert.assertEquals(tareas[0].prioridad, tarea.prioridad)
        Assert.assertEquals(tareas[0].fin, tarea.fin)
    }

    fun getTareaPorId(){
        val tareaRecuperada : Tarea = tareaService.recuperarTareaPorId(tarea.id.toInt())

        Assert.assertEquals(tareaRecuperada.titulo, tarea.titulo)
        Assert.assertEquals(tareaRecuperada.descripcion, tarea.descripcion)
        Assert.assertEquals(tareaRecuperada.id, tarea.id)
        Assert.assertEquals(tareaRecuperada.comienzo, tarea.comienzo)
        Assert.assertEquals(tareaRecuperada.fin, tarea.fin)
        Assert.assertEquals(tareaRecuperada.prioridad, tarea.prioridad)

        Assertions.assertThrows(NotFoundException::class.java){
            tareaService.recuperarTareaPorId(-1)
        }

    }

    @AfterEach
    fun after(){
        DataServiceImpl().deleteAll()
    }
}