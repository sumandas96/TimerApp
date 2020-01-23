package SumanDAzz.company.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageButton playButton,pauseButton,resetButton;
    EditText textViewhourmin,textViewSec;
    CountDownTimer mcountDownTimer;
    final long Time_millis = 60000;
    long mTime_left = Time_millis;
    boolean isTimerRunning;
    int hour,minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        resetButton = findViewById(R.id.resetButton);
        textViewhourmin = findViewById(R.id.editMinHour);

     //  textViewSec = findViewById(R.id.editsec);





    }
    public void updateTimer(){
         hour = (int)(mTime_left/1000)/3600;
         minutes = (int)((mTime_left/1000)%3600)/60;
        int seconds = (int)(mTime_left/1000)%60;
        //int seconds = secendsLeft-minutes*60;
        //int seconds = (int)(mTime_left/1000)%60 ;

      /*  String myString = Integer.toString(seconds);
        if(seconds <=9){
            myString = "0" + myString;
        }
//        "00:" + "0" +Integer.toString(minutes) +*/
        String timeFormatted = null;
        if(hour>0) {
            timeFormatted = String.format(Locale.getDefault(), "%d:%02d:%02d", hour, minutes,seconds);
        }
         else
        {
            timeFormatted = String.format(Locale.getDefault(), "%02d:%02d",minutes,seconds);
        }

        textViewhourmin.setText(timeFormatted);

    }
    public void countDown(View view){
/*
//        String countSec = textViewSec.getText().toString();
//        long  msec = Long.parseLong(countSec)*100000;
//        long limitSec = 59000;
//        if(msec > limitSec) {
//            Toast.makeText(MainActivity.this,"Invalid input!",Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            textViewSec.setText(textViewSec.getText() + String.valueOf(msec/ 1000));*/

        String countSec = textViewhourmin.getText().toString();
//        String mText = ":00";
//        long  msec = Long.parseLong(countSec)*mTime_left+Long.parseLong(mText);
        long mtime = Long.parseLong(countSec)*mTime_left;
        if( mtime==0 ){
            Toast.makeText(this,"Please Enter a Positive number",Toast.LENGTH_SHORT).show();
        }

            mcountDownTimer = new CountDownTimer(mtime,1000){

                @Override
                public void onTick(long millisUntilFinished) {
                    //updateTimer((int)millisUntilFinished/1000);
                    mTime_left = millisUntilFinished;
                    updateTimer();

                }

                @Override
                public void onFinish() {
                    //textView.setText("00:00:00");
                    Toast.makeText(MainActivity.this, "Finished", Toast.LENGTH_SHORT).show();
                }
            }.start();

        }
        public void pause(View view){
            mcountDownTimer.cancel();
            isTimerRunning = false;

        }
        public void reset(View view){
            mTime_left = Time_millis;
            updateTimer();




        }



    }



