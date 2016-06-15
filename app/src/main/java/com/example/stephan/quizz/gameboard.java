package com.example.stephan.quizz;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

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
    private String answer1, answer2, answer3, answer4, question;
    private ArrayList<String> index;
    private Button bAnswer1, bAnswer2, bAnswer3, bAnswer4;
    private TextView etQuestion,Score1,Score2,Player1,Player2;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameboard);

        Player1 = (TextView) findViewById(R.id.Player1);
        Player2 = (TextView) findViewById(R.id.Player2);
        Score1 = (TextView) findViewById(R.id.score1);
        Score2 = (TextView) findViewById(R.id.score2);
        etQuestion = (TextView) findViewById(R.id.etQuestion);
        bAnswer1 = (Button) findViewById(R.id.bAnswer1);
        bAnswer2 = (Button) findViewById(R.id.bAnswer2);
        bAnswer3 = (Button) findViewById(R.id.bAnswer3);
        bAnswer4 = (Button) findViewById(R.id.bAnswer4);
        etTimer = (TextView) findViewById(R.id.etTimer);

        etTimer.setText("00:30");

        final Counter timer = new Counter(30000, 1000);

        new Load().execute();

        timer.start();

        bAnswer1.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            String s = bAnswer1.getText().toString();
                                            sendAnswer(s);
                                            new getAnswer().execute(s);

                                        }
                                    }
        );
        bAnswer2.setOnClickListener(new View.OnClickListener() {

                                        public void onClick(View v) {
                                            String s = bAnswer2.getText().toString();
                                            sendAnswer(s);
                                            new getAnswer().execute(s);
                                        }
                                    }
        );
        bAnswer3.setOnClickListener(new View.OnClickListener() {

                                        public void onClick(View v) {
                                            String s = bAnswer3.getText().toString();
                                            sendAnswer(s);
                                            new getAnswer().execute(s);
                                        }

                                    }
        );
        bAnswer4.setOnClickListener(new View.OnClickListener() {

                                        public void onClick(View v) {
                                            String s = bAnswer4.getText().toString();
                                            sendAnswer(s);
                                            new getAnswer().execute(s);
                                        }

                                    }

        );


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "gameboard Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.stephan.quizz/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "gameboard Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.stephan.quizz/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
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
            if (millisUntilFinished < 10001) {
                etTimer.setTextColor(Color.RED);
            }

            etTimer.setText(hms);

        }

        @Override
        public void onFinish() {

            etTimer.setText("Better be faster next time!");

        }
    }

    public class getAnswer extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            Network net = Network.getInstance();
            BufferedReader bir = net.getBir();
            PrintWriter pw = net.getPw();


            String answer0 = params[0];

            String answer1 = null;
            try {
                answer1 = bir.readLine();
            }catch (IOException e){
                e.printStackTrace();
            }

            if(!answer0.equals(answer1)){
                System.out.println(answer1+"Dette er answer 1");
                return answer1;

            }

            System.out.println(answer0+"Dette er answer 0");
            return answer0;

        }

        protected void onPostExecute(String s) {

            System.out.println(s+"Dette er den retunerede s");

            if(bAnswer1.getText().toString().equals(s)){
                bAnswer1.setBackgroundColor(Color.parseColor("#000000"));
            }
            else if(bAnswer2.getText().toString().equals(s)){
                bAnswer2.setBackgroundColor(Color.parseColor("#000000"));
            }
            else if(bAnswer3.getText().toString().equals(s)){
                bAnswer3.setBackgroundColor(Color.parseColor("#000000"));

            }
            else if(bAnswer4.getText().toString().equals(s)){
                bAnswer4.setBackgroundColor(Color.parseColor("#000000"));
            }



            Network net = Network.getInstance();
            bir = net.getBir();

            String s1 = null;
            try{
                s1 = bir.readLine();
            }catch (IOException e){
                e.printStackTrace();
            }
            System.out.println(s1+"Dette er s1");



            if (s1.equals("1")) {
                Intent gameIntent = new Intent(gameboard.this, gameboard.class);
                gameboard.this.startActivity(gameIntent);
                pw.println("NEW QUESTION");
                pw.flush();
            } else if(s1.equals("2")){
                Intent gameIntent = new Intent(gameboard.this, gameboard.class);
                gameboard.this.startActivity(gameIntent);
                pw.println("NEW QUESTION");
                pw.flush();
            } else {

                Toast.makeText(gameboard.this, "Game over", Toast.LENGTH_LONG).show();
                Intent Home = new Intent(gameboard.this, startmenu.class);
                gameboard.this.startActivity(Home);


            }





        }


    }


    public class Load extends AsyncTask<Void, String, ArrayList> {


        @Override
        protected ArrayList<String> doInBackground(Void... params) {


            Network net = Network.getInstance();
            BufferedReader bir = net.getBir();
            PrintWriter pw = net.getPw();

            String question;
            String answer1;
            String answer2;
            String answer3;
            String answer4;
            String player1;
            String player2;
            String score1;
            String score2;
            ArrayList<String> index = new ArrayList();


            try {

                question = bir.readLine();
                answer1 = bir.readLine();
                answer2 = bir.readLine();
                answer3 = bir.readLine();
                answer4 = bir.readLine();
                player1 = bir.readLine();
                player2 = bir.readLine();
                score1 = bir.readLine();
                score2 = bir.readLine();

                index.add(question);
                index.add(answer1);
                index.add(answer2);
                index.add(answer3);
                index.add(answer4);
                index.add(player1);
                index.add(player2);
                index.add(score1);
                index.add(score2);


            } catch (IOException e) {
                e.printStackTrace();

            }
            return index;
        }


        protected void onPostExecute(ArrayList index) {

            etQuestion.setText(index.get(0).toString());
            bAnswer1.setText(index.get(1).toString());
            bAnswer2.setText(index.get(2).toString());
            bAnswer3.setText(index.get(3).toString());
            bAnswer4.setText(index.get(4).toString());
            Player1.setText(index.get(5).toString());
            Player2.setText(index.get(6).toString());
            Score1.setText(index.get(7).toString());
            Score2.setText(index.get(8).toString());
        }
    }

    /*public class Update extends AsyncTask<Void, String, ArrayList>{

        @Override
        protected ArrayList<String> doInBackground(Void... params) {

            Network net = Network.getInstance();
            bir = net.getBir();
            pw = net.getPw();

            String player1 = null;
            String player2 = null;
            String score1 = null;
            String score2 = null;

            try{
                pw.println("UPDATE");
                pw.flush();
                player1 = bir.readLine();
                player2 = bir.readLine();
                score1 = bir.readLine();
                score2 = bir.readLine();

            }catch (IOException e){
                e.printStackTrace();
            }

            ArrayList<String> update = new ArrayList<>();
            update.add(player1);
            update.add(player2);
            update.add(score1);
            update.add(score2);


            return update;
        }

        protected void onPostExecute(ArrayList update) {


            Player1.setText(update.get(0).toString());
            Player2.setText(update.get(1).toString());
            Score1.setText(update.get(2).toString());
            Score2.setText(update.get(3).toString());

        }




    }*/




    public void sendAnswer(String string) {
        Network net = Network.getInstance();
        pw = net.getPw();

        pw.println("ANSWER" + "\n" + string);
        pw.flush();

    }


}
