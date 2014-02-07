package gh.funthomas424242.itext.example;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Titelblatt extends PDFCreator {

    @Override
    protected void createPDF(final FileOutputStream outStream)
            throws DocumentException {

        final Document document = new Document(PageSize.A8.rotate());
        PdfWriter.getInstance(document, outStream);
        document.open();
        Paragraph paragraph = new Paragraph("Titelblatt!");
        document.add(paragraph);
        document.close();
    }

}
