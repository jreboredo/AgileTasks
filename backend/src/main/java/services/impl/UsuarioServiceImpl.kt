package services.impl

import Controllers.dao.UserDAO
import Controllers.runner.TransactionRunner.runTrx
import modelo.Usuario
import org.apache.catalina.User
import services.UsuarioService

class UsuarioServiceImpl: UsuarioService {
    private val UserDAO = UserDAO()
    override fun nuevoUsuario(user: Usuario) {
        runTrx{
            UserDAO.guardar(user)
        }
    }

    override fun modificarUsuario(user: Usuario) {
        runTrx {
            UserDAO.actualizar(user)
        }
    }

    override fun getId(userName: String): Long {
        return runTrx {
            UserDAO.recuperarPorUserName(userName)
        }
    }

    override fun validateUser(user: Usuario): Boolean {
        return runTrx {
            UserDAO.validateUser(user)
        }
    }
}