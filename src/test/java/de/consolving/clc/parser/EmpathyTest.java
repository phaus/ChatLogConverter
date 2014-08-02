package de.consolving.clc.parser;

import de.consolving.clc.writer.LoggerWriter;
import junit.framework.TestCase;

/**
 *
 * @author philipp
 */
public class EmpathyTest extends TestCase {

    private final static String EMPATHY_LOG_DIR = "src/test/resources/empathy.logs";

    public EmpathyTest(String testName) {
        super(testName);
    }

    public void testEmpathyParse() {
        LoggerWriter writer = new LoggerWriter();
        ChatLogParser parser = EmpathyParser.getInstance();
        parser.setLogDirectory(EMPATHY_LOG_DIR);
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
