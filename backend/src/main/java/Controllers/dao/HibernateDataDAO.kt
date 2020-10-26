package Controllers.dao

import Controllers.runner.HibernateTransaction

open class HibernateDataDAO : DataDAO {

    override fun clear() {
        val session = HibernateTransaction.currentSession
        val nombreDeTablas = session.createNativeQuery("show tables").resultList
        session.createNativeQuery("SET FOREIGN_KEY_CHECKS=0;").executeUpdate()
        nombreDeTablas.forEach { tabla -> session.createNativeQuery("truncate table $tabla").executeUpdate() }
        session.createNativeQuery("SET FOREIGN_KEY_CHECKS=1;").executeUpdate()
    }
}