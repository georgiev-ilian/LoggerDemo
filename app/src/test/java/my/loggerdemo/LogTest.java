package my.loggerdemo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ilian Georgiev.
 */

public class LogTest {

    @Test
    public void testLog_capacity() {
        Log<Message> log = Log.getInstance();

        Log.log("Hello I'm a logger message to be logged #1");
        Log.log("Hello I'm a logger message to be logged #2");

        assertEquals(2, log.size());

        for (int i = 0; i < 100000; i++) {
            Log.log("Hello I'm a logger message to be logged");
        }

        assertEquals(Log.LOGGER_CAPACITY, log.size());
    }
}
