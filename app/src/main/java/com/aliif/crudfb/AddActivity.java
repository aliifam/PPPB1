package com.aliif.crudfb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    EditText nama, matkul;
    Button tambah;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nama = findViewById(R.id.input_nama);
        matkul = findViewById(R.id.input_matkul);
        tambah = findViewById(R.id.add_data);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strNama = nama.getText().toString();
                String strMatkul = matkul.getText().toString();

                if (strNama.isEmpty()) {
                    nama.setError("Masukkan Nama");
                }else if (strMatkul.isEmpty()){
                    matkul.setError("Masukkan Mata kuliah");
                }else {
                    databaseReference.child("Mahasiswa").push().setValue(new Model(strNama, strMatkul)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Data Gagal ditambahkan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}