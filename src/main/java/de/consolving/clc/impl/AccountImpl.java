/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.consolving.clc.impl;

import de.consolving.clc.model.Account;

/**
 *
 * @author philipp
 */
public class AccountImpl implements Account {

    private final String protocol;
    private final String name;

    public AccountImpl(String name, String protocol) {
        this.protocol = protocol;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name + " " + this.protocol;
    }

    public String getName() {
        return name;
    }

    public String getProtocol() {
        return protocol;
    }
}
