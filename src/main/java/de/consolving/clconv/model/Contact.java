/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.consolving.clconv.model;

/**
 *
 * @author philipp
 */
public class Contact {

    private String name;

    public Contact(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
