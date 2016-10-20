package my.loggerdemo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ilian Georgiev.
 */
public class MessageTest {

    @Test
    public void testMessageCreation_normalString() {
        String logMessage = "this is log";
        long currentTime = System.currentTimeMillis();

        String result = "{ currentTime: " + currentTime
                + "; identifier: my.loggerdemo.MessageTest|" +
                "testMessageCreation_normalString|MessageTest.java|21|1; message: " + logMessage + ";}";
        int callingMethodIndex = 3;
        Message message = new Message(logMessage, callingMethodIndex);
        message.setCurrentTime(currentTime);

        assertEquals(result, message.toString());
    }

    @Test
    public void testMessageCreation_stringWithQuotes() {
        String logMessage = "this is ''log it's";
        long currentTime = System.currentTimeMillis();

        String result = "{ currentTime: " + currentTime
                + "; identifier: my.loggerdemo.MessageTest|" +
                "testMessageCreation_stringWithQuotes|MessageTest.java|36|1; message: " + logMessage + ";}";
        int callingMethodIndex = 3;
        Message message = new Message(logMessage, callingMethodIndex);
        message.setCurrentTime(currentTime);

        assertEquals(result, message.toString());
    }

    @Test
    public void testMessageCreation_stringWithEscape() {
        String logMessage = "this is \"hello\" log";
        long currentTime = System.currentTimeMillis();

        String result = "{ currentTime: " + currentTime
                + "; identifier: my.loggerdemo.MessageTest|" +
                "testMessageCreation_stringWithEscape|MessageTest.java|51|1; message: " + logMessage + ";}";
        int callingMethodIndex = 3;
        Message message = new Message(logMessage, callingMethodIndex);
        message.setCurrentTime(currentTime);

        assertEquals(result, message.toString());
    }

    @Test
    public void testMessageCreation_null() {
        String logMessage = null;
        long currentTime = System.currentTimeMillis();

        String result = "{ currentTime: " + currentTime
                + "; identifier: my.loggerdemo.MessageTest|" +
                "testMessageCreation_null|MessageTest.java|66|1;}";
        int callingMethodIndex = 3;
        Message message = new Message(logMessage, callingMethodIndex);
        message.setCurrentTime(currentTime);

        assertEquals(result, message.toString());
    }

    @Test
    public void testMessageCreation_emptyString() {
        String logMessage = "";
        long currentTime = System.currentTimeMillis();

        String result = "{ currentTime: " + currentTime
                + "; identifier: my.loggerdemo.MessageTest|" +
                "testMessageCreation_emptyString|MessageTest.java|81|1; message: ;}";
        int callingMethodIndex = 3;
        Message message = new Message(logMessage, callingMethodIndex);
        message.setCurrentTime(currentTime);

        assertEquals(result, message.toString());
    }

    @Test
    public void testMessageCreation_twoSpaces() {
        String logMessage = "  ";
        long currentTime = System.currentTimeMillis();

        String result = "{ currentTime: " + currentTime
                + "; identifier: my.loggerdemo.MessageTest|" +
                "testMessageCreation_twoSpaces|MessageTest.java|96|1; message:   ;}";
        int callingMethodIndex = 3;
        Message message = new Message(logMessage, callingMethodIndex);
        message.setCurrentTime(currentTime);

        assertEquals(result, message.toString());
    }
}
