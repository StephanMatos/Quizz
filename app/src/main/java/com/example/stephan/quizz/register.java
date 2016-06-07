package com.example.stephan.quizz;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class register extends AppCompatActivity {

    private GoogleApiClient client;
    public Client client1;

    @Override
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
                client1.sock("62.44.134.26");
                if(etPassword.equals(atPassword)){

                    String u = etUsername.getText().toString();
                    String p = etPassword.getText().toString();
                    client1.newUser(u,p);
                }
            }
        });
    }


}
