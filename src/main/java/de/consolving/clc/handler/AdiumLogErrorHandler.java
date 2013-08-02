/**
 * AdiumLogErrorHandler 02.08.2013
 *
 * @author Philipp Haussleiter
 *
 */
package de.consolving.clc.handler;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class AdiumLogErrorHandler implements ErrorHandler {

    private final static Logger LOG = Logger.getLogger(AdiumLogErrorHandler.class.getName());

    public void warning(SAXParseException saxpe) throws SAXException {
        LOG.log(Level.WARNING, "we''ve got some warning: {0}", saxpe.getLocalizedMessage());
    }

    public void error(SAXParseException saxpe) throws SAXException {
        LOG.log(Level.WARNING, "we''ve got some error: {0}", saxpe.getLocalizedMessage());
    }

    public void fatalError(SAXParseException saxpe) throws SAXException {
        LOG.log(Level.WARNING, "we''ve got some fatalError: {0}", saxpe.getLocalizedMessage());
    }
}
