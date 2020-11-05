package services.impl

import Elementos.Ing.AgileTasks.modelo.Tarea
import Elementos.Ing.AgileTasks.persistencia.runner.TransactionRunner.runTrx
import Elementos.Ing.AgileTasks.persistencia.runner.dao.TareaDAO
import services.TareaService

class TareaServiceImpl: TareaService {
    private val tareaDAO = TareaDAO()
    override fun agregarTarea(tarea: Tarea) {
        runTrx{
            tareaDAO.guardar(tarea)
        }
    }

    override fun recuperarTareaPorId(id: Int): Tarea {
        return runTrx {
            tareaDAO.recuperar(id)
        }
    }

    override fun recuperarPorUserId(id: Int): List<Tarea> {
        return runTrx {
            tareaDAO.recuperarPorUserId(id)
        }
    }

    override fun modificarTarea(tarea: Tarea) {
        runTrx {
            tareaDAO.actualizar(tarea)
        }
    }

    override fun recuperarTodas(): List<Tarea> {
        return runTrx {
            tareaDAO.recuperarATodos()
        }
    }

    override fun eliminar(tarea: Tarea) {
        runTrx {
            tareaDAO.eliminar(tarea)
        }
    }

    override fun recuperarPorUserName(userName: String): List<Tarea> {
        return runTrx {
            tareaDAO.recuperarPorUserName(userName)
        }
    }
}