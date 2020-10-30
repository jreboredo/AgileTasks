package Elementos.Ing.AgileTasks.persistencia.runner.dao

import Elementos.Ing.AgileTasks.persistencia.runner.HibernateTransaction
import Elementos.Ing.AgileTasks.modelo.Usuario

class UserDAO: HibernateDAO<Usuario>(Usuario::class.java){
    fun recuperarPorUserName(userName: String): Long{
        val session = HibernateTransaction.currentSession
        val hql = "select u from Usuario u where lower(u.userName) like :pUserName"
        val query = session.createQuery(hql, Usuario::class.java)
        query.setParameter("pUserName", userName.toLowerCase())
        return query.singleResult.id
    }
    fun validateUser(user: Usuario): Boolean{
        val userId = this.recuperarPorUserName(user.userName)
        val userDB = this.recuperar(userId.toInt())
        return (userDB.userName == user.userName && userDB.password == user.password)
    }
}