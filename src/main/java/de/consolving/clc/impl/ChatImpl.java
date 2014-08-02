package de.consolving.clc.impl;

import de.consolving.clc.model.Chat;
import java.util.Date;

/**
 *
 * @author philipp
 */
public class ChatImpl implements Chat {

    private final String account;
    private Date time;

    public ChatImpl(String account) {
        this.account = account;
    }

    public ChatImpl(String account, Date time) {
        this.account = account;
        this.time = time;
    }

    public String getAccount() {
        return this.account;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString() {
        return account + "@" + time.toString();
    }
}
