package com.example.aminaettayebi.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.sql.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import  java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class register extends Activity implements View.OnClickListener {

    Button bRegister;
    EditText etbrukernavn, etpassord, etnavn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etbrukernavn = (EditText) findViewById(R.id.etbrukernavn);
        etpassord = (EditText) findViewById(R.id.etpassord);
        etnavn = (EditText) findViewById(R.id.etnavn);
        bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {
        if (v.getId()== bRegister.getId())  {
            new Thread(new Runnable() {
                public void run() {
                    System.out.print("hallo");
                    insert();
                }


            }).start();
        } }

    protected void insert(){
        try {

            Class.forName("com.myql.jdbc.Driver");
            String server = "sql11.freemysqlhosting.net";
            String database = "sql11163131";
            String username = "sql11163131";
            String password = "wi4gXfVvT3";
            String connectionString = "jdbc:mysql://" + server + "/" + database + "?user=" + username + "&password=" + password;
            Connection connection=DriverManager.getConnection(connectionString);
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery("Select * from Student");
            System.out.print("hei");


        } catch (ClassNotFoundException |  SQLException e){
            e.printStackTrace();}
        }
    }






