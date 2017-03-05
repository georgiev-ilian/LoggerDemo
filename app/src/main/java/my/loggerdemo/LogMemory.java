package my.loggerdemo;

import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Constant capacity Deque. Use only {@link #offer} method for insertions.
 * Created by Ilian Georgiev.
 */
public final class LogMemory extends LinkedBlockingDeque<Message> implements Logger {

    public static final int DEFAULT_MEMORY_CAPACITY = 500;

    public LogMemory() {
        this(DEFAULT_MEMORY_CAPACITY);
    }

    public LogMemory(int capacity) {
        super(capacity);
    }

    @Override
    public boolean offer(@NonNull Message message) {
        boolean result = super.offer(message);

        try {
            if (!result) {
                take();
                result = super.offer(message);
            }
        } catch (InterruptedException ex) {
            return false;
        }

        return result;
    }

    @Override
    public void log(Message message) {
        offer(message);
    }

    @Override
    public void log(String tag, String message) {

    }

    @Override
    public String export() {
        StringBuilder sb = new StringBuilder();
        Iterator<Message> it = iterator();
        Message message;

        while (it.hasNext()) {
            message = it.next();
            sb.append(message.toString()).append("\n");
        }

        return sb.toString();
    }
}
