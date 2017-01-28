package edu.asu.msse.pkondudu.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekbar;
    TextView timerTextView;
    Button controllerButton;
    CountDownTimer countDownTimer;
    Boolean counterIsActive = false;

    public void resetTimer() {
        timerTextView.setText("0:30");
        timerSeekbar.setProgress(30);
        timerSeekbar.setEnabled(true);
        countDownTimer.cancel();
        controllerButton.setText("Go!");
        counterIsActive = false;
    }

    public void updateTimer(int i) {
        int minutes = (int) i/60;
        int seconds = i - (minutes * 60);

        timerTextView = (TextView) findViewById(R.id.timerTextView);

        String secondsString = "";

        if (seconds <= 9) {
            secondsString = "0" + seconds;
        } else {
            secondsString = String.valueOf(seconds);
        }
        timerTextView.setText(String.valueOf(minutes) + ":" + secondsString);
    }

    public void controlTimer(View view) {
        Log.i("Button pressed", "in controlTimer");

        if (counterIsActive == false) {

            counterIsActive = true;

            timerSeekbar.setEnabled(false);

            controllerButton.setText("Stop");

            countDownTimer = new CountDownTimer(timerSeekbar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
//                Log.i("Timer done", "Finished");
                    resetTimer();
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                }
            }.start();
        } else {
            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekbar = (SeekBar) findViewById(R.id.timerSeekBar);
        controllerButton = (Button) findViewById(R.id.controllerButton);

        timerSeekbar.setMax(600);
        timerSeekbar.setProgress(30);

        timerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
