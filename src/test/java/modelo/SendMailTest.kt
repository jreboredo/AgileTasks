package modelo

import Elementos.Ing.AgileTasks.modelo.SendMail
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import javax.mail.Session

class SendMailTest {

    val sendMail = SendMail()

    @Test
    fun sendMailTest(){
        assertEquals(sendMail.host, "smtp.gmail.com")
        assertEquals(sendMail.from, "InfoAgileTasks@gmail.com")
    }

    @Test
    fun settingMailTest(){
        val newSession = sendMail.settingMail("ignacio.mendez0000@gmail.com")

        assertEquals(newSession.properties.getProperty("mail.smtp.host"), "smtp.gmail.com")
        assertEquals(newSession.properties.getProperty("mail.smtp.port"), "465")
        assertEquals(newSession.properties.getProperty("mail.smtp.ssl.enable"), "true")
        assertEquals(newSession.properties.getProperty("mail.smtp.auth"), "true")
        assertEquals(sendMail.SendTo, "ignacio.mendez0000@gmail.com")
    }

    @Test
    fun sendingMailTest(){
        /*
        val result = sendMail.sendingEmail("ignacio.mendez0000@gmail.com", "Email de prueba", "a ver si anda esto")
        assertEquals(result, "Sent message Successfully...")

         */
    }

}