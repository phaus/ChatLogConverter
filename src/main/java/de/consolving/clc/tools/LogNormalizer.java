/**
 * LogNormalizer 02.08.2013
 *
 * @author Philipp Haussleiter
 *
 */
package de.consolving.clc.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogNormalizer {

    private String tmpDir;
    private File tmpFile;
    private final static String LT = "<";
    private final static String GT = ">";
    private Charset defaultCharSet = Charset.forName("UTF-8");

    public LogNormalizer(String tmpDir) {
        this.tmpDir = tmpDir;
    }

    public void setDefaultCharSet(Charset defaultCharSet) {
        this.defaultCharSet = defaultCharSet;
    }

    /**
     * This Methode wrappes HTML Content in CDATA and writes it in a temporara file.
     * @param logFile
     * @param tag
     * @return the new File with CDATA wrapped Content.
     */
    public File EncodeHTMLinXML(File logFile, String tag) {
        String oldLine;
        try {
            tmpFile = new File(tmpDir + File.separator + logFile.getName());
            tmpFile.createNewFile();
            FileInputStream fis = new FileInputStream(logFile);
            FileOutputStream fos = new FileOutputStream(tmpFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, defaultCharSet));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, defaultCharSet));
            while ((oldLine = br.readLine()) != null) {
                bw.write(returnHTMLMessage(oldLine, tag));
                bw.write("\n");
            }
            bw.flush();
            bw.close();
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LogNormalizer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LogNormalizer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmpFile;
    }

    private String returnHTMLMessage(String line, String tag) {
        if (line.startsWith(LT + tag)) {
            int firstClosed = line.indexOf(GT);
            int endTagIndex = line.indexOf(LT + "/" + tag + GT);
            StringBuilder newLine = new StringBuilder();
            newLine.append(line.substring(0, firstClosed+1));
            newLine.append("<![CDATA[");
            newLine.append(line.substring(firstClosed+1, endTagIndex));
            newLine.append("]]>");
            newLine.append(line.substring(endTagIndex).trim());
            return newLine.toString();
        } else {
            return line;
        }

    }
}
