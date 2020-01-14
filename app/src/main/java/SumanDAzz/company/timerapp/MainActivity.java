package SumanDAzz.company.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton imageButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageButton = findViewById(R.id.imageButton);
        textView = findViewById(R.id.textView);
    }
    public void updateTimer(int secendsLeft){
        int minutes = (int)secendsLeft/60;
        int seconds = secendsLeft-minutes*60;
        String myString = Integer.toString(seconds);
        if(seconds <=9){
            myString = "0" + myString;
        }
        textView.setText("00:" + "0" +Integer.toString(minutes) + ":" + myString);
    }
    public void countDown(View view){
        new CountDownTimer(10000,1000){

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
}
