package services.impl

import Controllers.runner.TransactionRunner.runTrx
import modelo.Nota
import services.NotaService
import Controllers.dao.NotaDAO

class NotaServiceImpl: NotaService {
    val notaDAO: NotaDAO = NotaDAO()
    override fun agregarNota(nota: Nota) {
        runTrx{
            notaDAO.guardar(nota)
        }
    }
    override fun modificarNota(nota: Nota) {
        runTrx {
            notaDAO.actualizar(nota)
        }
    }

    override fun asignarNotaAUser(nota: Nota) {
        TODO("Not yet implemented")
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
}