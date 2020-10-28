package Elementos.Ing.AgileTasks.persistencia.runner.dao

import Elementos.Ing.AgileTasks.persistencia.runner.HibernateTransaction

open class HibernateDataDAO : DataDAO<Any?> {

    override fun clear() {
        val session = HibernateTransaction.currentSession
        val nombreDeTablas = session.createNativeQuery("show tables").resultList
        session.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate()
        nombreDeTablas.forEach { tabla -> session.createNativeQuery("truncate table $tabla").executeUpdate() }
        session.createNativeQuery("SET FOREIGN_KEY_CHECKS=1;").executeUpdate()
    }
}