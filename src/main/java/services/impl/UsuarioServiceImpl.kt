package services.impl

import Elementos.Ing.AgileTasks.persistencia.runner.dao.UserDAO
import Elementos.Ing.AgileTasks.persistencia.runner.TransactionRunner.runTrx
import Elementos.Ing.AgileTasks.modelo.Usuario
import services.UsuarioService

class UsuarioServiceImpl: UsuarioService {
    private val UserDAO = UserDAO()
    override fun nuevoUsuario(user: Usuario) {
        runTrx{
            user.setId(UserDAO.getNextId())
            UserDAO.guardar(user)
        }
    }

    override fun modificarUsuario(user: Usuario) {
        runTrx {
            UserDAO.actualizar(user)
        }
    }

    override fun eliminarUsuario(user: Usuario) {
        runTrx {
            UserDAO.eliminar(user)
        }
    }


    override fun getId(id: Int): Usuario {
        return runTrx {
            UserDAO.recuperarPorId(id)
        }
    }

    override fun validateUser(userName: String, password : String): Usuario? {
        return runTrx {
            UserDAO.validateUser(userName, password)
        }
    }

    override fun getUserByName(userName: String): Usuario {
        return runTrx {
            UserDAO.getUserByName(userName)
        }
    }
}