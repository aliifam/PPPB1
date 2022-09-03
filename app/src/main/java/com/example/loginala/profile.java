package com.example.loginala;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class profile extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView user_email = findViewById(R.id.user_email);
        TextView user_password = findViewById(R.id.user_password);
        Button back_button = findViewById(R.id.backbtn);

        Intent intent = getIntent();
        String str_user_email = intent.getStringExtra("email");
        String str_user_password = intent.getStringExtra("password");

        user_email.setText("Your Email = " + '"' + str_user_email + '"');
        user_password.setText("Your Password = " + '"' + str_user_password + '"');

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}