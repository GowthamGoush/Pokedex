package com.example.tester_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView recyclerView2;
    private String pokeName,pokeUrl,imgUrl;
    private RequestQueue requestQueue2;
    private ArrayList<pokeDetail> pokeArray;
    private SampleAdapter2 sampleAdapter2;
    private int num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView2 = findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        pokeArray = new ArrayList<>();

        requestQueue2 = Volley.newRequestQueue(this);

        Intent intent2 = getIntent();

        pokeName = intent2.getStringExtra(MainActivity.POKE_NAME);

        num2 = intent2.getIntExtra(MainActivity.POKE_NUM,0);

        imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+num2+".png";

        callJson2();

    }

    public void callJson2(){

        pokeArray.clear();
        String url = "https://pokeapi.co/api/v2/pokemon/"+num2;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String h = response.getString("height");
                    String w = response.getString("weight");
                    String e = response.getString("base_experience");

                    JSONArray jsonArray = response.getJSONArray("types");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    JSONObject type2 = jsonObject.getJSONObject("type");

                    String t = type2.getString("name");

                    pokeArray.add(new pokeDetail(pokeName,h,w,t,e,imgUrl));
                    sampleAdapter2 = new SampleAdapter2(MainActivity2.this,pokeArray);
                    recyclerView2.setAdapter(sampleAdapter2);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue2.add(request);

    }


}