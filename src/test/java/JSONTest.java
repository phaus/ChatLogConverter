
import de.consolving.clc.parser.AdiumParser;
import de.consolving.clc.parser.ChatLogParser;
import de.consolving.clc.parser.EmpathyParser;
import de.consolving.clc.writer.JSONLoggerWriter;
import junit.framework.TestCase;

/**
 * JSONTest 25.07.2014
 *
 * @author Philipp Haussleiter
 *
 */
public class JSONTest extends TestCase {

    private final static String EMPATHY_LOG_DIR = "src/test/resources/empathy.logs";
    private final static String ADIUM_LOG_DIR = "src/test/resources/adium.logs";

    public JSONTest(String testName) {
        super(testName);
    }

    public void testAdiumWrite() {
        JSONLoggerWriter writer = new JSONLoggerWriter();
        ChatLogParser parser = AdiumParser.getInstance();
        parser.setLogDirectory(ADIUM_LOG_DIR);
        parser.setWriter(writer);
        parser.parseAndWrite();
    }

    public void testEmpathyWrite() {
        JSONLoggerWriter writer = new JSONLoggerWriter();
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
}
