package com.example.stephan.quizz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

/**
 * Created by Stephan on 03-06-2016.
 */
public class Client {

    private Socket sock;
    private BufferedReader bir;
    private PrintWriter pw;
    private String username,password;
    public boolean loggedIn;


    public Client(){

    }

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

            }
        }

        protected  void onCancelled(String s) {

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
                pw.println("New User\n"+username+"\n"+password);
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
