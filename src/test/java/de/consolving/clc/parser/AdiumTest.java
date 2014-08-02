package de.consolving.clc.parser;

import de.consolving.clc.writer.LoggerWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 * AdiumTest 02.08.2013
 *
 * @author Philipp Haussleiter
 *
 */
public class AdiumTest extends TestCase {

    private final static String ADIUM_LOG_DIR = "src/test/resources/adium.logs";

    public AdiumTest(String testName) {
        super(testName);
        Logger.getLogger(AdiumParser.class.getName()).setLevel(Level.FINE);
    }

    public void testAdiumParse() {
        LoggerWriter writer = new LoggerWriter();
        ChatLogParser parser = AdiumParser.getInstance();
        parser.setLogDirectory(ADIUM_LOG_DIR);
        parser.setWriter(writer);
        parser.parseAndWrite();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
