package gh.funthomas424242.itext.example;

import gh.funthomas424242.itext.example.lib.FileUtil;

import java.awt.Color;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfSpotColor;
import com.itextpdf.text.pdf.SpotColor;

public abstract class PDFCreator implements Runnable {

    // CMYK Farbsystem für Druckereien
    // chamois from http://www.colorcombos.com/tags/colors/chamois EDE6B6
    protected final CMYKColor CHAMOIS_DARK_CMYK = new CMYKColor(0f, 0.03f,
            0.232f, 0.071f);
    protected final CMYKColor CHAMOIS_LIGHT_CMYK = new CMYKColor(0.0f, 0.0f,
            0.137f, 0.0f);
    // Spot Color für Druckereien
    final private PdfSpotColor psc_cmyk = new PdfSpotColor(
            "iTextSpotColorCMYK", CHAMOIS_LIGHT_CMYK);
    final protected SpotColor CHAMOIS_SPOT = new SpotColor(psc_cmyk, 0.5f);

    // RGB für Bildschirmdarstellung
    protected final BaseColor CHAMOIS_RGB = new BaseColor(255, 255, 220);
    // simple
    protected final Color CHAMOIS_AWT_COLOR = new java.awt.Color(0xFF, 0xFF,
            0xDE);

    protected final FileUtil filesystem = new FileUtil("target");

    @Override
    public void run() {

        final FileOutputStream outStream = createOutputStream();
        if (outStream != null) {
            try {
                createPDF(outStream);
                filesystem.printLogMessage(this.getClass(), Boolean.TRUE);
            } catch (DocumentException e) {
                filesystem.printLogMessage(this.getClass(), Boolean.FALSE);
            }

        } else {
            filesystem.printLogMessage(this.getClass(), Boolean.FALSE);
        }

    }

    protected final FileOutputStream createOutputStream() {
        return filesystem.createOutputStream(this.getClass());
    }

    protected void printLogMessage(final String text) {
        filesystem.printLogMessage(this.getClass(), text);

    }

    protected abstract void createPDF(final FileOutputStream outStream)
            throws DocumentException;

}
