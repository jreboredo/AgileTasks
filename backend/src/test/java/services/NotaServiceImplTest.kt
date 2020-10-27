package services

import Controllers.runner.TransactionRunner.runTrx
import modelo.Nota
import modelo.Usuario
import org.assertj.core.api.Assert
import org.junit.Before
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.*
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