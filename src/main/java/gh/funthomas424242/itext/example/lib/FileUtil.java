package gh.funthomas424242.itext.example.lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtil {

    protected final Logger LOG = Logger.getLogger(FileUtil.class.getName());
    protected String workingDirPath;
    protected Boolean streamCreated = Boolean.FALSE;

    public FileUtil(final String workingDirPath) {
        this.workingDirPath = workingDirPath;
    }

    public FileOutputStream createOutputStream(final Class<?> clazz) {

        final String fileName = getFileName(clazz);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            streamCreated = Boolean.FALSE;
        }
        streamCreated = Boolean.TRUE;
        return fileOutputStream;
    }

    private String getFileName(final Class<?> clazz) {
        return workingDirPath + File.separator + clazz.getName() + ".pdf";
    }

    public void printLogMessage(final Class<?> clazz, final Boolean isOK) {
        final String fileName = getFileName(clazz);
        if (Boolean.TRUE.equals(isOK) && streamCreated) {
            LOG.log(Level.INFO, fileName + " successful created");
        } else {
            LOG.log(Level.WARNING, fileName + " has errors");
        }

    }
}
