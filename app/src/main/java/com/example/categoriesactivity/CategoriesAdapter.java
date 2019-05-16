package com.example.categoriesactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoriesAdapter extends ArrayAdapter<String> {

    // save the Arraylist as instance variable
    private ArrayList<String> categories;

    // a constructor
    public CategoriesAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        this.categories = objects;
    }

    // put the elements in the view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // convertView will be made when grid is shown for the first time
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_item, parent, false);
        }

        // otherwise, recycle convertView
        TextView text = convertView.findViewById(R.id.categoryText);

        // accessing the information we want to display
        String category = categories.get(position);

        // assigning the new data to the Views
        text.setText(category);

        return convertView;
    }
}
