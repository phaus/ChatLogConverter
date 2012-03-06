/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import de.consolving.clc.parser.ChatLogParser;
import de.consolving.clc.parser.PurpleParser;
import de.consolving.clc.writer.LoggerWriter;
import junit.framework.TestCase;

/**
 *
 * @author philipp
 */
public class PurpleTest extends TestCase {

    private final static String PURPLE_LOG_DIR = "src/test/resources/purple.logs";

    public PurpleTest(String testName) {
        super(testName);
    }

    public void testPurpleParse() {
        LoggerWriter writer = new LoggerWriter();
        ChatLogParser parser = PurpleParser.getInstance();
        parser.setLogDirectory(PURPLE_LOG_DIR);
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
    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
}
