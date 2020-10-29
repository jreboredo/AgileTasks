package modelo

import Elementos.Ing.AgileTasks.modelo.Nota
import Elementos.Ing.AgileTasks.modelo.Usuario
import org.junit.Assert
import org.junit.jupiter.api.Test

class NotaTest {

    var nota : Nota = Nota()
    var usuario : Usuario = Usuario()

    @Test
    fun notaTest(){
        nota.setDescripcion("Descripcion")
        nota.titulo = "Titulo"
        nota.setUser(usuario)

        Assert.assertEquals(nota.descrpicion, "Descripcion")
        Assert.assertEquals(nota.titulo, "Titulo")
    }
}