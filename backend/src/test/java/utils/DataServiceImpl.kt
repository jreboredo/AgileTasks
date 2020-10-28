package utils

import agiletasks.dao.NotaDAO
import agiletasks.dao.UserDAO
import agiletasks.dao.modelo.Nota
import agiletasks.dao.modelo.Usuario


class DataServiceImpl {
    var NotaDAO: NotaDAO = NotaDAO()
    var UserDAO: UserDAO = UserDAO()
    fun crearDatosDummy(){
        this.crearUserAdmin()
        var nota1 = Nota()
        nota1.setUser(UserDAO.recuperar(1))
        nota1.setTitulo("Dummy1")
        nota1.setDescripcion("Test de Nota")
        NotaDAO.guardar(nota1)
    }
    fun crearUserAdmin(){
        var user: Usuario = Usuario()
        user.password = "quick"
        user.userName = "admin"
        UserDAO.guardar(user)
    }
}