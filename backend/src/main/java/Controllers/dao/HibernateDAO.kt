package Controllers.dao

import Controllers.excepciones.DuplicatedTypeException
import Controllers.excepciones.NotFoundException
import Controllers.runner.HibernateTransaction
import java.sql.SQLIntegrityConstraintViolationException
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery



open class HibernateDAO<T>(private val entityType: Class<T>) {

    fun guardar(item: T) {
        val session = HibernateTransaction.currentSession
        try {
            session.save(item)
        }catch (e: Exception){
            var cause = e.cause
            when(cause){
                is SQLIntegrityConstraintViolationException ->
                    throw DuplicatedTypeException("Tipo duplicado")
                else ->
                    throw e
            }
        }
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


}