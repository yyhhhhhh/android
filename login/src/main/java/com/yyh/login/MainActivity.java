package com.yyh.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.text_userName);
        password = findViewById(R.id.text_pwd);
        login = findViewById(R.id.login_btn);

        login.setOnClickListener(view ->{
            Toast.makeText(this,R.string.message,Toast.LENGTH_SHORT).show();
        });
    }
}