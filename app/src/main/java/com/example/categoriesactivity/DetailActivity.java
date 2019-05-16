package com.example.categoriesactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // receive intent
        Intent intent = getIntent();
        MenuItem sendItem = (MenuItem) intent.getSerializableExtra("dish");

        // find the Views
        TextView nameView = findViewById(R.id.detailName);
        TextView descriptionView = findViewById(R.id.detailDescriptiom);
        TextView priceView = findViewById(R.id.detailPrice);
        ImageView imageView = findViewById(R.id.detailImage);

        // place the details in views
        nameView.setText(sendItem.getName());
        descriptionView.setText(sendItem.getDescription());
        priceView.setText("€" + String.valueOf(sendItem.getPrice()));
        Picasso.with(this)
                .load(sendItem.getImageUrl())
                .fit()
                .centerCrop()
                .into(imageView);

    }
}
