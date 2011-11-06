/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.consolving.clconv.model;

/**
 *
 * @author philipp
 */
public class Account {
    private String protocol;
    private String name;
    
    public Account(String name, String protocol){
        this.protocol = protocol;
        this.name = name;
    }
    
    @Override
    public String toString(){
        return this.name+" "+this.protocol;
    }
}
