package com.example.categoriesactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter {

    // save the Arraylist as instance variable
    private ArrayList<MenuItem> menu;

    // a constructor
    public MenuAdapter(Context context, int resource, ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        this.menu = objects;
    }

    // put the elements in the view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // convertView will be made when grid is shown for the first time
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_list_item, parent, false);
        }

        // otherwise, recycle convertView
        TextView listName = convertView.findViewById(R.id.listName);
        TextView listPrice = convertView.findViewById(R.id.listPrice);
        ImageView listImage = convertView.findViewById(R.id.listImage);

        // accessing the information we want to display
        MenuItem item = menu.get(position);

        // assigning the new data to the Views
        listName.setText(item.getName());
        listPrice.setText("€" + String.valueOf(item.getPrice()));
        Picasso.with(this.getContext())
                .load(item.getImageUrl())
                .centerCrop()
                .fit()
                .into(listImage);

        return convertView;
    }
}
