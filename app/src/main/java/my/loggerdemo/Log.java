package my.loggerdemo;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Constant capacity Deque. Use only {@link #offer} method for insertions.
 * Created by Ilian Georgiev.
 */
public final class Log<E> extends LinkedBlockingDeque<E> {

    public static final int LOGGER_CAPACITY = 500;

    private static Log<Message> instance;

    private Log(int capacity) {
        super(capacity);
    }

    @Override
    public boolean offer(E e) {
        boolean result = super.offer(e);

        try {
            if (!result) {
                take();
                result = super.offer(e);
            }
        } catch (InterruptedException ex) {
            return false;
        }

        return result;
    }

    public static Log<Message> getInstance() {
        if (instance == null) {
            instance = new Log<>(LOGGER_CAPACITY);
        }

        return instance;
    }

    /**
     * Appends a message to the logger queue
     * Note : Delimiter characters ; and | will be replaced by space.
     * It is better to avoid using these characters in the message.
     *
     * @param message The message string to be logged
     */
    public static void log(String message) {
        Message loggerMessage = new Message(message);
        Log.getInstance().offer(loggerMessage);
    }

    /**
     * @return Returns collected logs as a string ready to be send to our server.
     */
    public static String export() {
        StringBuilder sb = new StringBuilder();
        Iterator<Message> it = Log.getInstance().iterator();
        Message message;

        while (it.hasNext()) {
            message = it.next();
            sb.append(message.toString()).append("\n");
        }

        return sb.toString();
    }
}
