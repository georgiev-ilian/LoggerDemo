package my.loggerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

        /*log("Message #1    helllo |';;'' asd l|l");
        log("Message #2");
        log("Message #3 o;o helloppp|||");
        log(null);
        log("Message #4");*/

        //android.util.Log.d("MainActivity", "onResume: hello");

        LogToFile.getInstance(this).log("Message #1");
        LogToFile.getInstance(this).log("Message #2");
        LogToFile.getInstance(this).log("Message #3");

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

        android.util.Log.d("MainActivity", "onResume: #1");
        android.util.Log.d("MainActivity", "onResume: " + LogToFile.getInstance(this).export());
        android.util.Log.d("MainActivity", "onResume: #2");
    }
}
