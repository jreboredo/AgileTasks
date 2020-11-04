package Elementos.Ing.AgileTasks.persistencia.runner.dao


import Elementos.Ing.AgileTasks.modelo.Nota
import Elementos.Ing.AgileTasks.modelo.Tarea
import Elementos.Ing.AgileTasks.persistencia.runner.HibernateTransaction

class TareaDAO : HibernateDAO<Tarea>(Tarea::class.java) {
    fun recuperarPorUserId(userID: Int) : List<Tarea> {
        val session = HibernateTransaction.currentSession
        val hql = "select t from Tarea t where lower(t.user.id) = :pId"
        val query = session.createQuery(hql, Tarea::class.java)
        query.setParameter("pId", userID)
        return query.list()
    }

    fun recuperarPorUserName(userName: String) : List<Tarea> {
        val session = HibernateTransaction.currentSession
        val hql = "select u from Tarea u where lower(u.user.userName) = :puserName"
        val query = session.createQuery(hql, Tarea::class.java)
        query.setParameter("puserName", userName.toLowerCase())
        return query.list()
    }
}