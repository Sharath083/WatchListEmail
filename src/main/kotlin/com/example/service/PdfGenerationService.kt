package com.example.service

import com.example.repository.WatchListDaoImpl
import com.example.utils.appconstants.Constants.BLANK_PDF_PATH
import com.itextpdf.text.Document
import com.itextpdf.text.Phrase
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.PdfStamper
import com.itextpdf.text.pdf.PdfWriter
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.ByteArrayOutputStream
import java.io.File

class PdfGenerationService : KoinComponent {
    private val watchListDaoImpl by inject<WatchListDaoImpl>()

    suspend fun generateEmptyPdf(uuid:String): File {
        val pdfFile = File("D:\\", "WatchList.pdf")

        val templatePath = BLANK_PDF_PATH
        try {
            val document = Document()
            val byteArrayOutputStream = ByteArrayOutputStream()

            val writer = PdfWriter.getInstance(document, byteArrayOutputStream)

            document.open()

            val pageSize = document.pageSize

            val isLandscape = pageSize.width > pageSize.height

            val table = PdfPTable(2)
            table.widthPercentage = 100f


            table.addCell(createCell("WatchListName"))

            table.addCell(createCell("Symbols"))

            val watchListData = watchListDaoImpl.getAllWatchList(uuid)

            watchListData.map{
                table.addCell(createCell(it.watchListName))
                    table.addCell(createCell(it.symbol.toString()))

            }

            document.add(table)

            document.close()

            stampPdfTemplate(byteArrayOutputStream.toByteArray(), pdfFile)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }

        return pdfFile
    }

    private fun stampPdfTemplate(content: ByteArray, outputPdf: File) {
        try {
            val templateReader = PdfReader(BLANK_PDF_PATH)
            val byteArrayOutputStream = ByteArrayOutputStream()
            val stamper = PdfStamper(templateReader, byteArrayOutputStream)

            // Set form flattening to true to modify existing content
            stamper.setFormFlattening(true)
            outputPdf.length()


            // Stamp the content on the template
            val contentByte = stamper.getOverContent(1)
            val writer = stamper.writer
            val importedPage = writer.getImportedPage(PdfReader(content), 1)
            contentByte.addTemplate(importedPage, 0f, 0f)

            // Close the stamper
            stamper.close()

            // Write the stamped content to the output PDF
            outputPdf.outputStream().use { it.write(byteArrayOutputStream.toByteArray()) }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
    private fun createCell(value: String): Phrase {
        return Phrase(value)
    }
}

