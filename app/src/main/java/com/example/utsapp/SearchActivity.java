package com.example.utsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button btnDatePicker = findViewById(R.id.daterangepickerbut);
        Button searchbutton = findViewById(R.id.searchbutton);
        Spinner Jumlah = findViewById(R.id.spinner);

        final String[] tglmulai = new String[1];
        final String[] tglselesai = new String[1];

        // when floationg acition button is clicked
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initiation date picker with
                // MaterialDatePicker.Builder.datePicker()
                // and building it using build()
                MaterialDatePicker<Pair<Long, Long>> datePicker = MaterialDatePicker.Builder.dateRangePicker().build();
                datePicker.show(getSupportFragmentManager(), "DatePicker");

                // Setting up the event for when ok is clicked
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Pair<Long, Long> selection) {
                        Long startdate = selection.first;
                        Long enddate = selection.second;
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
                        tglmulai[0] = simpleDateFormat.format(startdate);
                        tglselesai[0] = simpleDateFormat.format(enddate);
                        btnDatePicker.setText(tglmulai[0] + " - " + tglselesai[0]);
                    }
                });
            }
        });
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tglmulai[0] == null || tglselesai[0] == null){
                    Toast.makeText(getApplicationContext(), "Isi dulu semua field baru cari Hotel", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                    intent.putExtra("checkin", tglmulai[0]);
                    intent.putExtra("checkout", tglselesai[0]);
                    intent.putExtra("jmlhkamar", Jumlah.getSelectedItem().toString());
                    startActivity(intent);
                }
            }
        });
    }

}