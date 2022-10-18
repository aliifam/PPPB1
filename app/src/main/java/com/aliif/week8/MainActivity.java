package com.aliif.week8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TEXT_STATE = "currentText";

    private TextView mTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextview = findViewById(R.id.textView1);

        if (savedInstanceState!=null){
            mTextview.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    public void startTask(View view)
    {
        mTextview.setText(R.string.napping);

        new SimpleAsyncTask(mTextview).execute();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE, mTextview.getText().toString());
    }
}