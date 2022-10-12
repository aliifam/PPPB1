package com.example.utsapp;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;
    private final String tgl_mulai;
    private final String tgl_selesai;
    private final String jumlah_dipesan;

    public HotelAdapter(Context context, List<Object> listRecyclerItem, String tgl_mulai, String tgl_selesai, String jumlah_dipesan) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
        this.tgl_mulai = tgl_mulai;
        this.tgl_selesai = tgl_selesai;
        this.jumlah_dipesan = jumlah_dipesan;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView price;
        private ImageView image;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            image = (ImageView) itemView.findViewById(R.id.hotelimg);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.hotel_item, viewGroup, false);

                return new ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);
        Hotels hotels = (Hotels) listRecyclerItem.get(i);

        switch (viewType) {
            case TYPE:
            default:

                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;


                itemViewHolder.name.setText(hotels.getName());
                itemViewHolder.price.setText(hotels.getPrice());
                Context context = itemViewHolder.image.getContext();
                int id = context.getResources().getIdentifier(hotels.getImage(), "drawable", context.getPackageName());
                itemViewHolder.image.setImageResource(id);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("tanggal_mulai", tgl_mulai);
                intent.putExtra("tanggal_selesai", tgl_selesai);
                intent.putExtra("jumlah_kamar", jumlah_dipesan);
                intent.putExtra("hotelname", hotels.getName());
                intent.putExtra("hotelprice", hotels.getPrice());
                intent.putExtra("hotelimage", hotels.getImage());
                intent.putExtra("hoteldescription", hotels.getDescription());
                intent.putExtra("hotelfasilitas", hotels.getFasilitas().toString());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}
