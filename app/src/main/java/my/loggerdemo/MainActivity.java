package my.loggerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

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
            logger.log(new Message("Message #2"));
            logger.log(new Message("Message #3"));
        } catch (LoggerException e) {
            Log.d("MainActivity", "Cannot create logger and log messages", e);
        }

        if (logger != null) {
            Log.d("MainActivity", "onResume: #1");

            try {
                Log.d("MainActivity", "onResume: " + logger.export());
            } catch (LoggerException e) {
                Log.d("MainActivity", "Cannot export from logger", e);
            }

            android.util.Log.d("MainActivity", "onResume: #2");
        }
    }
}
