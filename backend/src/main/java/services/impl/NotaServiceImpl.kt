package services.impl

import Elementos.Ing.AgileTasks.persistencia.runner.TransactionRunner.runTrx
import Elementos.Ing.AgileTasks.modelo.Nota
import services.NotaService
import Elementos.Ing.AgileTasks.persistencia.runner.dao.NotaDAO

class NotaServiceImpl: NotaService {
    val notaDAO: NotaDAO = NotaDAO()
    override fun agregarNota(nota: Nota) {
        runTrx{
            notaDAO.guardar(nota)
        }
    }

    override fun recuperarPorId(id: Int): Nota {
       return runTrx {
            notaDAO.recuperar(id)
        }
    }

    override fun recuperarPorUserId(id: Int): List<Nota> {
        return runTrx {
            notaDAO.recuperarPorUserId(id)
        }
    }

    override fun modificarNota(nota: Nota) {
        runTrx {
            notaDAO.actualizar(nota)
        }
    }
    override fun asignarNotaATarea(nota: Nota) {
        TODO("Not yet implemented")
    }

    override fun recuperarTodas(): List<Nota> {
        var ret: List<Nota> = emptyList()
        runTrx {
            ret = notaDAO.recuperarATodos()
        }
        return ret
    }

    override fun eliminar(nota : Nota) {
       runTrx {
           notaDAO.eliminar(nota)
       }
    }

}