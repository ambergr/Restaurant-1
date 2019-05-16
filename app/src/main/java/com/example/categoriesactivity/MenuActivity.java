package com.example.categoriesactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback{
    ArrayList<MenuItem> menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // receive the intent and the clicked category
        Intent intent = getIntent();
        String clickedCategory = intent.getStringExtra("category");

        // change the title of the menu
        TextView menuTitle = findViewById(R.id.menuTitle);
        menuTitle.setText(clickedCategory + " menu");

        // instantiate a MenuRequest object
        MenuRequest x = new MenuRequest(this);

        // send a request for the clicked category menu
        x.getMenu(this, clickedCategory);

        // set item listener
        ListView listMenu = findViewById(R.id.listMenu);
        listMenu.setOnItemClickListener(new ItemListener());
    }

    // what to do with the received menu
    @Override
    public void gotMenuItems(ArrayList<MenuItem> menu) {
        this.menu = menu;

        // set the adapter
        ListView listMenu = findViewById(R.id.listMenu);
        ArrayAdapter adapter = new MenuAdapter(this, R.layout.menu_list_item, menu);
        listMenu.setAdapter(adapter);
    }

    @Override
    public void gotMenuError(String message) {

    }

    private class ItemListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // deduce the clicked category
            MenuItem clickedItem = menu.get(position);

            // start a new intent
            Intent intent = new Intent(MenuActivity.this, DetailActivity.class);
            intent.putExtra("dish", clickedItem);
            startActivity(intent);
        }
    }
}
