package my.loggerdemo;

/**
 * Created by Ilian Georgiev.
 */

public interface Logger {
    /**
     * Stores a message to the logger
     * @param message
     */
    void log(Message message) throws LoggerException;

    /**
     * @return Returns collected logs as a string ready to be send.
     */
    String export() throws LoggerException;

    /**
     * Wipe all collected messages in the logger
     */
    void clear() throws LoggerException;

    /**
     * @return Returns the number of logged messages
     */
    int size();
}
