package utils

import Elementos.Ing.AgileTasks.modelo.Nota
import Elementos.Ing.AgileTasks.modelo.Usuario
import Elementos.Ing.AgileTasks.persistencia.runner.HibernateTransaction
import Elementos.Ing.AgileTasks.persistencia.runner.TransactionRunner.runTrx
import Elementos.Ing.AgileTasks.persistencia.runner.dao.DataDAO
import Elementos.Ing.AgileTasks.persistencia.runner.dao.HibernateDataDAO
import Elementos.Ing.AgileTasks.persistencia.runner.dao.NotaDAO
import Elementos.Ing.AgileTasks.persistencia.runner.dao.UserDAO

class DataServiceImpl : DataService{
    var NotaDAO: NotaDAO = NotaDAO()
    var UserDAO: UserDAO = UserDAO()
    val dataDAO = HibernateDataDAO()

    override fun crearDatosDummy(){
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

    override fun deleteAll() {
        runTrx {
            dataDAO.clear()
        }
    }
}