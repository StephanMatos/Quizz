package com.example.stephan.quizz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class register extends AppCompatActivity {

    private EditText etUsername,etPassword, atPassword;
    private Button bregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        atPassword = (EditText) findViewById(R.id.atPassword);
        bregister = (Button) findViewById(R.id.bregister);

    }


}
