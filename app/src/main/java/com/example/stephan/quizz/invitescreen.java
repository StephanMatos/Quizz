package com.example.stephan.quizz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.PrintWriter;

public class invitescreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitescreen);

        final EditText etUsername = (EditText) findViewById(R.id.etsearchplayer);
        final Button Tele = (Button) findViewById(R.id.bdata);
        final Button Java = (Button) findViewById(R.id.bjava);
        final Button math1 = (Button) findViewById(R.id.bmath1);
        final Button digital = (Button) findViewById(R.id.bdigital);


        Tele.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String u = etUsername.getText().toString();
                String b = Tele.getText().toString();
                System.out.println(b+u);
                Invite(u,b);
            }

        }
        );

        Java.setOnClickListener(new View.OnClickListener() {

                                    public void onClick(View v) {
                                        String u = etUsername.getText().toString();
                                        String b = Java.getText().toString();
                                        System.out.println(b+u);
                                        Invite(u,b);
                                    }

                                }
        );

        math1.setOnClickListener(new View.OnClickListener() {

                                    public void onClick(View v) {
                                        String u = etUsername.getText().toString();
                                        String b = math1.getText().toString();
                                        System.out.println(b+u);
                                        Invite(u,b);
                                    }

                                }
        );

        digital.setOnClickListener(new View.OnClickListener() {

                                    public void onClick(View v) {
                                        String u = etUsername.getText().toString();
                                        String b = digital.getText().toString();
                                        System.out.println(b+u);
                                        Invite(u,b);
                                    }

                                }
        );


    }



    public void Invite(String theme, String username){

        Network network = Network.getInstance();
        PrintWriter pw = null;
        pw = network.getPw();
        pw.println("INVITE\n"+theme+"\n"+username);

    }

}
