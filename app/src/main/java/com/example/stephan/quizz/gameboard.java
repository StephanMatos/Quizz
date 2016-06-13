package com.example.stephan.quizz;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NevApi")

public class gameboard extends AppCompatActivity {

    TextView etTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameboard);

        final TextView Player1 = (TextView) findViewById(R.id.Player1);
        final TextView Player2 = (TextView) findViewById(R.id.Player2);
        final TextView Score1 = (TextView) findViewById(R.id.score1);
        final TextView Score2 = (TextView) findViewById(R.id.score2);
        final TextView etQuistion = (TextView) findViewById(R.id.etQuestion);
        final Button bAnswer1 = (Button) findViewById(R.id.bAnswer1);
        final Button bAnswer2 = (Button) findViewById(R.id.bAnswer2);
        final Button bAnswer3 = (Button) findViewById(R.id.bAnswer3);
        final Button bAnswer4 = (Button) findViewById(R.id.bAnswer4);
        etTimer = (TextView) findViewById(R.id.etTimer);

        etTimer.setText("00:30");

        final Counter timer = new Counter(30000, 1000);


        bAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NevApi")

    public class Counter extends CountDownTimer {

         /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public Counter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
        @SuppressLint("NevApi")
        @Override
        public void onTick(long millisUntilFinished) {

            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millis),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

            System.out.print(hms);

            if (millisUntilFinished < 30000) {
                etTimer.setTextColor(Color.GREEN);
            }
            if (millisUntilFinished < 20001) {
                etTimer.setTextColor(Color.YELLOW);
            }
            if(millisUntilFinished < 10001) {
                etTimer.setTextColor(Color.RED);
            }

            etTimer.setText(hms);

        }

        @Override
        public void onFinish() {

        etTimer.setText("Better be faster next time!");

        }
    }
}