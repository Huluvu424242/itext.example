package gh.funthomas424242.itext.example;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;

public class Titelblatt extends PDFCreator {

    protected BaseFont cousineBold = null;
    protected BaseFont cousineBoldItalic = null;
    protected BaseFont cousineItalic = null;
    protected BaseFont cousineRegular = null;

    public Titelblatt() {

        super();

        try {
            cousineBold = BaseFont.createFont(
                    "src/main/resources/fonts/Cousine-Bold.ttf",
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            cousineBoldItalic = BaseFont.createFont(
                    "src/main/resources/fonts/Cousine-BoldItalic.ttf",
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            cousineItalic = BaseFont.createFont(
                    "src/main/resources/fonts/Cousine-Italic.ttf",
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            cousineRegular = BaseFont.createFont(
                    "src/main/resources/fonts/Cousine-Regular.ttf",
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (Exception ex) {
            printLogMessage("Could not create the base fonts");
        }

    }

    @Override
    protected void createPDF(final FileOutputStream outStream)
            throws DocumentException {

        final Rectangle pageTemplate = createPageTemplate();
        final Document document = new Document(pageTemplate);
        PdfWriter.getInstance(document, outStream);
        document.open();

        addMetaData(document);
        addTitelzeile(document);

        final Font font12 = new Font(cousineRegular, 12);
        font12.setColor(new CMYKColor(0.8f, 0.4f, 0, 0));
        final Paragraph paragraph = new Paragraph(
                "Das ist ein Beispiel für einen Absatz. Man schreibt einfach "
                        + "flüssig weiter ohne Umbruch und dann bricht der"
                        + " Absatz"
                        + " schon alles von selber um. Das ist ein schöner"
                        + " Schreibspaß"
                        + " wenn man nicht über Abstände, Umbrüche"
                        + " und Silbentrennung" + " nachdenken muss :)", font12);

        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(paragraph);
        document.close();
    }

    private Rectangle createPageTemplate() {
        // PageSize.A4 aber veränderbar zum Setzen der Hintergrundfarbe
        final Rectangle pageTemplate = new Rectangle(PageSize.A4.getHeight(),
                PageSize.A4.getWidth());

        pageTemplate.setBackgroundColor(CHAMOIS_LIGHT_CMYK);
        pageTemplate.setBorder(PageSize.A4.getBorder());
        return pageTemplate;
    }

    private void addTitelzeile(final Document document)
            throws DocumentException {
        final Font font24 = new Font(cousineBoldItalic, 24);
        // blue defined at http://www.colorcombos.com/colors/336699
        final CMYKColor blue = new CMYKColor(0.667f, 0.333f, 0f, 0.4f);
        font24.setColor(blue);
        final Paragraph titel = new Paragraph("Titelzeile", font24);
        titel.setAlignment(Element.ALIGN_CENTER);
        document.add(titel);
    }

    private void addMetaData(final Document document) {
        document.addTitle("Titel");
        document.addSubject("Thema");
        document.addKeywords("iText");
        document.addAuthor("Thomas Schubert");
        document.addCreator("ProgrammName = iText");
        document.addProducer();
        document.addLanguage("deDE");
    }

}
