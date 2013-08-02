
import de.consolving.clc.parser.AdiumParser;
import de.consolving.clc.parser.ChatLogParser;
import de.consolving.clc.writer.LoggerWriter;
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
