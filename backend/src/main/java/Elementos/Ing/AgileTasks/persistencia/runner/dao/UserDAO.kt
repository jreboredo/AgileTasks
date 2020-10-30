package Elementos.Ing.AgileTasks.persistencia.runner.dao

import Elementos.Ing.AgileTasks.persistencia.runner.HibernateTransaction
import Elementos.Ing.AgileTasks.modelo.Usuario

class UserDAO: HibernateDAO<Usuario>(Usuario::class.java){
    fun recuperarPorUserName(id: Int): Usuario{
        val session = HibernateTransaction.currentSession
        val hql = "select u from Usuario u where u.id = :pId"
        val query = session.createQuery(hql, Usuario::class.java)
        query.setParameter("pId", id)
        return query.singleResult
    }
    fun validateUser(user: Usuario): Boolean{
        val userId = this.recuperarPorUserName(user.id.toInt())
        val userDB = this.recuperar(userId.id.toInt())
        return (userDB.userName == user.userName && userDB.password == user.password)
    }
}