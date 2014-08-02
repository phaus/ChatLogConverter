/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.consolving.clc.parser;

import de.consolving.clc.impl.AccountImpl;
import de.consolving.clc.impl.ChatImpl;
import de.consolving.clc.impl.ContactImpl;
import de.consolving.clc.writer.ChatLogWriter;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author philipp
 */
public class PurpleParser implements ChatLogParser {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd.HHmmsszzzzzzzzz");
    private final static Logger LOG = Logger.getLogger(PurpleParser.class.getName());
    private static final PurpleParser INSTANCE = new PurpleParser();
    private File logDirectory;
    private ChatLogWriter writer;

    public static PurpleParser getInstance() {
        return INSTANCE;
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
                account = new AccountImpl(accountFolder.getName(), protocolFolder.getName());
                writer.openAccount(account);
                enumerateContacts(accountFolder);
                writer.closeAccount(account);
            }
        }
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
        ChatImpl chat = getChatFromEntryFile(accountFolder.getName().trim(), chatFile.getName().trim());
        writer.openChat(chat);
        writer.closeChat(chat);
    }

    private ChatImpl getChatFromEntryFile(String account, String filename) {
        Date date = new Date();
        try {
            date = DATE_FORMAT.parse(filename.replace(".html", ""));
        } catch (ParseException ex) {
            LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
        }
        return new ChatImpl(account, date);
    }
}
