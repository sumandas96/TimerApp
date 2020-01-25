package SumanDAzz.company.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

private long timeCountInMillSeconds = 1 * 60000;

private enum TimerStatus{
    STARTED,
    STOPPED
}

    private TimerStatus timerStatus = TimerStatus.STOPPED;

    private EditText editTextMinute;
    private EditText editTextSec;
    private TextView textViewTime;
    private ImageView imageViewReset;
    private ImageView imageViewStartStop;
    private CountDownTimer countDownTimer;
    public RelativeLayout layout;
    private Button lightButton, darkButton,secButton,minButton;

    protected int mode = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();

        initListeners();

    }

    private void initViews() {
        editTextMinute = findViewById(R.id.editTextMinute);
        editTextSec = findViewById(R.id.editTextSeconds);
        textViewTime = findViewById(R.id.textViewTime);
        imageViewReset = findViewById(R.id.imageViewReset);
        imageViewStartStop = findViewById(R.id.imageViewStartStop);
        layout = findViewById(R.id.activity_main);
        darkButton = findViewById(R.id.mySwitchButton);
        lightButton = findViewById(R.id.mySwitchButton2);
        minButton = findViewById(R.id.timerButtonMin);
        secButton = findViewById(R.id.timerButtonSec);

    }

    private void initListeners() {
        imageViewStartStop.setOnClickListener(this);
        imageViewReset.setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.imageViewReset:
                reset();
                break;
            case R.id.imageViewStartStop:
                startStop();
                break;
        }
    }

    public void reset() {
        stopCountDownTimer();
        startCountDownTimer();
    }

    private void startStop() {

        if (timerStatus == TimerStatus.STOPPED) {
            if(mode == 1) {
                setTimerValue(1);
            }

            else if(mode == 2) {
                setTimerValue(2);
            }

            else {
                Toast.makeText(getApplicationContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
            }


            imageViewReset.setVisibility(View.VISIBLE);

            imageViewStartStop.setImageResource(R.drawable.icon_pause);

            editTextMinute.setEnabled(false);

            timerStatus = TimerStatus.STARTED;

            startCountDownTimer();

        } else {

            imageViewReset.setVisibility(View.GONE);

            imageViewStartStop.setImageResource(R.drawable.icon_play);

            editTextMinute.setEnabled(true);

            timerStatus = TimerStatus.STOPPED;

            stopCountDownTimer();
        }

    }

    private void setTimerValue(int mode) {
        if(mode == 1) {
            int time = 0;
            if (!editTextMinute.getText().toString().isEmpty()) {
                time = Integer.parseInt(editTextMinute.getText().toString().trim());



            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.message_minute), Toast.LENGTH_LONG).show();
            }

            timeCountInMillSeconds = time * 60 * 1000;
        }

        else if(mode == 2) {
            int time = 0;
            if (!editTextSec.getText().toString().isEmpty()) {
                time = Integer.parseInt(editTextSec.getText().toString().trim());

            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.message_second), Toast.LENGTH_LONG).show();
            }

            timeCountInMillSeconds = time * 1000;

        }

        else {
            Toast.makeText(getApplicationContext(), getString(R.string.error), Toast.LENGTH_SHORT).show();
        }
    }


    private void startCountDownTimer(){

        countDownTimer = new CountDownTimer(timeCountInMillSeconds,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                textViewTime.setText(hmsTimeFormatter(millisUntilFinished));

            }

            @Override
            public void onFinish() {
                textViewTime.setText(hmsTimeFormatter(timeCountInMillSeconds));
                imageViewReset.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),getString(R.string.Finished), Toast.LENGTH_SHORT).show();
                imageViewStartStop.setImageResource(R.drawable.icon_play);
                editTextMinute.setEnabled(true);
                timerStatus = TimerStatus.STOPPED;
            }
        }.start();
        countDownTimer.start();
    }

    private void stopCountDownTimer() {
        countDownTimer.cancel();
    }

    private String hmsTimeFormatter(long milliSeconds) {

        String hms = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds)- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));

        return hms;
    }

    public void onClickMin(View view) {
        editTextMinute.setVisibility(View.VISIBLE);
        editTextSec.setVisibility(View.INVISIBLE);
        active(1);
    }

    public void onClickSec(View view) {
        editTextSec.setVisibility(View.VISIBLE);
        editTextMinute.setVisibility(View.INVISIBLE);
        active(2);
    }

    private void active(int k) {
        mode = k;
    }

    public void onClickTheme (View view) {
        //Dark theme
        layout.setBackgroundColor(Color.parseColor("#1E2538"));
        lightButton.setVisibility(View.VISIBLE);
        darkButton.setVisibility(View.INVISIBLE);
        textViewTime.setTextColor(Color.parseColor("#f39c12"));
        secButton.setBackgroundResource(R.drawable.dark_button);
        minButton.setBackgroundResource(R.drawable.dark_button);
    }

    public void onClickThemeLight (View view) {
        //Default theme
        layout.setBackgroundColor(Color.parseColor("#2980b9"));
        lightButton.setVisibility(View.INVISIBLE);
        darkButton.setVisibility(View.VISIBLE);
        textViewTime.setTextColor(Color.parseColor("#ecf0f1"));
        secButton.setBackgroundResource(R.drawable.buttonblue);
        minButton.setBackgroundResource(R.drawable.buttonblue);
    }




}