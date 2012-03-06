/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.consolving.clc.parser;

import de.consolving.clc.impl.AccountImpl;
import de.consolving.clc.impl.ContactImpl;
import de.consolving.clc.writer.ChatLogWriter;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author philipp
 */
public class PurpleParser implements ChatLogParser {

    private final static Logger LOG = Logger.getLogger(PurpleParser.class.getName());
    private static PurpleParser instance = new PurpleParser();
    private File logDirectory;
    private ChatLogWriter writer;

    public static PurpleParser getInstance() {
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
                enumerateChats(contactFolder);
            }
        }
    }

    private void enumerateChats(File contactFolder) {
        ContactImpl contact = new ContactImpl(contactFolder.getName().trim());
        writer.openContact(contact);
        for (File chatFile : contactFolder.listFiles()) {
            enumerateEntries(chatFile);
        }
        writer.closeContact(contact);
    }

    private void enumerateEntries(File chatFile) {
    }
}
