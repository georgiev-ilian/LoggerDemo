package my.loggerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    //private static LoggerDeque<LoggerMessage> loggerDeque = new LoggerDeque<>(1000);

    //public static final String CONSTANT = "This is constant";

    //public final String[] CONSTANT_ARRAY = new String[100000000];

    //public String myVariable = "This is variable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Logger logger = null;
        try {
            logger = new LogToFile(this);
            logger.log(new Message("Message #1"));
            logger.log(new Message("Message #1"));
            logger.log(new Message("Message #1"));
        } catch (LoggerException e) {
            Log.d("MainActivity", "Cannot create logger and log messages", e);
        }

        /*Iterator<Message> it = Log.getInstance().iterator();
        Message message;

        while (it.hasNext()) {
            message = it.next();
            android.util.Log.d("MainActivity", "onResume: " + message.toString());
        }*/

/*
        for (int i = 0; i < 100000; i++) {
            //Log.a("Hello I'm a logger message to be logged");
            LogToFile.getInstance(this).log("Message #1");
        }
*/

        /*for (int i = 0; i < 100000000; i++) {
            CONSTANT_ARRAY[i] = "Hello my constant";
        }*/

        if (logger != null) {
            android.util.Log.d("MainActivity", "onResume: #1");

            try {
                Log.d("MainActivity", "onResume: " + logger.export());
            } catch (LoggerException e) {
                Log.d("MainActivity", "Cannot export from logger", e);
            }

            android.util.Log.d("MainActivity", "onResume: #2");
        }
    }
}
