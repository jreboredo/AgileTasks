package services

import Controllers.runner.TransactionRunner.runTrx
import io.cucumber.java.Before
import org.junit.jupiter.api.Test
import utils.DataServiceImpl

class NotaDAOTest {
    private lateinit var dataServiceImpl: DataServiceImpl
    @Before
    fun prepare() {
        this.dataServiceImpl = DataServiceImpl()
    }

    @Test
    fun test1(){
        return runTrx{
        this.dataServiceImpl = DataServiceImpl()
        dataServiceImpl.crearNotasDummy()
        }
    }
}