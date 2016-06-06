package com.example.stephan.quizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class startmenu extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startmenu);

        final ImageButton blogout = (ImageButton) findViewById(R.id.blogout);
        final ImageButton bcurrent_game = (ImageButton) findViewById(R.id.bcurrent_game);
        final ImageButton bstart_newgame = (ImageButton) findViewById(R.id.bstart_newgame);

        blogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(startmenu.this, LoginActivity.class);
                startmenu.this.startActivity(logoutIntent);
            }
        });
    }
}
