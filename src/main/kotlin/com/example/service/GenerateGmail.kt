package com.example.service

import com.example.exceptions.CommonException
import com.example.model.SuccessResponse
import com.example.repository.UserDaoImpl
import com.example.utils.appconstants.EmailConstants
import com.example.utils.appconstants.EmailConstants.EMAIL_HOST
import com.example.utils.appconstants.EmailConstants.EMAIL_PORT
import com.example.utils.appconstants.EmailConstants.EMAIL_SUBJECT
import io.ktor.http.*
import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.EmailAttachment
import org.apache.commons.mail.MultiPartEmail

class GenerateGmail {

    private suspend fun generateEmailWithAttachment(uuid: String) :String {
        return try {
            val userMail=UserDaoImpl().getUserGmail(uuid)

            val email = MultiPartEmail()

            email.hostName = EMAIL_HOST
            email.setSmtpPort(EMAIL_PORT)
            email.setSSLOnConnect(true)
            email.setAuthenticator(DefaultAuthenticator(
                EmailConstants.ADMIN_MAIL,
                EmailConstants.ADMIN_PASS))
            email.isSSLOnConnect = true

            email.setFrom(EmailConstants.ADMIN_MAIL)
            email.subject = EMAIL_SUBJECT
            email.setMsg(EmailConstants.EMAIL_MESSAGE)
            email.addTo(userMail)

            val templatePath = EmailConstants.BLANK_PDF_PATH
            GeneratePdf().generateEmptyPdf(uuid, templatePath)

            val attachment = EmailAttachment()
            attachment.path = EmailConstants.PDF_PATH
            attachment.description = EmailConstants.ATTACHMENT_DESCRIPTION
            attachment.name = EmailConstants.PDF_NAME
            email.attach(attachment)

            email.send()

        } catch (e: Exception) {
            throw CommonException("Failed to send email:$e  ${e.message}", HttpStatusCode.InternalServerError)
        }
    }
    suspend fun emailCheck(uuid: String):SuccessResponse{
        if(generateEmailWithAttachment(uuid).isNotEmpty()){
            return SuccessResponse("EMAIL SENT SUCCESSFULLY",HttpStatusCode.OK.toString())
        }
        else{
            throw CommonException("EMAIL NOT SENT",HttpStatusCode.BadRequest)
        }
    }
}