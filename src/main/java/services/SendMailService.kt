package services

import javax.mail.internet.MimeMessage

interface SendMailService {
    fun sendMail(stringTo : String, stringMessage : String, stringSubject : String) : String
}