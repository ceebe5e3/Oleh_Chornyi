package ua.nure.chornyi.SummaryTask4.web.command.subscriber;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.db.entity.ServiceTariffs;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.exception.DBException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;
import ua.nure.chornyi.SummaryTask4.web.service.interfaces.ServiceTariffsService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Download tariffs in the format PDF.
 */
public class DownloadPDFCommand extends Command {

    private static final Logger logger = Logger.getLogger(DownloadPDFCommand.class);

    private ServiceTariffsService tariffsService;

    public DownloadPDFCommand(ServiceTariffsService tariffsService) {
        this.tariffsService = tariffsService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("Download PDF file command starts");
        try {
            createPDF();
        } catch (DocumentException | IOException e) {
            logger.error("Cannot create the file.");
        }

        logger.info("File was made.");

        String filename = "Tariffs.pdf";
        String filePath = "D:\\Project\\summaryTask4\\web" + filename;

        response.setHeader("Content-Disposition", "attachment; filename=" + filename);

        byte[] buffer = new byte[4096];

        try (ServletOutputStream outputStream = response.getOutputStream();
             FileInputStream inputStream = new FileInputStream(new File(filePath))) {
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            logger.error("Cannot find the file.");
        }

        logger.info("File is ready for downloading.");

        logger.debug("Command finished");
        return null;
    }


    private void createPDF() throws DocumentException, DBException, IOException {
        List<ServiceTariffs> serviceTariffs = tariffsService.findServicesAndTariffs();
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter.getInstance(document, new FileOutputStream(new File("D:\\Project\\summaryTask4\\web\\Tariffs.pdf")));
        document.open();
        BaseFont baseFont = BaseFont.createFont("C:\\WINDOWS\\Fonts\\ARIAL.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont);
        Paragraph title = new Paragraph("Service&Tariffs", font);
        document.add(title);

        Chapter chapter = new Chapter(title, 1);
        chapter.setNumberDepth(0);

        Section section = chapter.addSection(title);

        PdfPTable pdfPTable = new PdfPTable(5);

        pdfPTable.setSpacingBefore(25);
        pdfPTable.setSpacingAfter(25);

        PdfPCell pdf1 = new PdfPCell(new Phrase("№", font));
        pdfPTable.addCell(pdf1);
        PdfPCell pdf2 = new PdfPCell(new Phrase("Tariff"));
        pdfPTable.addCell(pdf2);
        PdfPCell pdf3 = new PdfPCell(new Phrase("Service"));
        pdfPTable.addCell(pdf3);
        PdfPCell pdf4 = new PdfPCell(new Phrase("Price"));
        pdfPTable.addCell(pdf4);
        PdfPCell pdf5 = new PdfPCell(new Phrase("Description"));
        pdfPTable.addCell(pdf5);

        for (int i = 0; i < serviceTariffs.size(); i++) {
            pdfPTable.addCell(i + 1 + "");
            pdfPTable.addCell(new PdfPCell(new Phrase(serviceTariffs.get(i).getTariff().getName(), font)));
            pdfPTable.addCell(new PdfPCell(new Phrase(serviceTariffs.get(i).getService().getName(), font)));
            pdfPTable.addCell(new PdfPCell(new Phrase(serviceTariffs.get(i).getPrice() + "", font)));
            pdfPTable.addCell(new PdfPCell(new Phrase(serviceTariffs.get(i).getDescription(), font)));
        }
        section.add(pdfPTable);
        document.add(section);
        document.close();
    }
}
