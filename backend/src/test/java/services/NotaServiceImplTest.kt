package services

import Elementos.Ing.AgileTasks.excepciones.Controllers.runner.TransactionRunner.runTrx
import org.junit.jupiter.api.Test
import services.impl.NotaServiceImpl

import utils.DataServiceImpl

class NotaServiceImplTest{
    private var dataServiceImpl: DataServiceImpl = DataServiceImpl()
    private var NotaService: NotaService = NotaServiceImpl()
    @Test
    fun DatosDummy() {
        runTrx{
            dataServiceImpl.crearDatosDummy()
        }
    }

    @Test
    fun getAllNotas(){
        //TODO("NOT IMPLEMENTED YET")

    }
}