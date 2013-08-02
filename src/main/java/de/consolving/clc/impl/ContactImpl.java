package de.consolving.clc.impl;

import de.consolving.clc.model.Contact;

/**
 *
 * @author philipp
 */
public class ContactImpl implements Contact {

    private String name;

    public ContactImpl(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
