package com.droidrank.sample.volley;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.droidrank.sample.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Volley_Demo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> strings=new ArrayList<>();
        strings.add("abc");

    }

    void text() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://njjj.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonElements=obj.getJSONArray("");
                    for (int i=0;i<jsonElements.length();i++){
                        JSONObject jsonObject1=  jsonElements.getJSONObject(i);

//                        Hero hero=new Hero(jsonObject1.getString("name"));
//                        jsolist.add(hero);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
