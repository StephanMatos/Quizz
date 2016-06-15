package com.example.stephan.quizz;

import android.app.Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Stephan on 09-06-2016.
 */
public class Network extends Application {

    private static Network instance = null;
    private static Socket sock;
    private static  BufferedReader bir;
    private static PrintWriter pw;
    private String username,password;


    public Network(){

        Socket sock = null;
        BufferedReader bir = null;
        PrintWriter pw = null;
        String username = null;
        String password = null;
    }

    public static Network getInstance() {
        if (instance == null) {
            instance = new Network();
        }
        return(instance);
    }

    public void Init (){

        String ip = "10.16.167.159";

        try {
            sock = new Socket(ip, 2048);
            System.out.println(sock);
            bir = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            pw = new PrintWriter(sock.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Jeg er i netværk "+" Socket ="+sock+" Bufferedreader="+bir+" Printwriter="+pw);

    }

    public Socket getSock(){
        return sock;
    }
    public BufferedReader getBir(){
        return bir;
    }
    public PrintWriter getPw(){
        return pw;
    }
    public String getUsername(){
        return  username;
    }
    public String getPassword(String password){
        return password;
    }
    public void setUsername(String user){
        this.username = user;
    }




}
