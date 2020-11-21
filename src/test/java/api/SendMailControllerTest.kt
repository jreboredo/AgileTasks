package api

import Elementos.Ing.AgileTasks.Controllers.Rest.SendMailController
import Elementos.Ing.AgileTasks.DataClasses.DataMail
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SendMailControllerTest {
    val sendMailController = SendMailController()

    val dataMail = DataMail()

    @Test
    fun sendMailControllerTest(){
        dataMail.stringTo = "ignacio.mendez0000@gmail.com"
        dataMail.stringSubject = "Email de prueba"
        dataMail.stringMessage = "a ver si anda esto"
        /*
        val result = sendMailController.sendMail(dataMail)
        Assertions.assertEquals(result, "Sent message Successfully...")
         */
    }
}