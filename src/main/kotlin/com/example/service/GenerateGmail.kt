package com.example.service

import com.example.repository.UserDaoImpl
import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.EmailAttachment
import org.apache.commons.mail.MultiPartEmail

class GenerateGmail {

    suspend fun generateEmailWithAttachment(uuid: String) {
        try {
            val userMail=UserDaoImpl().getUserGmail(uuid)

            val email = MultiPartEmail()

            email.hostName = "smtp.gmail.com"
            email.setSmtpPort(465)
            email.setSSLOnConnect(true)
            email.setAuthenticator(DefaultAuthenticator("sharath.r@marketsimplified.com",
                "sharath@s38"))
            email.isSSLOnConnect = true

            
            email.setFrom("sharath.r@marketsimplified.com")
            email.subject = "Sample Email with Attachment"
            email.setMsg("Hi bro")
            email.addTo(userMail)

            val templatePath = "D:\\BlankPdf.pdf"
            GeneratePdf().generateEmptyPdf(uuid, templatePath)

            val attachment = EmailAttachment()
            attachment.path = "D:\\WatchList.pdf"
            attachment.description = "Sample PDF Attachment"
            attachment.name = "Your Symbols.pdf"
            email.attach(attachment)

            
            email.send()

        
        } catch (e: Exception) {
            throw CommonException(e.toString,HttpStatusCode.InternalServerError)
        }


    }
    
    

}
