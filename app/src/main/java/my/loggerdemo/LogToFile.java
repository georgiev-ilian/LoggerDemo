package my.loggerdemo;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Ilian Georgiev.
 */

public final class LogToFile implements Logger {

    private static final String DELIMITER = "|";

    private static final String NEW_LINE = "\n";

    private OutputStream stream;

    private final String logName;

    private final Context context;

    private int length = 0;

    public LogToFile(Context context) throws LoggerException {
        this.context = context;
        logName = String.valueOf(System.currentTimeMillis());
        try {
            stream = context.openFileOutput(logName, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            throw new LoggerException("Cannot open file for logging messages");
        }
    }

    @Override
    public void log(Message message) throws LoggerException {
        try {
            stream.write(message.toString().getBytes());
            length++;
        } catch (IOException e) {
            Log.d("LogToFile", "log: e=" + e);
            throw new LoggerException("Cannot log message to a file");
        }
    }

    @Override
    public void log(String tag, String message) {
        long currentTime = System.currentTimeMillis();
        long threadId = Thread.currentThread().getId();

        String str = currentTime + DELIMITER + threadId + DELIMITER + tag + DELIMITER
                + message + NEW_LINE;

        try {
            stream.write(str.getBytes());
            length++;
        } catch (IOException e) {
            Log.d("LogToFile", "log: e=" + e);
            //throw new LoggerException("Cannot log message to a file");
        }
    }

    public String export() throws LoggerException {
        InputStream inputStream = null;
        String result = null;
        try {
            stream.close();

            inputStream = context.openFileInput(logName);
            byte[] buffer = new byte[inputStream.available()];
            int readResult = inputStream.read(buffer);

            if (readResult > 0) {
                result = new String(buffer);
            }
        } catch (IOException e) {
            throw new LoggerException("Failed to export logger. Cannot open and read the log file.");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new LoggerException("Failed to export logger. Cannot close input stream");
                }
            }

            clear();
        }

        return result;
    }

    public void clear() {
        context.deleteFile(logName);
        length = 0;
    }

    @Override
    public int size() {
        return length;
    }
}
