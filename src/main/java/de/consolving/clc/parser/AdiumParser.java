/**
 * AdiumParser 02.08.2013
 *
 * @author Philipp Haussleiter
 *
 */
package de.consolving.clc.parser;

import de.consolving.clc.handler.AdiumLogContentHandler;
import de.consolving.clc.handler.AdiumLogErrorHandler;
import de.consolving.clc.impl.AccountImpl;
import de.consolving.clc.impl.ChatImpl;
import de.consolving.clc.impl.ContactImpl;
import de.consolving.clc.tools.LogNormalizer;
import de.consolving.clc.writer.ChatLogWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class AdiumParser implements ChatLogParser {

    private final static Logger LOG = Logger.getLogger(AdiumParser.class.getName());
    private static AdiumParser instance = new AdiumParser();
    private File logDirectory;
    private ChatLogWriter writer;
    private LogNormalizer ln;

    public static AdiumParser getInstance() {
        return instance;
    }

    @Override
    public void setWriter(ChatLogWriter writer) {
        this.writer = writer;
    }

    @Override
    public void setLogDirectory(String logPath) {
        File path = new File(logPath);
        if (path.exists()) {
            this.logDirectory = path;
        }
    }

    @Override
    public void parseAndWrite() {
        enumerateProtocols();
    }

    private AdiumParser() {
        ln = new LogNormalizer("/tmp");
    }

    private void enumerateProtocols() {
        if (logDirectory.exists()) {
            for (File protocolFolder : this.logDirectory.listFiles()) {
                if (!protocolFolder.getName().startsWith(".")) {
                    enumerateAccounts(protocolFolder);
                }
            }
        } else {
            LOG.log(Level.SEVERE, "{0} is not a Folder!", logDirectory.getAbsolutePath());
        }
    }

    private void enumerateAccounts(File protocolFolder) {
        AccountImpl account;
        for (File accountFolder : protocolFolder.listFiles()) {
            if (!accountFolder.getName().startsWith(".")) {
                account = new AccountImpl(getNameFromFolder(accountFolder.getName()), protocolFolder.getName());
                writer.openAccount(account);
                enumerateContacts(accountFolder);
                writer.closeAccount(account);
            }
        }
    }

    private void enumerateContacts(File accountFolder) {
        for (File contactFolder : accountFolder.listFiles()) {
            if (!contactFolder.getName().startsWith(".")) {
                enumerateChats(contactFolder);
            }
        }
    }

    /**
     * Enumerates the Log Files itself. Older Version of Adium did not use a
     * Bundle for Logs, just a plain file.
     *
     * @param contactFolder
     */
    private void enumerateChats(File contactFolder) {
        ContactImpl contact = new ContactImpl(getNameFromFolder(contactFolder.getName()));
        writer.openContact(contact);
        // This is for older Adium Versions.
        if (contactFolder.isFile()) {
            enumerateEntries(contactFolder);
        } else {
            for (File chatFile : contactFolder.listFiles()) {
                if (!chatFile.getName().startsWith(".")) {
                    enumerateEntries(chatFile);
                }
            }
        }
        writer.closeContact(contact);
    }

    private void enumerateEntries(File chatFile) {
        ChatImpl chat = new ChatImpl(getNameFromFolder(chatFile.getName()));
        writer.openChat(chat);
        try {
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            chatFile = ln.EncodeHTMLinXML(chatFile, "message");
            FileReader reader = new FileReader(chatFile.getAbsolutePath());
            InputSource inputSource = new InputSource(reader);
            AdiumLogContentHandler handler = new AdiumLogContentHandler();
            handler.setChat(chat);
            handler.setWriter(writer);
            xmlReader.setContentHandler(handler);
            xmlReader.setErrorHandler(new AdiumLogErrorHandler());
            xmlReader.parse(inputSource);
        } catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        writer.closeChat(chat);
    }

    private static String getNameFromFolder(String folder) {
        String[] parts = folder.trim().split(" ");
        return parts[0];
    }
}
