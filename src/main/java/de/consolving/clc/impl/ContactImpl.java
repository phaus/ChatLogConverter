package de.consolving.clc.impl;

import de.consolving.clc.model.Contact;

/**
 *
 * @author philipp
 */
public class ContactImpl implements Contact {

    private final String name;

    public ContactImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
