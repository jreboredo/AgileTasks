package modelo

import Elementos.Ing.AgileTasks.DataClasses.DataMail
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DataMailTest {

    val dataMail : DataMail = DataMail()

    @Test
    fun dataMailTest(){
        dataMail.stringMessage = "Message"
        dataMail.stringSubject = "Subject"
        dataMail.stringTo = "To"

        assertEquals(dataMail.stringMessage, "Message")
        assertEquals(dataMail.stringSubject, "Subject")
        assertEquals(dataMail.stringTo, "To")
    }
}