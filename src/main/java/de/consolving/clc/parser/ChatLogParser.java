/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.consolving.clc.parser;

import de.consolving.clc.writer.ChatLogWriter;

/**
 *
 * @author philipp
 */
public interface ChatLogParser {
    public void setWriter(ChatLogWriter writer);
    public void setLogDirectory(String logPath);
    public void parseAndWrite();
}
