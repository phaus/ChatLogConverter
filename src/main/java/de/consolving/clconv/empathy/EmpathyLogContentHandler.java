/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.consolving.clconv.empathy;

import de.consolving.clconv.model.Chat;
import de.consolving.clconv.model.Entry;
import de.consolving.clconv.writer.ChatLogWriter;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 *
 * @author philipp
 */
public class EmpathyLogContentHandler implements ContentHandler {

    private ChatLogWriter writer;
    private Chat chat;
    private Entry entry = null;

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public void setWriter(ChatLogWriter writer) {
        this.writer = writer;
    }

    @Override
    public void setDocumentLocator(Locator lctr) {
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startPrefixMapping(String string, String string1) throws SAXException {
    }

    @Override
    public void endPrefixMapping(String string) throws SAXException {
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if ("message".equals(qName)) {
            entry = new Entry();
            for (int i = 0; i < atts.getLength(); i++) {
                if ("time".equals(atts.getQName(i))) {
                    entry.time = atts.getValue(i);
                }
                if ("name".equals(atts.getQName(i))) {
                    entry.name = atts.getValue(i);
                }
                if ("type".equals(atts.getQName(i))) {
                    entry.type = atts.getValue(i);
                }
                if ("id".equals(atts.getQName(i))) {
                    entry.id = atts.getValue(i);
                }
            }
        }
    }

    @Override
    public void endElement(String string, String string1, String string2) throws SAXException {
    }

    @Override
    public void characters(char[] chars, int start, int length) throws SAXException {
        if (entry == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < (start + length); i++) {
            sb.append(chars[i]);
        }
        entry.message = sb.toString();
        this.writer.writerEntry(entry, chat);
        entry = null;
    }

    @Override
    public void ignorableWhitespace(char[] chars, int i, int i1) throws SAXException {
    }

    @Override
    public void processingInstruction(String string, String string1) throws SAXException {
    }

    @Override
    public void skippedEntity(String string) throws SAXException {
    }
}
