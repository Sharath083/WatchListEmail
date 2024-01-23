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

            // SMTP server settings for Gmail
            email.hostName = "smtp.gmail.com"
            email.setSmtpPort(465)
            email.setSSLOnConnect(true)
            email.setAuthenticator(DefaultAuthenticator("sharath.r@marketsimplified.com",
                "sharath@s38"))
            email.isSSLOnConnect = true

            // Email details
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

            // Send the email
            email.send()

            println("Email sent successfully")
        } catch (e: Exception) {
            println("Failed to send email: ${e.message}")
            e.printStackTrace()
        }


    }
    suspend fun generateEmptyGmail(uuid:String): MultiPartEmail {
        val userMail=UserDaoImpl().getUserGmail(uuid)
        println("=---------------$userMail")
        val email = MultiPartEmail()
        email.hostName = "smtp.gmail.com"
        email.setSmtpPort(465)
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
        println("11111111113333333333333333333333333333333331111111111111")

        email.send()
        println("111111111122222222222222222222222222222111111111111111111111111111111111111111")

        return email
    }

}