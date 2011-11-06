/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.consolving.clconv.model;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author philipp
 */
public class Chat {
    
    private String account;
    private Set<Entry> entries;
    
    public Chat(String account){
        this.account = account;
        this.entries = new TreeSet<Entry>();
    }
    
    public void addEntry(Entry entry){
        this.entries.add(entry);
    }
    
    @Override
    public String toString(){
        return account;
    }
}
