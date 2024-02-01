package com.example.service

import com.example.config.EnvironmentVariables
import com.example.exceptions.CommonException
import com.example.model.SuccessResponse
import com.example.repository.UserDaoImpl
import com.example.utils.appconstants.Constants
import com.example.utils.appconstants.Constants.EMAIL_HOST
import com.example.utils.appconstants.Constants.EMAIL_PORT
import com.example.utils.appconstants.Constants.EMAIL_SUBJECT
import io.ktor.http.*
import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.EmailAttachment
import org.apache.commons.mail.MultiPartEmail
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GmailGenerationService:KoinComponent {
    private val pdfGenerationService by inject<PdfGenerationService>()
    private val userDao by inject<UserDaoImpl>()
    private val env=EnvironmentVariables.env



    private suspend fun generateEmailWithAttachment(uuid: String) :String {

        return try {
            val userMail=userDao.getUserGmail(uuid)

            val email = MultiPartEmail()

            email.hostName = EMAIL_HOST
            email.setSmtpPort(EMAIL_PORT)
            email.setSSLOnConnect(true)
            email.setAuthenticator(DefaultAuthenticator(
                env.adminEmail,
                env.adminPassword))
            email.isSSLOnConnect = true

            email.setFrom(env.adminEmail)
            email.subject = EMAIL_SUBJECT
            email.setMsg(Constants.EMAIL_MESSAGE)
            email.addTo(userMail)

            pdfGenerationService.generateEmptyPdf(uuid)

            val attachment = EmailAttachment()
            attachment.path = Constants.PDF_PATH
            attachment.description = Constants.ATTACHMENT_DESCRIPTION
            attachment.name = Constants.PDF_NAME
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