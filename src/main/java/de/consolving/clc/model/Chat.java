/**
 * Chat 06.03.2012
 *
 * @author Philipp Haussleiter
 *
 */
package de.consolving.clc.model;

import java.util.Date;

/**
 *
 * @author Philipp Haußleiter
 */
public interface Chat {

    public String getAccount();

    public Date getTime();
    
    public void setTime(Date time);
}
