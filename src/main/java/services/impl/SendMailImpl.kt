package services.impl

import Elementos.Ing.AgileTasks.modelo.SendMail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import services.SendMailService
import javax.mail.internet.MimeMessage
@Component
class SendMailImpl : SendMailService {
    @Autowired
    private val sendMail : SendMail = SendMail()
    override fun sendMail(stringTo : String, stringMessage : String, stringSubject : String) : String{
        return sendMail.sendingEmail(stringTo, stringMessage, stringSubject)
    }
}