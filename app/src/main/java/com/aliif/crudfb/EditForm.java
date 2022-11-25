package com.aliif.crudfb;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditForm extends DialogFragment {

    String nama, matkul, key, choose;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public EditForm(String nama, String matkul, String key, String choose)
    {
        this.nama = nama;
        this.matkul = matkul;
        this.key = key;
        this.choose = choose;
    }

    TextView tnama, tmatkul;
    Button edit_button;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_add, container, false);
        tnama = view.findViewById(R.id.input_nama);
        tmatkul = view.findViewById(R.id.input_matkul);
        edit_button = view.findViewById(R.id.add_data);

        tnama.setText(nama);
        tmatkul.setText(matkul);
        edit_button.setText("Edit Data");
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = tnama.getText().toString();
                String matkul = tmatkul.getText().toString();
                if (choose.equals("Edit"))
                {
                    databaseReference.child("Mahasiswa").child(key).setValue(new Model(nama, matkul)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(view.getContext(), "Data Successfully Edited", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(), "Data Failed Edited", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
}
