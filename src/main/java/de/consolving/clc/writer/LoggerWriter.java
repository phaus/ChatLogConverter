/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.consolving.clc.writer;

import de.consolving.clc.model.Account;
import de.consolving.clc.model.Chat;
import de.consolving.clc.model.Contact;
import de.consolving.clc.model.Entry;

/**
 *
 * @author philipp
 */
public class LoggerWriter implements ChatLogWriter {

    @Override
    public void openChat(Chat chat) {
        System.out.println("\t\topening: " + chat);
    }

    @Override
    public void writerEntry(Entry entry, Chat chat) {
        System.out.println("\t\t\t" + entry);
    }

    @Override
    public void closeChat(Chat chat) {
        System.out.println("\t\tclosing: " + chat);
    }

    @Override
    public void openAccount(Account account) {
        System.out.println("\n\nopening: " + account);
    }

    @Override
    public void closeAccount(Account account) {
        System.out.println("\nclosing: " + account + "\n");
    }

    @Override
    public void openContact(Contact contact) {
        System.out.println("\topening: " + contact);
    }

    @Override
    public void closeContact(Contact contact) {
        System.out.println("\tclosing: " + contact);
    }
}
