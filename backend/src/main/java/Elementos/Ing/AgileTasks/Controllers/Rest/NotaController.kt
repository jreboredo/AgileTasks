package Elementos.Ing.AgileTasks.Controllers.Rest

import Elementos.Ing.AgileTasks.modelo.Nota
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import services.impl.NotaServiceImpl


@RestController
@RequestMapping(value = ["/notes"])
class NotaController() {
    private val notaService: NotaServiceImpl = NotaServiceImpl()

    @RequestMapping(value = ["/allnotes"], method = [(RequestMethod.GET)])
    fun getAllNotas(): List<Nota> = notaService.recuperarTodas()

    //Recupera una nota por Id de la nota
    @GetMapping("/get/{id}")
    fun getNotaId(@PathVariable id: Int): Nota {
        return notaService.recuperarPorId(id)
    }

    //Recupera todas las notas por Id del usuario
    @GetMapping("/getByUser/{id}")
    fun buscarPorUsuario(@PathVariable id: Int): List<Nota> {
        return notaService.recuperarPorUserId(id)
    }


    //Te permite agregar una nota nueva
    @PostMapping("/NuevaNota")
    fun agregarNota(@RequestBody nota: Nota) {
        notaService.agregarNota(nota)
    }

    //Te permite eliminar una nota
    @PostMapping("/DeleteNota/{id}")
    fun borrarNota(@PathVariable id: Int) {
        val nota: Nota = this.getNotaId(id)

        notaService.eliminar(nota)
    }

    //Buscar por userName
    @GetMapping("/getByUserName/{userName}")
    fun buscarPorNombreUsuario(@PathVariable userName : String) : List<Nota> {
        return notaService.recuperarPorUserName(userName)
    }

    //Editar notas
    @PutMapping("/EditarNota/{id}")
    fun editarNota(@PathVariable id: Int, @RequestBody nota: Nota) {
        var notaVieja: Nota = getNotaId(id)
        notaVieja.setTitulo(nota.titulo)
        notaVieja.setDescripcion(nota.descrpicion)
        notaService.modificarNota(notaVieja)
    }
}

