package gh.funthomas424242.itext.example;

import gh.funthomas424242.itext.example.lib.FileUtil;

import java.io.FileOutputStream;

import com.itextpdf.text.DocumentException;

public abstract class PDFCreator implements Runnable {

    protected final FileUtil filesystem = new FileUtil("target");

    @Override
    public void run() {

        final FileOutputStream outStream = createOutputStream();
        if (outStream != null) {
            try {
                createPDF(outStream);
                filesystem.printLogMessage(Boolean.TRUE);
            } catch (DocumentException e) {
                filesystem.printLogMessage(Boolean.FALSE);
            }

        } else {
            filesystem.printLogMessage(Boolean.FALSE);
        }

    }

    protected final FileOutputStream createOutputStream() {
        return filesystem.createOutputStream(this.getClass());
    }

    protected abstract void createPDF(final FileOutputStream outStream)
            throws DocumentException;
}
