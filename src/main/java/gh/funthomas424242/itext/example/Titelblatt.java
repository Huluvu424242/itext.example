package gh.funthomas424242.itext.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import com.itextpdf.text.Annotation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Jpeg;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.HyphenationAuto;
import com.itextpdf.text.pdf.HyphenationEvent;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.PngImage;

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

        addMetadaten(document);
        addTitelzeile(document);
        addAbsaetze(document);

        try {
            final URL bildURL = new URL(
                    "http://upload.wikimedia.org/wikipedia/commons/7/74/Flugzeuglandung_einer_airberlin.jpg");
            final Jpeg bild = new Jpeg(bildURL);
            bild.setAlignment(Paragraph.ALIGN_CENTER);
            bild.scalePercent(13);
            final Annotation annotation = new Annotation("Lizenz",
                    "CC-BY-SA-3.0 created by Huluvu424242");
            bild.setAnnotation(annotation);
            document.add(bild);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        document.close();
    }

    private void addAbsaetze(final Document document) throws DocumentException {
        final Font font12 = new Font(cousineRegular, 12);
        font12.setColor(new CMYKColor(0.8f, 0.4f, 0, 0));
        final Paragraph paragraph = new Paragraph(
                "Das ist ein Beispiel für einen Absatz. Man schreibt einfach "
                        + "flüssig weiter ohne Umbruch und dann bricht der"
                        + " Absatz"
                        + " schon alles von selber um. Das ist ein schöner"
                        + " Schreibspaß"
                        + " wenn man nicht über Abstände, Umbrüche"
                        + " und Silbentrennung" + " nachdenken muss :) ",
                font12);

        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(paragraph);
        // Paragraph ist wiederbenutzbar nach dem Kopieren ins Dokument
        // Der nächste Paragraph ist identisch + ein Satz
        paragraph.add("Das ist ein zusätzlicher Satz, den der vorherige Absatz"
                + " nicht enthält.");
        document.add(paragraph);
        // clear löscht nur den Text nicht die Formatierung
        paragraph.clear();
        paragraph.add("Ein völlig neuer Absatz mit völlig neuem Text."
                + " Was soll man dazu sagen? Das ist schon eine tolle "
                + "Sache dieses iText. Viel Spass kann man damit haben "
                + "Frau aber auch :) ");
        document.add(paragraph);
        paragraph.clear();
        // Einzüge definieren - dadurch verbessert sich die Lesbarkeit
        paragraph.setFirstLineIndent(10f);
        paragraph
                .add("Das sah bislang schon ganz gut aus aber die"
                        + " Zwischenräume zwischen den Absätzen sind noch zu klein. "
                        + "Daher jetzt einfach einen Einzug definiert und schon beginnt "
                        + "dieser Absatz ein Stückchen weiter rechts. "
                        + "So gefällt uns das gleich viel besser und man kann auch "
                        + "alles gleich viel besser und flüssiger lesen.");
        document.add(paragraph);

        // Silbentrennung anwenden - so wird der Text kompakter
        // Neue Deutsche Rechtschreibung - mindestens 3 Zeichen vor und 2 nach
        // dem Trennstrich sonst keine Trennung
        // Wir benötigen einen neuen Paragraphen, da sich die Trennregeln
        // scheinbar nicht überschreiben lassen
        final Paragraph neuerParagraph = new Paragraph();
        neuerParagraph.setFont(font12);
        neuerParagraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        final HyphenationEvent trennRegeln = new HyphenationAuto("de", "DR", 3,
                2);
        neuerParagraph.setHyphenation(trennRegeln);
        final Phrase text = new Phrase("Das wird ein völlig neuer Absatz. "
                + "Schön lang mit hoffentlich langen Wörtern. "
                + "Dazu ein paar zusätzliche Wörter. "
                + "Schifffahrt, Buchstabensuppe, "
                + "Gedankenkombinationen und Gefierschrankkombinationen, "
                + "Werkstattbeleuchtung im Zwischenraum der Parmutationen"
                + " einer Glasvitrine sind im "
                + "Hochhaussesselliftes durch die Raumpflegerin unbegehbar."
                + "\n"
                + "OK, Der Satz war etwas sinnfrei aber als Beispiel gut "
                + "geeignet.");
        neuerParagraph.add(text);
        document.add(neuerParagraph);
    }

    private Rectangle createPageTemplate() {
        // PageSize.A4 aber veränderbar zum Setzen der Hintergrundfarbe
        final Rectangle pageTemplate = new Rectangle(PageSize.A4.getWidth(),
                PageSize.A4.getHeight());

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

    private void addMetadaten(final Document document) {
        document.addTitle("Titel");
        document.addSubject("Thema");
        document.addKeywords("iText");
        document.addAuthor("Thomas Schubert");
        document.addCreator("ProgrammName = iText");
        document.addProducer();
        document.addLanguage("deDE");
    }

}
