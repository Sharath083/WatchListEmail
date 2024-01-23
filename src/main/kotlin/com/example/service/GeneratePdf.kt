package com.example.service

import com.example.model.RecentWatchListResponse
import com.example.repository.WatchListDaoImpl
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.Phrase
import com.itextpdf.text.Rectangle
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfReader
import com.itextpdf.text.pdf.PdfStamper
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File

class GeneratePdf {

    suspend fun generateEmptyPdf(uuid:String, templatePath: String): File {
        val pdfFile = File("D:\\", "WatchList.pdf")

        try {
            val document = Document()
            val byteArrayOutputStream = ByteArrayOutputStream()

            val writer = PdfWriter.getInstance(document, byteArrayOutputStream)

            document.open()

            val pageSize = document.pageSize

            

            val table = PdfPTable(2)
            table.widthPercentage = 100f


            table.addCell(createCell("WatchListName"))

            table.addCell(createCell("Symbols"))

            val watchListData = WatchListDaoImpl().getAllWatchList(uuid)

            watchListData.map{
                table.addCell(createCell(it.watchListName))
                    table.addCell(createCell(it.symbol.toString()))

            }

            document.add(table)

            document.close()

            stampPdfTemplate(byteArrayOutputStream.toByteArray(), templatePath, pdfFile,watchListData.size)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }

        return pdfFile
    }

    private fun stampPdfTemplate(content: ByteArray, templatePath: String, outputPdf: File,size:Int) {
        try {
            val templateReader = PdfReader(templatePath)
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

}
fun createCell(value: String): Phrase {
    return Phrase(value)
}
