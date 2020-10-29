package utils

import Elementos.Ing.AgileTasks.modelo.Nota
import Elementos.Ing.AgileTasks.modelo.Usuario
import Elementos.Ing.AgileTasks.persistencia.runner.HibernateTransaction
import Elementos.Ing.AgileTasks.persistencia.runner.TransactionRunner.runTrx
import Elementos.Ing.AgileTasks.persistencia.runner.dao.NotaDAO
import Elementos.Ing.AgileTasks.persistencia.runner.dao.UserDAO

class DataServiceImpl : DataService{
    var NotaDAO: NotaDAO = NotaDAO()
    var UserDAO: UserDAO = UserDAO()

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
            val session = HibernateTransaction.currentSession
            val nombreDeTablas = session.createNativeQuery("show tables").resultList
            session.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate()
            nombreDeTablas.forEach { result ->
                var tabla = ""
                when (result) {
                    is String -> tabla = result
                    is Array<*> -> tabla = result[0].toString()
                }
                session.createNativeQuery("truncate table $tabla").executeUpdate()
            }
            session.createNativeQuery("SET FOREIGN_KEY_CHECKS=1;").executeUpdate()
        }
    }
}