package com.aliif.week8;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.TextView;

import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {

    @SuppressLint("StaticFieldLeak")
    private TextView mTextView;

    public SimpleAsyncTask(TextView tv)
    {
        mTextView = tv;
    }

    @Override
    protected String doInBackground(Void... voids) {

        Random r = new Random();
        int n = r.nextInt(11);

        int s = n * 200;

        try {
            Thread.sleep(s);
        }catch (InterruptedException e){
            e.printStackTrace();
        }


        return "Akhirnya bagun setelah tidur selama" + s + "milidetik!";
    }

    protected void onPostExecute(String result)
    {
        mTextView.setText(result);
    }
}
