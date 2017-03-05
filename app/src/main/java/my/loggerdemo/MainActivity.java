package my.loggerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";

    private Logger logger = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    logger.log(new Message("Message #4"));
                    logger.log(new Message("Message #5"));
                } catch (LoggerException e) {
                    e.printStackTrace();
                }
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logger.log(LOG_TAG, "Message #4");
                logger.log(LOG_TAG, "Message #5");
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d("MainActivity", "onClick: " + logger.export());
                } catch (LoggerException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            logger = new LogToFile(this);
            /*logger.log(new Message("Message #1"));
            logger.log(new Message("Message #2"));
            logger.log(new Message("Message #3"));*/
        } catch (LoggerException e) {
            Log.d("MainActivity", "Cannot create logger and log messages", e);
        }

/*
        if (logger != null) {
            Log.d("MainActivity", "onResume: #1");

            try {
                Log.d("MainActivity", "onResume: " + logger.export());
            } catch (LoggerException e) {
                Log.d("MainActivity", "Cannot export from logger", e);
            }

            android.util.Log.d("MainActivity", "onResume: #2");
        }
*/
    }
}
