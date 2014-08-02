/**
 * AdiumLogContentHandler 02.08.2013
 *
 * @author Philipp Haussleiter
 *
 */
package de.consolving.clc.handler;

import de.consolving.clc.impl.ChatImpl;
import de.consolving.clc.impl.EntryImpl;
import de.consolving.clc.model.Entry;
import de.consolving.clc.writer.ChatLogWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class AdiumLogContentHandler implements ContentHandler {

    // 2013-11-03T22:44:19+01:00

    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    private final static Logger LOG = Logger.getLogger(AdiumLogContentHandler.class.getName());
    private static final String EVENT = "event";
    private static final String STATUS = "status";
    private static final String MESSAGE = "message";
    private static final String NORMAL = "normal";
    private ChatLogWriter writer;
    private ChatImpl chat;
    private Entry entry = null;

    public void setChat(ChatImpl chat) {
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
        if (EVENT.equals(qName)) {
            entry = new EntryImpl();
            entry = getEntryWithAttributes(entry, atts);
            entry.setName(entry.getId());
            this.writer.writerEntry(entry, chat);
        }
        if (STATUS.equals(qName)) {
            entry = new EntryImpl();
            entry = getEntryWithAttributes(entry, atts);
            this.writer.writerEntry(entry, chat);
        }
        if (MESSAGE.equals(qName)) {
            entry = new EntryImpl();
            entry.setType(NORMAL);
            entry = getEntryWithAttributes(entry, atts);
        }
    }

    private Date parseDate(String dateString) throws ParseException {
        Date date = null;
        int stringLen = dateString.trim().length();
        if (stringLen > 6) {
            String zPart = dateString.substring(stringLen - 6, stringLen);
            date = TIME_FORMAT.parse(dateString.substring(0, stringLen - 6) + zPart.replace(":", ""));
        }
        return date;
    }

    private Entry getEntryWithAttributes(Entry e, Attributes atts) {
        for (int i = 0; i < atts.getLength(); i++) {
            if ("time".equals(atts.getQName(i))) {
                try {
                    entry.setTime(parseDate(atts.getValue(i)));
                } catch (ParseException ex) {
                    LOG.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
                }
            }
            if ("sender".equals(atts.getQName(i))) {
                entry.setId(atts.getValue(i));
            }
            if ("type".equals(atts.getQName(i))) {
                entry.setType(atts.getValue(i));
            }
            if ("alias".equals(atts.getQName(i))) {
                entry.setName(atts.getValue(i));
            }
        }
        return e;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (entry != null && NORMAL.equals(entry.getType()) && MESSAGE.equals(qName)) {
            this.writer.writerEntry(entry, chat);
            entry = null;
        }
    }

    @Override
    public void characters(char[] chars, int start, int length) throws SAXException {
        if (entry == null || !NORMAL.equals(entry.getType())) {
            return;
        }
        StringBuilder sb = new StringBuilder(entry.getMessage());
        for (int i = start; i < (start + length); i++) {
            sb.append(chars[i]);
        }
        entry.setMessage(sb.toString());
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
