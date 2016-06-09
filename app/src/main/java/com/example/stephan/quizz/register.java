package com.example.stephan.quizz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class register extends AppCompatActivity {

    private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText atPassword = (EditText) findViewById(R.id.atPassword);
        final Button bregister = (Button) findViewById(R.id.bregister);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        bregister.setOnClickListener(new View.OnClickListener() {
                                         public void onClick(View v) {



                                             String u = etUsername.getText().toString();
                                             String p = etPassword.getText().toString();
                                             String p1 = atPassword.getText().toString();
                                             if(!p.equals(p1)){
                                                 AlertDialog.Builder fail = new AlertDialog.Builder(register.this);
                                                 fail.setMessage("Passwords do not match").setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                                                     public void onClick(DialogInterface dialog, int which) {
                                                         dialog.dismiss();
                                                     }
                                                 });
                                                 fail.show();
                                                 return;
                                             }


                                             new newUser().execute(u,p);
                                         }
                                     }
        );
    }



    public class newUser extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {

            Network net = Network.getInstance();
            net.Init();
            Socket sock = net.getSock();
            BufferedReader bir = net.getBir();
            PrintWriter pw = net.getPw();
            System.out.println(pw+""+bir+""+sock);
            String s = null;

            String username = params[0];
            String password = params[1];
            System.out.println(username+password+"Jeg er inde i metoden");

            pw.println("REGISTER\n"+username+"\n"+password);
            pw.flush();
            try {

                s = bir.readLine();
                System.out.println(s);

            }catch (IOException e){
                e.printStackTrace();
            }
            return s;
        }
        protected void onPostExecute(String s){

            if (s.equals("OK")) {
                System.out.println("jeg burder gå videre");
                Intent loginIntent = new Intent(register.this, startmenu.class);
                register.this.startActivity(loginIntent);
            } else {
                Toast.makeText(register.this,"User already exist",Toast.LENGTH_LONG).show();
            }
        }
    }




}
