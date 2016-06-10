package com.example.stephan.quizz;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class invitescreen extends AppCompatActivity {

    public BufferedReader bir;
    public PrintWriter pw;
    public Socket sock;
    private String user;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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
                                        System.out.println(b + u);
                                        new Invite().execute(b,u);
                                    }

                                }
        );

        Java.setOnClickListener(new View.OnClickListener() {

                                    public void onClick(View v) {
                                        String u = etUsername.getText().toString();
                                        String b = Java.getText().toString();
                                        System.out.println(b + u);
                                        new Invite().execute(b,u);
                                    }

                                }
        );

        math1.setOnClickListener(new View.OnClickListener() {

                                     public void onClick(View v) {
                                         String u = etUsername.getText().toString();
                                         String b = math1.getText().toString();
                                         System.out.println(b + u);
                                         new Invite().execute(b,u);
                                     }

                                 }
        );

        digital.setOnClickListener(new View.OnClickListener() {

                                       public void onClick(View v) {
                                           String u = etUsername.getText().toString();
                                           String b = digital.getText().toString();
                                           System.out.println(b + u);
                                           new Invite().execute(b,u);
                                       }

                                   }
        );


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }




    public class Invite extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {

            Network net = Network.getInstance();
            Socket sock = net.getSock();
            BufferedReader bir = net.getBir();
            PrintWriter pw = net.getPw();
            String user = net.getUsername();
            String theme = params[0];
            String username = params[1];

            System.out.println(theme+username+user);
            pw.println("INVITE\n" + theme + "\n" + username + "\n" + user);
            pw.flush();
            String s = null;
            try {
                s = bir.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }

        protected void onPostExecute(String s){

            if (s.equals("OK")) {
                Intent gameIntent = new Intent(invitescreen.this, gameboard.class);
                invitescreen.this.startActivity(gameIntent);
            } else {
                Toast.makeText(invitescreen.this, "User not found, try again", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "invitescreen Page", // TODO: Define a title for the content shown.
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
                "invitescreen Page", // TODO: Define a title for the content shown.
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
}
