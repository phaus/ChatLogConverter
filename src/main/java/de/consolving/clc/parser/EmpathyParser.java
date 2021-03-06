/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.consolving.clc.parser;

import de.consolving.clc.handler.EmpathyLogContentHandler;
import de.consolving.clc.impl.AccountImpl;
import de.consolving.clc.impl.ChatImpl;
import de.consolving.clc.impl.ContactImpl;
import de.consolving.clc.writer.ChatLogWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author philipp
 */
public class EmpathyParser implements ChatLogParser {

    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private final static Logger LOG = Logger.getLogger(ChatLogParser.class.getName());
    private static final EmpathyParser instance = new EmpathyParser();
    private File logDirectory;
    private ChatLogWriter writer;

    public static EmpathyParser getInstance() {
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

    private void enumerateAccounts() {
        if (logDirectory.exists()) {
            AccountImpl account;
            for (File accountFolder : logDirectory.listFiles()) {
                if (!accountFolder.getName().startsWith(".")) {
                    account = getAccountFromFile(accountFolder);
                    writer.openAccount(account);
                    enumerateContacts(accountFolder);
                    writer.closeAccount(account);
                }
            }
        } else {
            LOG.log(Level.WARNING, "{0} is not a Folder!", logDirectory.getAbsolutePath());
        }
    }

    @Override
    public void parseAndWrite() {
        enumerateAccounts();
    }

    private void enumerateContacts(File accountFolder) {
        for (File contactFolder : accountFolder.listFiles()) {
            if (!contactFolder.getName().startsWith(".")) {
                enumerateChats(contactFolder, accountFolder);
            }
        }
    }

    private void enumerateChats(File contactFolder, File accountFolder) {
        ContactImpl contact = new ContactImpl(contactFolder.getName().trim());
        writer.openContact(contact);
        for (File chatFile : contactFolder.listFiles()) {
            enumerateEntries(chatFile, accountFolder);
        }
        writer.closeContact(contact);
    }

    private void enumerateEntries(File chatFile, File accountFolder) {
        if (chatFile.isDirectory()) {
            LOG.log(Level.INFO, "{0} is a ChatRoom!", chatFile.getName());
            return;
        }
        ChatImpl chat = getChatFromChatFile(chatFile.getName().trim(), accountFolder.getName().trim());
        writer.openChat(chat);
        try {
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            FileReader reader = new FileReader(chatFile.getAbsolutePath());
            InputSource inputSource = new InputSource(reader);
            EmpathyLogContentHandler handler = new EmpathyLogContentHandler();
            handler.setChat(chat);
            handler.setWriter(writer);
            xmlReader.setContentHandler(handler);
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

    private ChatImpl getChatFromChatFile(String filename, String account) {
        Date date = new Date();
        try {
            date = DATE_FORMAT.parse(filename.replace(".log", ""));
        } catch (ParseException ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
        }
        return new ChatImpl(account, date);
    }

    private String cleanFileName(String name) {
        String find[] = {"_2e", "_40"};
        String replace[] = {".", "@"};
        String modName = name.substring(0, name.length() - 1);
        for (int i = 0; i < find.length; i++) {
            modName = modName.replace(find[i], replace[i]);
        }
        return modName;
    }

    private AccountImpl getAccountFromFile(File accountFile) {
        String accountName = cleanFileName(accountFile.getName());
        String parts[] = accountName.split("_");
        return new AccountImpl(catParts(parts, "", 2, parts.length), parts[1].trim());
    }

    private String catParts(String parts[], String filler, int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex >= toIndex) {
            return "";
        }
        if (parts.length < fromIndex || parts.length < toIndex) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = fromIndex; i < toIndex; i++) {
            sb.append(parts[i]);
            sb.append(filler);
        }
        return sb.toString();
    }
}
