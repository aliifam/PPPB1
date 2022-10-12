package com.example.utsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class ListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Object> viewItems = new ArrayList<>();

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final String TAG = "ListActivity";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        String checkin = intent.getStringExtra("checkin");
        String checkout = intent.getStringExtra("checkout");
        String jmlhkamar = intent.getStringExtra("jmlhkamar");

        TextView checkindate = findViewById(R.id.checkindate);
        TextView checkoutdate = findViewById(R.id.checkoutdate);
        TextView jumlahkamar = findViewById(R.id.jumlahkamar);

        checkindate.setText("Checkin Date = " +  checkin);
        checkoutdate.setText("Checkout Date = " + checkout);
        jumlahkamar.setText("Jumlah Kamar = " + jmlhkamar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new HotelAdapter(this, viewItems, checkin, checkout, jmlhkamar);
        mRecyclerView.setAdapter(mAdapter);

        addItemsFromJSON();

    }

    private void addItemsFromJSON(){
        try {

            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i=0; i<jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                String name = itemObj.getString("name");
                String price = itemObj.getString("price");
                String image = itemObj.getString("image");
                String desc = itemObj.getString("description");
                JSONArray fasilitas = itemObj.getJSONArray("fasilitas");

                Hotels hotels = new Hotels(name, price, image, desc, fasilitas);
                viewItems.add(hotels);
            }

        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    private String readJSONDataFromFile() throws IOException{

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {

            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.hotels);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }
}