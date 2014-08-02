package de.consolving.clc.impl;

import de.consolving.clc.model.Entry;
import java.util.Date;

/**
 *
 * @author philipp
 */
public class EntryImpl implements Comparable<Entry>, Entry {

    private Date time;
    private String name;
    private String id;
    private String message = "";
    private String type;

    @Override
    public String toString() {
        return type + ": " + time + " " + name + " (" + id + "): " + message;
    }

    @Override
    public int compareTo(Entry t) {
        return this.time.compareTo(t.getTime());
    }

    public Date getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setType(String type) {
        this.type = type;
    }
}
