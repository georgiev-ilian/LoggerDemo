package my.loggerdemo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ilian Georgiev.
 */

public class LogTest {

    @Test
    public void testLog_capacity() {
        LogMemory<Message> log = LogMemory.getInstance();

        LogMemory.log("Hello I'm a logger message to be logged #1");
        LogMemory.log("Hello I'm a logger message to be logged #2");

        assertEquals(2, log.size());

        for (int i = 0; i < 100000; i++) {
            LogMemory.log("Hello I'm a logger message to be logged");
        }

        assertEquals(LogMemory.LOGGER_CAPACITY, log.size());
    }
}
