package com.aliif.crudfb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        TextView nama, matkul;
        CardView cardView;
        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            matkul = itemView.findViewById(R.id.matkul);
            cardView = itemView.findViewById(R.id.kartu);
        }
    }
}
