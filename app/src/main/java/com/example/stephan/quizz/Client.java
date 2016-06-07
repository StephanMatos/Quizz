package com.example.stephan.quizz;

import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Stephan on 03-06-2016.
 */
public class Client {

    private Socket sock;
    private BufferedReader bir;
    private PrintWriter pw;
    private String username,password;


    public Client(){



    }

    public void sock(String ip){

        try{
            sock = new Socket(ip,2048);
            bir = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            pw = new PrintWriter(sock.getOutputStream());

        } catch(IOException e){

            e.printStackTrace();
        }


    }

    public void login(String username,String password){

        this.username = username;
        this.password = password;

        pw.print("LOGIN");
        pw.flush();
        pw.print(username);
        pw.flush();
        pw.print(password);
        pw.flush();

    }

    public void newUser(String username,String password){


        this. username = username;
        this.password = password;

        pw.print("REGISTER");
        pw.flush();
        pw.print(username);
        pw.flush();
        pw.print(password);
        pw.flush();
    }


}
