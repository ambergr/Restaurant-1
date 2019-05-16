package com.example.categoriesactivity;

import android.app.Activity;
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

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    //instance variables
    private Context context;
    private Callback activity;

    // callback interface for the send requests (implemented in main/CategoriesActivity)
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // a constructor
    public CategoriesRequest(Context context) {
        this.context = context;
    }

    // method that sends the request, returns to the activity when response succeeded/failed
    public void getCategories(Callback activity) {

        this.activity = activity;

        // request is through a Volley, which allows for asynchronicity
        RequestQueue queue = Volley.newRequestQueue(context);

        // JsonObjectRequest bc the API returns the data as a JSON object
        String url = "https://resto.mprog.nl/categories";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }

    // two listener methods to handle JsonObjectRequest responses
    @Override
    public void onResponse(JSONObject response) {

        try {
            // method below accesses the object at top level, which here has "categories" as key
            JSONArray jsonArray = response.getJSONArray("categories");

            // create an arraylist to store the json values in
            int len = jsonArray.length();
            ArrayList<String> categories = new ArrayList<>(len);

            // read the values from the JSONArray into variable
            for (int i = 0; i < len; i++) {
                 categories.add(jsonArray.getString(i));
            }

            // use callback method to send the listArray to the activity
            activity.gotCategories(categories);

        } catch (JSONException e) {

            System.out.println("Something went wrong while retrieving the categories");
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        // send a description of the error to the activity callback method
        activity.gotCategoriesError(error.getMessage());

        // error also possible bc own wifi was turned off.
        // maybe implement an error message in the UI
        // "No internet connection. Retry button"
    }
}
