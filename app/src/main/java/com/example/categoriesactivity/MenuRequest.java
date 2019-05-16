package com.example.categoriesactivity;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    // instance variables
    private Context context;
    private Callback activity;

    // the callback interface (list of callback methods)
    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> menu);
        void gotMenuError(String message);
    }

    // a constructor
    public MenuRequest(Context context) {
        this.context = context;
    }

    // method that sends the API request
    public void getMenu(Callback activity, String category) {

        this.activity = activity;

        // request is through a Volley, which allows for asynchronicity
        RequestQueue queue = Volley.newRequestQueue(context);

        // JsonObjectRequest bc the API returns the data as a JSON object
        String url = "https://resto.mprog.nl/menu?category=" + category;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response) {
        // unpack the JSON and transform them into MenuItem objects
        try {
            // method below unpacks the top level array
            JSONArray jsonArray = response.getJSONArray("items");

            // create an arraylist to store the json values in
            int len = jsonArray.length();
            ArrayList<MenuItem> menu = new ArrayList<>(len);

            // read the values from the JSONArray into variable
            for (int i = 0; i < len; i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                MenuItem menuItem = new MenuItem(item.getString("name"),
                                                 item.getString("description"),
                                                 item.getString("image_url"),
                                                 item.getDouble("price"),
                                                 item.getString("category"));
                menu.add(menuItem);
            }

            // use callback method to send the listArray to the activity
            activity.gotMenuItems(menu);

        } catch (JSONException e) {

            System.out.println("Something went wrong while retrieving the menu");
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // send a description of the error to the activity callback method
        activity.gotMenuError(error.getMessage());
    }
}
