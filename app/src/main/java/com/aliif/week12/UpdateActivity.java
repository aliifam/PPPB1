package com.aliif.week12;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    String id, judul;

    EditText nama, jk, prodi, alamat;
    Button update, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nama = findViewById(R.id.nama_input);
        jk = findViewById(R.id.jk_input);
        prodi = findViewById(R.id.prodi_input);
        alamat = findViewById(R.id.alamat_input);

        update = findViewById(R.id.update_button);
        delete = findViewById(R.id.delete_button);

        databaseHelper = new DatabaseHelper(UpdateActivity.this);

        id = getIntent().getStringExtra("id");
        Log.d("ROW ID = ", id);
        fetchData(id);


        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(judul);
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(UpdateActivity.this);
                databaseHelper.updateData(id,
                        nama.getText().toString().trim(),
                        jk.getText().toString().trim(),
                        prodi.getText().toString().trim(),
                        alamat.getText().toString().trim());
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelete();
            }
        });
    }

    public void fetchData(String row_id)
    {
        Cursor cursor = databaseHelper.readData(row_id);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        judul = cursor.getString(1);
        nama.setText(cursor.getString(1));
        jk.setText(cursor.getString(2));
        prodi.setText(cursor.getString(3));
        alamat.setText(cursor.getString(4));
    }

    public void confirmDelete()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + judul + " ?");
        builder.setMessage("Are you sure you want to delete " + judul + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper databaseHelper = new DatabaseHelper(UpdateActivity.this);
                databaseHelper.deleteData(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}