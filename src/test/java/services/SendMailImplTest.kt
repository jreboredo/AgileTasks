package services

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import services.impl.SendMailImpl

class SendMailImplTest {

    val sendMailImpl = SendMailImpl()

    @Test
    fun sendMailImplTest(){
        val result = sendMailImpl.sendMail("ignacio.mendez0000@gmail.com", "Email de prueba", "a ver si anda esto")
        assertEquals(result, "Sent message Successfully...")
    }
}