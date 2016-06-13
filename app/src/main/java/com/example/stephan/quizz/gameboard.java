package com.example.stephan.quizz;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NevApi")

public class gameboard extends AppCompatActivity {

    TextView etTimer;
    private BufferedReader bir;
    private PrintWriter pw;
    private String answer1,answer2,answer3,answer4,question;
    private ArrayList<String> index;
    private Button bAnswer1,bAnswer2,bAnswer3,bAnswer4;
    private TextView etQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameboard);

        final TextView Player1 = (TextView) findViewById(R.id.Player1);
        final TextView Player2 = (TextView) findViewById(R.id.Player2);
        final TextView Score1 = (TextView) findViewById(R.id.score1);
        final TextView Score2 = (TextView) findViewById(R.id.score2);
        etQuestion = (TextView) findViewById(R.id.etQuestion);
        bAnswer1 = (Button) findViewById(R.id.bAnswer1);
        bAnswer2 = (Button) findViewById(R.id.bAnswer2);
        bAnswer3 = (Button) findViewById(R.id.bAnswer3);
        bAnswer4 = (Button) findViewById(R.id.bAnswer4);
        etTimer = (TextView) findViewById(R.id.etTimer);

        etTimer.setText("00:30");

        final Counter timer = new Counter(30000, 1000);


        bAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });
        bAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });
        bAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });
        bAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });

       new Load().execute();



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


    public class Load extends AsyncTask<Void,String,Void>{


        @Override
        protected Void doInBackground(Void... params) {

            Network net = Network.getInstance();
            BufferedReader bir = net.getBir();
            PrintWriter pw = net.getPw();

            return null;
        }
        protected void onPostExecute(){

            String question = null;
            String answer1 = null;
            String answer2 = null;
            String answer3 = null;
            String answer4 = null;
            try {

                question = bir.readLine();
                answer1 = bir.readLine();
                answer2 = bir.readLine();
                answer3 = bir.readLine();
                answer4 = bir.readLine();

            }catch (IOException e){

            }

            bAnswer1.setText(answer1);
            bAnswer2.setText(answer2);
            bAnswer3.setText(answer3);
            bAnswer4.setText(answer4);
            etQuestion.setText(question);

        }






    }
}
