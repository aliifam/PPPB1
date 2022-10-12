package com.example.utsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String nama_hotel = intent.getStringExtra("hotelname");
        String harga_hotel = intent.getStringExtra("hotelprice");
        String deskripsi_hotel = intent.getStringExtra("hoteldescription");
        String gambar_hotel = intent.getStringExtra("hotelimage");
        String fasilitas_hotel = intent.getStringExtra("hotelfasilitas");

        String fasafix = "";

        try {
            JSONArray fasilitasarray = new JSONArray(fasilitas_hotel);


            for (int i = 0; i < fasilitasarray.length(); i++) {
                fasafix = fasafix + "- " + fasilitasarray.getString(i) + "\n";
            }
        } catch (JSONException e) {
            e.printStackTrace();

        }

        TextView hotel_title = findViewById(R.id.nama_hotel);
        Button hotel_price = findViewById(R.id.harga_hotel);
        TextView hotel_description = findViewById(R.id.deskripsi_hotel);
        TextView hotel_facilities = findViewById(R.id.fasilitas_hotel);
        ImageView hotel_image = findViewById(R.id.gambar_hotel);

        hotel_title.setText(nama_hotel);
        hotel_price.setText(harga_hotel);
        hotel_description.setText(deskripsi_hotel);
        hotel_facilities.setText(fasafix);

        Context context = hotel_image.getContext();
        int id = context.getResources().getIdentifier(gambar_hotel, "drawable", context.getPackageName());
        hotel_image.setImageResource(id);

        hotel_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder orderAlert = new AlertDialog.Builder(DetailActivity.this);

                orderAlert.setTitle("Order Detail");
                String orderdetail = "Hotel dipesan: " + nama_hotel + "\n"
                        + "Harga Permalam: " + harga_hotel + "\n"
                        + "Tanggal Checkin: " + intent.getStringExtra("tanggal_mulai") + "\n"
                        + "Taggal Checkout: " + intent.getStringExtra("tanggal_selesai")+ "\n"
                        + "Jumlah Kamar dipesan: " + intent.getStringExtra("jumlah_kamar");
                orderAlert.setMessage(orderdetail);

                orderAlert.setPositiveButton("Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),"Order Berhasil silahkan cek inbox Email anda", Toast.LENGTH_LONG).show();
                    }
                });

                orderAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Tidak Jadi Order", Toast.LENGTH_SHORT).show();
                    }
                });

                orderAlert.show();

            }
        });

    }
}