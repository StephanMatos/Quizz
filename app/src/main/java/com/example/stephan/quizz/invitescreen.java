package com.example.stephan.quizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class invitescreen extends AppCompatActivity {

    public BufferedReader bir;
    public PrintWriter pw;
    public Socket sock;
    private String user;

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
                Invite(b,u);
            }

        }
        );

        Java.setOnClickListener(new View.OnClickListener() {

                                    public void onClick(View v) {
                                        String u = etUsername.getText().toString();
                                        String b = Java.getText().toString();System.out.println(b+u);
                                        Invite(b,u);
                                    }

                                }
        );

        math1.setOnClickListener(new View.OnClickListener() {

                                    public void onClick(View v) {
                                        String u = etUsername.getText().toString();
                                        String b = math1.getText().toString();
                                        System.out.println(b+u);
                                        Invite(b,u);
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

        Network net = Network.getInstance();
        Socket sock = net.getSock();
        BufferedReader bir = net.getBir();
        PrintWriter pw = net.getPw();
        String user = net.getUsername();
        System.out.println(theme+username+"        "+bir+"       "+pw);
        pw.println("INVITE\n"+theme+"\n"+username+"\n"+user);
        pw.flush();
        String s = null;
        try {
            s = bir.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(s.equals("OK")){

            Intent gameIntent = new Intent(invitescreen.this, gameboard.class);
            invitescreen.this.startActivity(gameIntent);

        }else{
            Toast.makeText(invitescreen.this,"User not found, try again",Toast.LENGTH_LONG).show();
        }


    }

}
