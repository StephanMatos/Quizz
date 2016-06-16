package com.example.stephan.quizz;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class currentgames extends AppCompatActivity {

    private Button button1,button2,button3,button4,button5,button6,button7,button8;
    private BufferedReader bir;
    private PrintWriter pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentgames);


        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);


    }






    public class refresh extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {



            Network net = Network.getInstance();
            pw = net.getPw();
            bir = net.getBir();
            pw.println("GETINVITE");
            pw.flush();
String s;

            try {

                while(bir.readLine().startsWith())


                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }




}
