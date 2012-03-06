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
public class PurpleWriter implements ChatLogWriter {

    @Override
    public void openChat(Chat chat) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void writerEntry(Entry entry, Chat chat) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void closeChat(Chat chat) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void openAccount(Account account) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void closeAccount(Account account) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void openContact(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void closeContact(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
