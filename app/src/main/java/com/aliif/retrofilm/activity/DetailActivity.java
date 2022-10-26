package com.aliif.retrofilm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.aliif.retrofilm.R;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String title = getIntent().getStringExtra("title");
        String image = getIntent().getStringExtra("image");
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        Picasso.get()
                .load(image)
                .placeholder(R.drawable.img)
                .error(R.drawable.img)
                .into((ImageView) findViewById(R.id.imageView));
    }
}