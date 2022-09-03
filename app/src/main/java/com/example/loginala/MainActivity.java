package com.example.loginala;

import androidx.appcompat.app.AppCompatActivity;

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
        setContentView(R.layout.login);

        Button loginbtn = findViewById(R.id.loginbtn);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();
                if (str_email.trim().isEmpty() || str_password.trim().isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Email dan Password harus diisi", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    Intent intent = new Intent(getApplicationContext(), profile.class);
                    intent.putExtra("email", str_email);
                    intent.putExtra("password", str_password);
                    startActivity(intent);
                }
            }
        });
    }
}