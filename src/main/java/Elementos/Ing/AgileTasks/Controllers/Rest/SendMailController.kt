package Elementos.Ing.AgileTasks.Controllers.Rest

import Elementos.Ing.AgileTasks.DataClasses.DataMail
import Elementos.Ing.AgileTasks.modelo.SendMail
import org.springframework.web.bind.annotation.*
import services.impl.SendMailImpl
import javax.mail.internet.MimeMessage

@RestController
@CrossOrigin(origins= ["http://localhost:3000"])
@RequestMapping(value = ["/mail"])
class SendMailController() {
    val sendMailImpl : SendMailImpl = SendMailImpl()

    @PostMapping("/SendMail")
    fun sendMail(@RequestBody dataMail: DataMail) : String{
        return sendMailImpl.sendMail(dataMail.stringTo, dataMail.stringMessage, dataMail.stringSubject)
    }
}