package SumanDAzz.company.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton playButton;
    TextView textViewSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = findViewById(R.id.playButton);
        textViewSec = findViewById(R.id.editSec);
    }
    public void updateTimer(int secendsLeft){
        int minutes = (int)secendsLeft/60;
        int seconds = secendsLeft-minutes*60;
        String myString = Integer.toString(seconds);
        if(seconds <=9){
            myString = "0" + myString;
        }
//        "00:" + "0" +Integer.toString(minutes) +
        textViewSec.setText(":" + myString);
    }
    public void countDown(View view){
        String countSec = textViewSec.getText().toString();
        long  msec = Long.parseLong(countSec)*1000;
        textViewSec.setText(textViewSec.getText() + String.valueOf(msec/ 1000));
        new CountDownTimer(msec,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int)millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                //textView.setText("00:00:00");
                Toast.makeText(MainActivity.this, "Finished", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    public void countPause(View view){

    }
}
