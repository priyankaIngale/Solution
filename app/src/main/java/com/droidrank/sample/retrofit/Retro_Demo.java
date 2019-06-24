package com.droidrank.sample.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.droidrank.sample.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro_Demo extends AppCompatActivity {
    ArrayList<Heros> heros;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retro_demo);
        heros=new ArrayList<>();

        final ListView listview=(ListView)findViewById(R.id.listview);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api=retrofit.create(Api.class);

        final Call<List<Heros>> listCall=api.getHeros();

        listCall.enqueue(new Callback<List<Heros>>() {
            @Override
            public void onResponse(Call<List<Heros>> call, Response<List<Heros>> response) {
                List<Heros> listheros=response.body();

                String[] heros=new String[listheros.size()];
                for (int i=0;i<listheros.size();i++){
                  // Heros hero=new Heros();
                     heros[i]=(listheros.get(i).getName());
                }
                listview.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.activity_list_item,heros));
            }

            @Override
            public void onFailure(Call<List<Heros>> call, Throwable t) {

            }
        });

    }
}
