package my.loggerdemo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ilian Georgiev.
 */

public class LogTest {

    @Test
    public void testLog_capacity() {
        LogMemory logger = new LogMemory();

        logger.log(new Message("Hello I'm a logger message to be logged #1"));
        logger.log(new Message("Hello I'm a logger message to be logged #2"));

        assertEquals(2, logger.size());

        for (int i = 0; i < 100000; i++) {
            logger.log(new Message("Hello I'm a logger message to be logged"));
        }

        assertEquals(LogMemory.DEFAULT_MEMORY_CAPACITY, logger.size());
    }
}
