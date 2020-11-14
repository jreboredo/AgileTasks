package Elementos.Ing.AgileTasks.Controllers.Rest

import Elementos.Ing.AgileTasks.modelo.Tarea
import org.springframework.web.bind.annotation.*
import services.impl.TareaServiceImpl

@RestController
@CrossOrigin(origins= ["http://localhost:3000"])
@RequestMapping(value = ["/tasks"])
class TareaController {
    private val tareaService: TareaServiceImpl = TareaServiceImpl()

    @RequestMapping(value = ["/alltasks"], method = [(RequestMethod.GET)])
    fun getAllTareas(): List<Tarea> = tareaService.recuperarTodas()

    //Recupera todas las tareas por Id del usuario
    @GetMapping("/getByUser/{id}")
    fun buscarPorUsuario(@PathVariable id: Int): List<Tarea> {
        return tareaService.recuperarPorUserId(id)
    }


    @PutMapping("/editById/{id}")
    fun editarTarea(@PathVariable id: Int, @RequestBody tareaNueva: Tarea) {
        val tarea = getTareaId(id)
        tarea.titulo = tareaNueva.titulo
        tarea.comienzo = tareaNueva.comienzo
        tarea.descripcion = tareaNueva.descripcion
        tarea.fin = tareaNueva.fin
        tarea.prioridad = tareaNueva.prioridad
        tarea.fin = tareaNueva.fin
        tarea.isCompletada = tareaNueva.isCompletada

        tareaService.modificarTarea(tarea)
    }

    //Recupera una tarea por Id de la tarea
    @GetMapping("/get/{id}")
    fun getTareaId(@PathVariable id: Int): Tarea {
        return tareaService.recuperarTareaPorId(id)
    }


    //Te permite agregar una tarea nueva
    @PostMapping("/NuevaTarea")
    fun agregarTarea(@RequestBody tarea: Tarea) {
        tareaService.agregarTarea(tarea)
    }

    //Te permite eliminar una tarea
    @DeleteMapping("/DeleteTarea/{id}")
    fun borrarTarea(@PathVariable id: Int) {
        val tarea: Tarea = this.getTareaId(id)
        tareaService.eliminar(tarea)
    }

    //Buscar por userName
    @GetMapping("/getByUserName/{userName}")
    fun buscarPorNombreUsuario(@PathVariable userName : String) : List<Tarea> {
        return tareaService.recuperarPorUserName(userName)
    }
}