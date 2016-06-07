package com.example.stephan.quizz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends AppCompatActivity {

    private GoogleApiClient client;
    public Client client1;

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

                client1.sock("62.44.134.26");
                String u = etUsername.getText().toString();
                String p = etPassword.getText().toString();
                client1.login(u,p);

                Intent loginIntent = new Intent(LoginActivity.this, startmenu.class);
                LoginActivity.this.startActivity(loginIntent);
            }
        });
    }
}
