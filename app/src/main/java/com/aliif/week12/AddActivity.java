package com.aliif.week12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText nama, jk, prodi, alamat;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nama = findViewById(R.id.nama_input);
        jk = findViewById(R.id.jk_input);
        prodi = findViewById(R.id.prodi_input);
        alamat = findViewById(R.id.alamat_input);
        add = findViewById(R.id.add_button);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(AddActivity.this);
                databaseHelper.addData(nama.getText().toString().trim(),
                        jk.getText().toString().trim(),
                        prodi.getText().toString().trim(),
                        alamat.getText().toString().trim());
            }
        });
    }
}