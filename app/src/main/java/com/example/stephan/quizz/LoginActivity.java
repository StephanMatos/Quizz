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
    public Client client1;
    private BufferedReader bir;
    private View v;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button blogin = (Button) findViewById(R.id.blogin);
        final TextView tvRegister = (TextView) findViewById(R.id.tvRegister);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
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

                Intent loginIntent = new Intent(LoginActivity.this, startmenu.class);
                LoginActivity.this.startActivity(loginIntent);
            }
        });
    }

    /*
    public void ShowAlert(View view) {
        AlertDialog.Builder alarm = new AlertDialog.Builder(this);
        alarm.setMessage("Connection Established").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).create();
    }
    */


    public void ShowAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Connection")
                .setMessage("Yay! Although this dialog now has two options...")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // try again
                        //dialog.dismiss();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }



    private Socket sock;
    private PrintWriter pw;
    private String username,password;
    public boolean loggedIn;

    public class Login extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            String ip = "10.16.168.102";
            String s = null;
            try{
                sock = new Socket(ip,2048);
                System.out.println(sock);
                bir = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                pw = new PrintWriter(sock.getOutputStream());
            } catch(IOException e){
                e.printStackTrace();
            }

            username = params[0];
            password = params[1];
            System.out.println(username+password+"Jeg er inde i metoden");
            try {
                pw.println("LOGIN\n"+username+"\n"+password);
                pw.flush();
                s = bir.readLine();
                if(s.equals("OK")){

                }
            } catch (IOException e) {
                cancel(true);
            }
            return s;
        }

        protected void onPostExecute(String s){
            if(s.equals("OK")){
                loggedIn = true;

            }
            else {
                loggedIn = false;
                ShowAlert();
            }
        }

        protected  void onCancelled(String s) {

        }



    }

}
