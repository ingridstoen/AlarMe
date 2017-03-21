package com.example.aminaettayebi.connecttomysql;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {

    private static final String server = "sql11.freemysqlhosting.net";
    String database = "sql11164791";
    String username = "sql11164791";
    String password = "YenkZIu277";
    String connectionString = "jdbc:mysql://" + server + "/" + database + "?user=" + username + "&password=" + password;
    private TextView firstName, lastName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName = (TextView) findViewById(R.id.textviewFirstName);
        lastName = (TextView) findViewById(R.id.textviewLastName);
        Button buttonLoad = (Button) findViewById(R.id.buttonLoad);
        buttonLoad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new MyTask().execute();
                System.out.print("done");


            }
        });

    }

    private class MyTask extends AsyncTask<Void, Void, Void> {
        private String fName = "", lName = "";
        protected Void doInBackground(Void... arg0) {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(connectionString);
                Statement statement = con.createStatement();
                String re="insert into Student(username,user_password,name)" + "values('ami','12','hallo');";
                statement.executeUpdate(re);


                //fName = rs.getNString(2);
                //lName = rs.getString(3);



            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        protected void onPostExecute(Void result) {
            //lastName.setText(lName);
            //firstName.setText(fName);
            super.onPostExecute(result);


        }
    }
}







































