package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    public void gokeyboard(View view){
        Intent intent = new Intent(ListActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void gospinner(View view){
        Intent intent = new Intent(ListActivity.this, SpinnerActivity.class);
        startActivity(intent);
    }

    public void goalert(View view){
        Intent intent = new Intent(ListActivity.this, AlertActivity.class);
        startActivity(intent);
    }

    public void gopicker(View view){
        Intent intent = new Intent(ListActivity.this, PickerActivity.class);
        startActivity(intent);
    }
}