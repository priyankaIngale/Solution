package com.droidrank.sample.volley;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.droidrank.sample.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Volley_Demo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        ArrayList<String> strings=new ArrayList<>();
        strings.add("abc");
        text_new();

    }

    void text() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://testtimetable.intellischools.org/Timetable/public/api/test", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray jsonElements=obj.getJSONArray("");

                    JSONObject obj2 = new JSONObject();
                    obj2.put("username","priyanka");
                    obj2.put("password","priyanka");
                    Log.d("TAG","Volley request order : "+obj2.toString());

//                    for (int i=0;i<jsonElements.length();i++){
////                        JSONObject jsonObject1=  jsonElements.getJSONObject(i);
////
//////                        Hero hero=new Hero(jsonObject1.getString("name"));
//////                        jsolist.add(hero);
////                    }


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

    void text_new(){

        /*  API CODE
        * Controller function
            public function demo(Request $request){
                return ['success'=>true];
            }
            Route::post('test','CommonController@demo');
        * */

        String URL = "http://testtimetable.intellischools.org/Timetable/public/api/test";

        RequestQueue queue = Volley.newRequestQueue(this);

        //Create json array for filter
        JSONArray array = new JSONArray();

        //Create json objects for two filter Ids
        JSONObject jsonParam = new JSONObject();
        JSONObject jsonParam1 = new JSONObject();

        try {
            //Add string params
            jsonParam.put("username", "priyanka");
            jsonParam.put("password", "priyanka");
//            jsonParam.put("PASSWORD", "XXXXXXXXXXXX");
//            jsonParam1.put("NAME", "XXXXXXXXXXXXXX");
//            jsonParam1.put("USERNAME", "XXXXXXXXXXXXXX");
//            jsonParam1.put("PASSWORD", "XXXXXXXXXXXX");
            Log.e("TAG","jsonParam : "+jsonParam.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        array.put(jsonParam);
        array.put(jsonParam1);
        JsonArrayRequest request_json = new JsonArrayRequest(Request.Method.POST, URL, array,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Get Final response
                        Log.e("TAG","Final response : "+response);

                        try{
                            // Loop through the array elements
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject student = response.getJSONObject(i);

                                // Get the current student (json object) data
                                String firstName = student.getString("success");
//                                String lastName = student.getString("lastname");
//                                String age = student.getString("age");

                                // Display the formatted json data in text view
//                                mTextView.append(firstName +" " + lastName +"\nAge : " + age);
//                                mTextView.append("\n\n");
                                Log.e("TAG","firstname response : "+firstName);

                                if (firstName.equals("true")){
                                    Log.e("TAG","true response ");
                                }else{
                                    Log.e("TAG","false response ");
                                }
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }




                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.e("Error: ", volleyError.getMessage());

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                // Add headers
                return headers;
            }
            //Important part to convert response to JSON Array Again
            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                String responseString;
                JSONArray array = new JSONArray();
                if (response != null) {

                    try {
                        responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                        JSONObject obj = new JSONObject(responseString);
                        (array).put(obj);
                    } catch (Exception ex) {
                    }
                }
                //return array;
                return Response.success(array, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        queue.add(request_json);

    }
}
