package com.example.stephan.quizz;

import android.os.AsyncTask;

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
    private String username,password,password2;


    public Client(){

    }

    public class Login extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... params) {
            String ip = "62.44.134.26";
            try{
                sock = new Socket(ip,2048);
                bir = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                pw = new PrintWriter(sock.getOutputStream());

            } catch(IOException e){

                e.printStackTrace();
            }

            params[0] = username;
            params[1] = password;
            try {
            pw.print("LOGIN");
            pw.flush();
            pw.print(username);
            pw.flush();
            pw.print(password);
            pw.flush();
                String s = bir.readLine();
                if(s.contains("OK")){

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    public class newUser extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... params) {
            String ip = "62.44.134.26";
            try{
                sock = new Socket(ip,2048);
                bir = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                pw = new PrintWriter(sock.getOutputStream());

            } catch(IOException e){

                e.printStackTrace();
            }

            params[0] = username;
            params[1] = password;
            try {
                pw.print("New User");
                pw.flush();
                pw.print(username);
                pw.flush();
                pw.print(password);
                pw.flush();


                String s = bir.readLine();
                if(s.contains("OK")){

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
