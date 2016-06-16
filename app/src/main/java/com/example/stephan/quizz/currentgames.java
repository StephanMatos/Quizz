package com.example.stephan.quizz;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class currentgames extends AppCompatActivity {

    private Button button1,button2,button3,button4,button5,button6,button7,button8;
    private TextView You1, You2, You3, You4, You5,You6, You7, You8, Oppenent1,Oppenent2,Oppenent3,Oppenent4,Oppenent5,Oppenent6,Oppenent7,Oppenent8;
    private BufferedReader bir;
    private PrintWriter pw;
    private ArrayList<Button> buttons;
    private ArrayList<TextView> YOU,OPPONENT;


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
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(button1);buttons.add(button2);buttons.add(button3);buttons.add(button4);buttons.add(button5);buttons.add(button6);buttons.add(button7);buttons.add(button8);

        You1 = (TextView) findViewById(R.id.y1);
        You2 = (TextView) findViewById(R.id.y2);
        You3 = (TextView) findViewById(R.id.y3);
        You4 = (TextView) findViewById(R.id.y4);
        You5 = (TextView) findViewById(R.id.y5);
        You6 = (TextView) findViewById(R.id.y6);
        You7 = (TextView) findViewById(R.id.y7);
        You8 = (TextView) findViewById(R.id.y8);
        ArrayList<TextView> YOU = new ArrayList<>();
        YOU.add(You1);YOU.add(You2);YOU.add(You3);YOU.add(You4);YOU.add(You5);YOU.add(You6);YOU.add(You7);YOU.add(You8);

        Oppenent1 = (TextView) findViewById(R.id.o1);
        Oppenent2 = (TextView) findViewById(R.id.o2);
        Oppenent3 = (TextView) findViewById(R.id.o3);
        Oppenent4 = (TextView) findViewById(R.id.o4);
        Oppenent5 = (TextView) findViewById(R.id.o5);
        Oppenent6 = (TextView) findViewById(R.id.o6);
        Oppenent7 = (TextView) findViewById(R.id.o7);
        Oppenent8 = (TextView) findViewById(R.id.o8);
        ArrayList<TextView> OPPONENT = new ArrayList<>();
        OPPONENT.add(Oppenent1);OPPONENT.add(Oppenent2);OPPONENT.add(Oppenent3);OPPONENT.add(Oppenent4);OPPONENT.add(Oppenent5);OPPONENT.add(Oppenent6);OPPONENT.add(Oppenent7);OPPONENT.add(Oppenent8);

        new refresh().execute();


    }






    public class refresh extends AsyncTask<Void, String, ArrayList> {

        @Override
        protected ArrayList<String> doInBackground(Void... params) {



            Network net = Network.getInstance();
            pw = net.getPw();
            bir = net.getBir();
            ArrayList index = new ArrayList();
            pw.println("GETBOARD");
            pw.flush();

            boolean active = true;


            String s;

            try {

                while(active){

                    s = bir.readLine();
                    index.add(s);

                    if(s.equals("END")){
                        active = false;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return index;
        }

        protected void onPostExecute(ArrayList index){

            for(int i = 0; i<8; i++){
                buttons.get(i).setText("You VS"+index.get(i).toString());
                YOU.get(i).setText(index.get(i*3+1).toString());
                OPPONENT.get(i).setText(index.get(i*3+2).toString());

            }

        }



    }




}
