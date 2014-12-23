package com.example.user.whatcolourisittoday;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread t = new Thread() {
        @Override
        public void run(){
            try {
                while (!isInterrupted()) {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            
                            Calendar c = Calendar.getInstance(); //for date
                            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                            String formattedDate = df.format(c.getTime());
                            TextView colourTextView = (TextView)findViewById(R.id.colourTextView);
                            colourTextView.setText(formattedDate);

                            String newFormattedDate = formattedDate.replace(":", ""); // for hex
                            SimpleDateFormat bf = new SimpleDateFormat("HHmmss"); //for hex
                            TextView hexView = (TextView)findViewById(R.id.hexView); //for hex
                            int hex = Integer.parseInt(newFormattedDate); //for hex string->int
                            hexView.setText("#"+hex); //for hex

                            String hex1 = Integer.toString(hex); //turn hex from int->String for parseColor
                            RelativeLayout colourChange = (RelativeLayout)findViewById(R.id.background);
                            colourChange.setBackgroundColor(Color.parseColor("#"+hex1)); //parseColor here only accepts strings, so we changed hex1 int->String

                        }
                    });
                }
            }
            catch (InterruptedException e) {
            }
        }
    };

    t.start();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
