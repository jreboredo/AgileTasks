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
class NotaController()  {
    private val  notaService: NotaServiceImpl = NotaServiceImpl()
    @RequestMapping(value=["/allnotes"], method = [(RequestMethod.GET)])
     fun getAllNotas(): List<Nota> = notaService.recuperarTodas()


    @GetMapping("/getId")
    fun getNotaId(@RequestParam id : Int): Nota {
        return notaService.recuperarPorId(id)
    }

    //Te permite agregar una nota nueva
    @PostMapping("/NuevaNota")
        fun agregarNota(@RequestBody nota : Nota) {
           // return notaServiceImpl.agregarNota(nota)
    }

    //Editar notas
    @PutMapping("/EditarNota")
     fun editarNota(@RequestBody nota : Nota) {
        //return notaServiceImpl.modificarNota(nota)
    }
}

