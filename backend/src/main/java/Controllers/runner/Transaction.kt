package Controllers.runner

interface Transaction {
    fun start()
    fun commit()
    fun rollback()
}