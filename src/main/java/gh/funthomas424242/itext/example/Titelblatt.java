package gh.funthomas424242.itext.example;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Titelblatt extends PDFCreator {

    @Override
    protected void createPDF(final FileOutputStream outStream)
            throws DocumentException {

        final Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outStream);
        document.open();

        document.addTitle("Titel");
        document.addSubject("Thema");
        document.addKeywords("iText");
        document.addAuthor("Thomas Schubert");
        document.addCreator("ProgrammName = iText");
        document.addProducer();
        document.addLanguage("deDE");

        final Paragraph titel = new Paragraph("Titelzeile");
        titel.setAlignment(Element.ALIGN_CENTER);
        document.add(titel);

        final Paragraph paragraph = new Paragraph(
                "Das sollte ein langer Text werden.");

        paragraph.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
        document.add(paragraph);
        document.close();
    }

}
