package Elementos.Ing.AgileTasks.persistencia.runner

interface Transaction {
    fun start()
    fun commit()
    fun rollback()
}