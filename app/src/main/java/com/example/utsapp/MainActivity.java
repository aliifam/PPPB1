package com.example.utsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginbtn = findViewById(R.id.loginbutton);
        EditText email = findViewById(R.id.loginemail);
        EditText password = findViewById(R.id.loginpassword);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_email = email.getText().toString();
                String str_pass = password.getText().toString();

                if (!str_email.equals("mike@email.com") || !str_pass.equals("abc123")){
                    AlertDialog.Builder myalertbuilder = new AlertDialog.Builder(MainActivity.this);
                    myalertbuilder.setTitle("Login Gagal");
                    myalertbuilder.setMessage("Email atau Password salah");
                    myalertbuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            email.setText("");
                            password.setText("");
                        }
                    });
                    myalertbuilder.show();
                }else{
                    Toast.makeText(getApplicationContext(), "login berhasil", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}