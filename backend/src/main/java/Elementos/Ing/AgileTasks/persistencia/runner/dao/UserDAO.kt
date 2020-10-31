package Elementos.Ing.AgileTasks.persistencia.runner.dao

import Elementos.Ing.AgileTasks.persistencia.runner.HibernateTransaction
import Elementos.Ing.AgileTasks.modelo.Usuario

class UserDAO: HibernateDAO<Usuario>(Usuario::class.java){
    fun recuperarPorUserName(userName: String): Usuario{
        val session = HibernateTransaction.currentSession
        val hql = "select u from Usuario u where u.userName = :pUserName"
        val query = session.createQuery(hql, Usuario::class.java)
        query.setParameter("pUserName", userName)
        return query.singleResult
    }
    fun validateUser(user: Usuario): Boolean{
        val userDB = this.recuperar(user.id.toInt())
        return (userDB.userName == user.userName && userDB.password == user.password)
    }
}