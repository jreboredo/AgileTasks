package Elementos.Ing.AgileTasks.persistencia.runner.dao

import Elementos.Ing.AgileTasks.modelo.Nota
import Elementos.Ing.AgileTasks.persistencia.runner.HibernateTransaction

 class NotaDAO  : HibernateDAO<Nota>(Nota::class.java) {
    fun recuperarPorUserId(id: Int) : List<Nota> {
            val session = HibernateTransaction.currentSession
            val hql = "select u from Nota u where u.user.id = :pId"
            val query = session.createQuery(hql, Nota::class.java)
            query.setParameter("pId", id)
            return query.list()
     }
    }