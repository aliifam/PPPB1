package com.aliif.sharpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText name;
    EditText email;
    public static final String prefku = "prefku";
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        sharedPreferences = getSharedPreferences(prefku, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(Name)) {
            name.setText(sharedPreferences.getString(Name, ""));
        }
        if (sharedPreferences.contains(Email)) {
            email.setText(sharedPreferences.getString(Email, ""));
        }
    }


    public void Save(View view) {
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        String n = name.getText().toString();
        String e = email.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Name, n);
        editor.putString(Email, e);
        editor.apply();
    }

    public void Retrieve(View view) {
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        sharedPreferences = getSharedPreferences(prefku, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(Name)) {
            name.setText(sharedPreferences.getString(Name, ""));
        }
        if (sharedPreferences.contains(Email)) {
            email.setText(sharedPreferences.getString(Email, ""));
        }
    }

    public void Clear(View view) {
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        name.setText("");
        email.setText("");
    }
}