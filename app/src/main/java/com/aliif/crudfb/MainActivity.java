package com.aliif.crudfb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;

    AdapterModel adapterModel;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    ArrayList<Model> models;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.add_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
        recyclerView = findViewById(R.id.list_item);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        
        viewData();
    }

    private void viewData() {
        databaseReference.child("Mahasiswa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                models = new ArrayList<>();
                for (DataSnapshot item: snapshot.getChildren())
                {
                    Model model = item.getValue(Model.class);
                    assert model != null;
                    model.setKey(item.getKey());
                    models.add(model);
                }
                adapterModel = new AdapterModel(models, MainActivity.this);
                recyclerView.setAdapter(adapterModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}