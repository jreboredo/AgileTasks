package services.Rest

import modelo.Nota
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import services.impl.NotaServiceImpl


@ServiceRest
@CrossOrigin
class NotaController(@Autowired private val notaServiceImpl: NotaServiceImpl)  {


    //Trae todas las notas
    @GetMapping("/getAll")
     fun getAllNotas(): List<Nota> {
            return notaServiceImpl.recuperarTodas()
    }

    @GetMapping("/getId")
    fun getNotaId(@RequestParam id : Int): String {
        notaServiceImpl.recuperarPorId(id.toLong())
        return "sos un cornudo"
    }

    //Te permite agregar una nota nueva
    @PostMapping("/NuevaNota")
        fun agregarNota(@RequestBody nota : Nota) {
            return notaServiceImpl.agregarNota(nota)
    }

    //Editar notas
    @PutMapping("/EditarNota")
     fun editarNota(@RequestBody nota : Nota) {
        return notaServiceImpl.modificarNota(nota)
    }
}

