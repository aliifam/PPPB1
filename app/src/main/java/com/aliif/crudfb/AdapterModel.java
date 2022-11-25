package com.aliif.crudfb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterModel extends RecyclerView.Adapter<AdapterModel.ModelViewHolder> {

    private List<Model> modelList;
    private Activity activity;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public AdapterModel(List<Model> modelList, Activity activity) {
        this.modelList = modelList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdapterModel.ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item, parent, false);

        return new ModelViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterModel.ModelViewHolder holder, int position) {
        final Model model = modelList.get(position);
        holder.nama.setText("Nama: "+ model.getNama());
        holder.matkul.setText("Matkul: " + model.getMatkul());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        databaseReference.child("Mahasiswa").child(model.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(activity, "Data Successfully deleted", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity, "Delete data Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setMessage("Yakin mau dihapus? " + model.getNama());
                builder.show();
            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                FragmentManager fragmentManager = ((AppCompatActivity)activity).getSupportFragmentManager();
                EditForm editForm = new EditForm(
                        model.getNama(),
                        model.getMatkul(),
                        model.getKey(),
                        "Edit"
                );
                editForm.show(fragmentManager, "Form");
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        TextView nama, matkul;
        CardView cardView;
        ImageView imageView;
        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            matkul = itemView.findViewById(R.id.matkul);
            cardView = itemView.findViewById(R.id.kartu);
            imageView = itemView.findViewById(R.id.delete);
        }
    }
}
