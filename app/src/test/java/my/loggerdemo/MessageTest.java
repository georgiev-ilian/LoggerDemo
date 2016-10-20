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
                "testMessageCreation_normalString|MessageTest.java|15|1; message:" + logMessage;
        Message message = new Message(logMessage);
        message.setCurrentTime(currentTime);

        assertEquals(result, message.toString());
    }
}
