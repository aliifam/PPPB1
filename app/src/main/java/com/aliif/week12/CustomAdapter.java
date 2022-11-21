package com.aliif.week12;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList id, nama, jk, prodi;

    public CustomAdapter(Context context, Activity activity, ArrayList id, ArrayList nama, ArrayList jk, ArrayList prodi) {
        this.context = context;
        this.activity = activity;
        this.id = id;
        this.nama = nama;
        this.jk = jk;
        this.prodi = prodi;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new CustomViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position) {
            holder.id.setText(String.valueOf(id.get(position)));
            holder.nama.setText(String.valueOf(nama.get(position)));
            holder.prodi.setText(String.valueOf(prodi.get(position)));
            holder.jk.setText(String.valueOf(jk.get(position)));

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdateActivity.class);
                    intent.putExtra("id", String.valueOf(id.get(position)));
                    activity.startActivityForResult(intent, 1);
                }
            });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView id, nama, prodi, jk;
        LinearLayout linearLayout;

        CustomViewHolder(@NonNull View view)
        {
            super(view);
            id = view.findViewById(R.id.id);
            nama = view.findViewById(R.id.nama);
            prodi = view.findViewById(R.id.prodi);
            jk = view.findViewById(R.id.jk);

            linearLayout = view.findViewById(R.id.mainLayout);

            Animation animation = AnimationUtils.loadAnimation(context, R.anim.translate);
            linearLayout.setAnimation(animation);
        }
    }
}
