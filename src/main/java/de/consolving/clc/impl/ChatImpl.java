package de.consolving.clc.impl;

import de.consolving.clc.model.Chat;

/**
 *
 * @author philipp
 */
public class ChatImpl implements Chat {

    private final String account;

    public ChatImpl(String account) {
        this.account = account;
    }

    public String getAccount() {
        return this.account;
    }

    @Override
    public String toString() {
        return account;
    }

}
