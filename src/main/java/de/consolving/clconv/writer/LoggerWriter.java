/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.consolving.clconv.writer;

import de.consolving.clconv.model.Account;
import de.consolving.clconv.model.Chat;
import de.consolving.clconv.model.Contact;
import de.consolving.clconv.model.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

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
