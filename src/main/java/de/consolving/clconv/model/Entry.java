/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.consolving.clconv.model;

/**
 *
 * @author philipp
 */
public class Entry implements Comparable<Entry> {
    public String time;
    public String name;
    public String id;
    public String message;
    public String type;

    @Override
    public String toString(){
        return time+" "+name+" ("+id+"): "+message;
    }

    @Override
    public int compareTo(Entry t) {
        return this.time.compareTo(t.time);
    }
}
