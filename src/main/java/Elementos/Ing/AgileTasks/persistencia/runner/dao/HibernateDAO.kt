package Elementos.Ing.AgileTasks.persistencia.runner.dao

import Elementos.Ing.AgileTasks.excepciones.NotFoundException
import Elementos.Ing.AgileTasks.persistencia.runner.HibernateTransaction
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery



open class HibernateDAO<T>(private val entityType: Class<T>) {

    fun guardar(item: T): T {
        val session = HibernateTransaction.currentSession
        session.save(item)
        return item
    }

    fun recuperar(id: String): T {
        val session = HibernateTransaction.currentSession
        return session.get(entityType, id) ?: throw NotFoundException("Id no encontrado para Clase: " + this.entityType)
    }
    fun recuperar(id: Int): T {
        val session = HibernateTransaction.currentSession
        return session.get(entityType, id) ?: throw NotFoundException("Id no encontrado para Clase: " + this.entityType)
    }

    fun actualizar(item: T) {
        val session = HibernateTransaction.currentSession
        session.update(item)
    }

    fun recuperarATodos(): List<T> {
        val session = HibernateTransaction.currentSession
        val builder: CriteriaBuilder = session.criteriaBuilder
        val criteria: CriteriaQuery<T> = builder.createQuery(entityType)
        criteria.from(entityType)
        return session.createQuery(criteria).resultList
    }

    fun eliminar(item: T){
        val session = HibernateTransaction.currentSession
        session.delete(item)
    }


}