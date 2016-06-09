package com.example.stephan.quizz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LoginActivity extends AppCompatActivity {

    private GoogleApiClient client;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button blogin = (Button) findViewById(R.id.blogin);
        final TextView tvRegister = (TextView) findViewById(R.id.tvRegister);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, register.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        blogin.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {

                                          String u = etUsername.getText().toString();
                                          String p = etPassword.getText().toString();
                                          System.out.println(u+p);

                                          new Login().execute(u,p);

                                      }

                                  }
        );
    }

    public class Login extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... params) {

            Network net = Network.getInstance();
            net.Init();
            Socket sock = net.getSock();
            BufferedReader bir = net.getBir();
            PrintWriter pw = net.getPw();

            String username = params[0];
            String password = params[1];
            System.out.println(username+password+"Jeg er inde i metoden");

            pw.println("LOGIN\n"+username+"\n"+password);
            pw.flush();
            try {

                String s = bir.readLine();
                System.out.println(s);
                if (s.equals("OK")) {
                    System.out.println("jeg burde gå videre");
                    Intent loginIntent = new Intent(LoginActivity.this, startmenu.class);
                    LoginActivity.this.startActivity(loginIntent);
                } else {
                    System.out.println("jeg er oppe i else haha");
                    AlertDialog.Builder fail = new AlertDialog.Builder(LoginActivity.this);
                    fail.setMessage("Failed to log in").setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    fail.show();

                }
            }catch (IOException e){
                e.printStackTrace();

            }
            return null;
        }



    }


}