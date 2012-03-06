/**
 * Entry
 * 06.03.2012
 * @author Philipp Haussleiter
 *
 */
package de.consolving.clc.model;

/**
 *
 * @author Philipp Haußleiter
 */
public interface Entry {

    public String getTime();

    public String getName();

    public String getId();

    public String getMessage();

    public String getType();

    public void setTime(String time);

    public void setName(String name);

    public void setId(String id);

    public void setMessage(String message);

    public void setType(String type);
}
