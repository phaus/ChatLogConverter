/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.consolving.clconv.writer;

import de.consolving.clconv.model.Account;
import de.consolving.clconv.model.Chat;
import de.consolving.clconv.model.Contact;
import de.consolving.clconv.model.Entry;

/**
 *
 * @author philipp
 */
public interface ChatLogWriter {
    public void openAccount(Account account);
    public void openContact(Contact contact);
    public void openChat(Chat chat);
    public void writerEntry(Entry entry, Chat chat);
    public void closeContact(Contact contact);
    public void closeChat(Chat chat);
    public void closeAccount(Account account);
}
