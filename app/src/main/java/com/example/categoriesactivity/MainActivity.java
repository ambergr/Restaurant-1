package com.example.categoriesactivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CategoriesRequest.Callback{

    // instance variables
    private ArrayList<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create an instance of the CategoriesRequest class
        CategoriesRequest x = new CategoriesRequest(this);

        // retrieve the categories
        x.getCategories(this);

        // set itemListener
        ListView listView = findViewById(R.id.listView);
        CategoryListener itemListener = new CategoryListener();
        listView.setOnItemClickListener(itemListener);

    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        this.categories = categories;

        // set the adapter
        ListView listView = findViewById(R.id.listView);
        CategoriesAdapter adapter = new CategoriesAdapter(this, R.layout.category_item, categories);
        listView.setAdapter(adapter);
    }

    // implement listener class for a click on the items
    private class CategoryListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // deduce the clicked category
            String clickedCategory = categories.get(position);

            // start a new intent
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            intent.putExtra("category", clickedCategory);
            startActivity(intent);
        }
    }

    @Override
    public void gotCategoriesError(String message) {

        // error most likely bc of no internet: give feedback in UI
        TextView textView = findViewById(R.id.textView);
        textView.setTextColor(Color.RED);
        textView.setText("No internet connection.");
    }
}
