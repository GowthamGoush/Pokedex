package com.example.tester_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SampleAdapter.OnItemClickListener , AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerView;
    private SampleAdapter sampleAdapter;
    private ArrayList<SampleInput> sampleInput;
    private RequestQueue requestQueue;
    public static final String POKE_NAME = "com.example.tester_app_PokeNAME";
    public static final String POKE_NUM = "com.example.tester_app_PokeNUM";
    public static final String POKE_URL = "com.example.tester_app_PokeURL";
    int pokeNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        sampleInput = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);

        Spinner pokeSpinner = findViewById(R.id.pokeSpinner);
        pokeSpinner.setOnItemSelectedListener(this);

        Spinner pokeSpinner2 = findViewById(R.id.pokeSpinner2);
        pokeSpinner2.setOnItemSelectedListener(this);

    }

    public void callJson(){

        sampleInput.clear();

        String url = "https://pokeapi.co/api/v2/pokemon?limit=964&offset=0";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            final JSONArray jsonArray = response.getJSONArray("results");
                            for(int i=0;i<jsonArray.length();i++) {
                                JSONObject results = jsonArray.getJSONObject(i);

                                final String pokeName = results.getString("name");
                                final String pokeUrl = results.getString("url");
                                final int num = i+1;
                                final String imageLink = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+num+".png";

                                sampleInput.add(new SampleInput(pokeName,pokeUrl,num,imageLink));
                                sampleAdapter = new SampleAdapter(MainActivity.this,sampleInput);
                                recyclerView.setAdapter(sampleAdapter);
                                sampleAdapter.setOnItemClickListener(MainActivity.this);
                            }

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
        requestQueue.add(jsonObjectRequest);
    }

    public void callJson_type(){

        sampleInput.clear();

        String url = "https://pokeapi.co/api/v2/type/"+pokeNum;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            final JSONArray jsonArray = response.getJSONArray("pokemon");
                            for(int i=0;i<jsonArray.length();i++) {
                                JSONObject poke = jsonArray.getJSONObject(i);
                                JSONObject poke2 = poke.getJSONObject("pokemon");

                                final String pokeName = poke2.getString("name");
                                final String pokeUrl = poke2.getString("url");

                                final String[] url = pokeUrl.split("/");
                                final int num = Integer.parseInt(url[url.length-1]);

                                final String imageLink = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + num + ".png";

                                sampleInput.add(new SampleInput(pokeName, pokeUrl, num, imageLink));
                                sampleAdapter = new SampleAdapter(MainActivity.this, sampleInput);
                                recyclerView.setAdapter(sampleAdapter);
                                sampleAdapter.setOnItemClickListener(MainActivity.this);
                            }

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
        requestQueue.add(jsonObjectRequest);
    }

    public void callJson_region(){

        String url = "https://pokeapi.co/api/v2/pokedex/"+pokeNum+"/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            final JSONArray jsonArray = response.getJSONArray("pokemon_entries");
                            for(int i=0;i<jsonArray.length();i++) {
                                JSONObject poke = jsonArray.getJSONObject(i);
                                JSONObject poke2 = poke.getJSONObject("pokemon_species");

                                final String pokeName = poke2.getString("name");
                                final String pokeUrl = poke2.getString("url");

                                final String[] url = pokeUrl.split("/");
                                final int num = Integer.parseInt(url[url.length-1]);

                                final String imageLink = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + num + ".png";

                                sampleInput.add(new SampleInput(pokeName, pokeUrl, num, imageLink));
                                sampleAdapter = new SampleAdapter(MainActivity.this, sampleInput);
                                recyclerView.setAdapter(sampleAdapter);
                                sampleAdapter.setOnItemClickListener(MainActivity.this);
                            }

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
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent1 = new Intent(this,MainActivity2.class);
        SampleInput clickedItem = sampleInput.get(position);
        intent1.putExtra(POKE_NAME,clickedItem.getText1());
        intent1.putExtra(POKE_NUM,clickedItem.getPokeId());
        intent1.putExtra(POKE_URL,clickedItem.getPokeUrl());
        startActivity(intent1);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0 && parent.getId() == R.id.pokeSpinner){
            callJson();
        }
        else if(position == 1 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 1;
            callJson_type();
        }
        else if(position == 2 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 2;
            callJson_type();
        }
        else if(position == 3 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 3;
            callJson_type();
        }
        else if(position == 4 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 4;
            callJson_type();
        }
        else if(position == 5 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 5;
            callJson_type();
        }
        else if(position == 6 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 6;
            callJson_type();
        }
        else if(position == 7 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 7;
            callJson_type();
        }
        else if(position == 8 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 8;
            callJson_type();
        }
        else if(position == 9 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 9;
            callJson_type();
        }
        else if(position == 10 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 10;
            callJson_type();
        }
        else if(position == 11 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 11;
            callJson_type();
        }
        else if(position == 12 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 12;
            callJson_type();
        }
        else if(position == 13 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 13;
            callJson_type();
        }
        else if(position == 14 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 14;
            callJson_type();
        }
        else if(position == 15 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 15;
            callJson_type();
        }
        else if(position == 16 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 16;
            callJson_type();
        }
        else if(position == 17 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 17;
            callJson_type();
        }
        else if(position == 18 && parent.getId() == R.id.pokeSpinner){
            pokeNum = 18;
            callJson_type();
        }
        else if(position == 0 && parent.getId() == R.id.pokeSpinner2){
            callJson();
        }
        else if(position == 1 && parent.getId() == R.id.pokeSpinner2){
            sampleInput.clear();
            pokeNum = 2;
            callJson_region();
            sampleInput.stream().distinct().collect(Collectors.toList());
        }
        else if(position == 2 && parent.getId() == R.id.pokeSpinner2){
            sampleInput.clear();
            pokeNum = 3;
            callJson_region();
            pokeNum = 7;
            callJson_region();
            sampleInput.stream().distinct().collect(Collectors.toList());
        }
        else if(position == 3 && parent.getId() == R.id.pokeSpinner2){
            sampleInput.clear();
            pokeNum = 4;
            callJson_region();
            pokeNum = 15;
            callJson_region();
            sampleInput.stream().distinct().collect(Collectors.toList());
        }
        else if(position == 4 && parent.getId() == R.id.pokeSpinner2){
            sampleInput.clear();
            pokeNum = 5;
            callJson_region();
            pokeNum = 6;
            callJson_region();
            sampleInput.stream().distinct().collect(Collectors.toList());
        }
        else if(position == 5 && parent.getId() == R.id.pokeSpinner2){
            sampleInput.clear();
            pokeNum = 8;
            callJson_region();
            pokeNum = 9;
            callJson_region();
            sampleInput.stream().distinct().collect(Collectors.toList());
        }
        else if(position == 6 && parent.getId() == R.id.pokeSpinner2){
            sampleInput.clear();
            pokeNum = 12;
            callJson_region();
            pokeNum = 13;
            callJson_region();
            pokeNum = 14;
            callJson_region();
            sampleInput.stream().distinct().collect(Collectors.toList());
        }
        else if(position == 7 && parent.getId() == R.id.pokeSpinner2){
            sampleInput.clear();
            pokeNum = 16;
            callJson_region();
            pokeNum = 17;
            callJson_region();
            pokeNum = 18;
            callJson_region();
            pokeNum = 19;
            callJson_region();
            pokeNum = 20;
            callJson_region();
            pokeNum = 21;
            callJson_region();
            pokeNum = 22;
            callJson_region();
            pokeNum = 23;
            callJson_region();
            pokeNum = 24;
            callJson_region();
            pokeNum = 25;
            callJson_region();
            sampleInput.stream().distinct().collect(Collectors.toList());
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
