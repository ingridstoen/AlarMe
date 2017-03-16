package com.example.aminaettayebi.loginregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity implements View.OnClickListener {

    //type edittext
    Button bLogin;
    EditText etbrukernavn,etpassord;
    TextView tvRegisterLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etbrukernavn= (EditText) findViewById(R.id.etbrukernavn);
        etpassord= (EditText) findViewById(R.id.etpassord);
        bLogin= (Button) findViewById(R.id.bLogin);
        tvRegisterLink= (TextView) findViewById(R.id.tvRegisterLink);
        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        // get id
        switch(v.getId()){
            // this will happen

            case R.id.bLogin:

                break;

            case R.id.tvRegisterLink:
                startActivity( new Intent(this, register.class));
                break;

    }  }

}
