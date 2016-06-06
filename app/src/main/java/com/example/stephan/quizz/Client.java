package com.example.stephan.quizz;

import android.widget.EditText;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Stephan on 03-06-2016.
 */
public class Client {

    private Socket sock;
    private BufferedReader bir;
    private PrintWriter pw;
    private String username,password,a;


    public Client(){


    }



    public void login(String username,String password){

        this.username = username;
        this.password = password;

        pw.print(username);
        pw.flush();
        pw.print(password);
        pw.flush();

    }

    public void newUser(String username,String password){


        this. username = username;
        this.password = password;

        pw.print(username);
        pw.flush();
        pw.print(password);
        pw.flush();
    }


}
