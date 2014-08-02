/**
 * Entry
 * 06.03.2012
 * @author Philipp Haussleiter
 *
 */
package de.consolving.clc.model;

import java.util.Date;

/**
 *
 * @author Philipp Haußleiter
 */
public interface Entry {

    public Date getTime();

    public String getName();

    public String getId();

    public String getMessage();

    public String getType();

    public void setTime(Date time);

    public void setName(String name);

    public void setId(String id);

    public void setMessage(String message);

    public void setType(String type);
}
