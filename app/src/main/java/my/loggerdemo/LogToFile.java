package my.loggerdemo;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Ilian Georgiev.
 */

public class LogToFile {

    private static LogToFile instance;

    private OutputStream stream;

    private final String logName;

    private final Context context;

    private LogToFile(Context context) {
        this.context = context;
        logName = String.valueOf(System.currentTimeMillis());
        try {
            stream = context.openFileOutput(logName, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static LogToFile getInstance(Context context) {
        if (instance == null) {
            instance = new LogToFile(context);
        }

        return instance;
    }

    public void log(String message) {
        Message loggerMessage = new Message(message);
        try {
            stream.write(loggerMessage.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String export() {
        InputStream inputStream = null;
        String result = null;
        try {
            stream.close();

            inputStream = context.openFileInput(logName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            result = new String(buffer);
            /*if (inputStream.read(buffer) == -1) {
                return new String(buffer);
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            clear();
        }

        return result;
    }

    public void clear() {
        context.deleteFile(logName);
    }
}
