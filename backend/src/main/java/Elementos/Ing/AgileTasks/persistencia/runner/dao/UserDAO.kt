package Elementos.Ing.AgileTasks.persistencia.runner.dao

import Elementos.Ing.AgileTasks.modelo.Usuario
import Elementos.Ing.AgileTasks.persistencia.runner.HibernateTransaction

class UserDAO : HibernateDAO<Usuario>(Usuario::class.java) {
    fun recuperarPorId(id: Int): Usuario {
        val session = HibernateTransaction.currentSession
        val hql = "select u from Usuario u where u.id = :pId"
        val query = session.createQuery(hql, Usuario::class.java)
        query.setParameter("pId", id)
        return query.singleResult
    }

    fun validateUser(userName: String, password: String): Usuario? {
        val userDB: Usuario? = this.getUserByName(userName)
        return if (userDB != null && userDB.userName == userName && userDB.password == password) {
            userDB
        } else {
            null
        }
    }

    fun getUserByName(userName: String): Usuario {
        val session = HibernateTransaction.currentSession
        val hql = "select u from Usuario u where lower(u.userName) = :puserName"
        val query = session.createQuery(hql, Usuario::class.java)
        query.setParameter("puserName", userName.toLowerCase())
        return query.singleResult
    }

    fun getNextId(): Int {
        val session = HibernateTransaction.currentSession
        val hql = "select count(u) from Usuario u"
        val query = session.createQuery(hql, java.lang.Long::class.java)
        return query.singleResult.toInt() + 1
    }
}