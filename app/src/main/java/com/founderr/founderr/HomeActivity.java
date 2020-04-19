package com.founderr.founderr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.GZIPInputStream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
    private String TAG = "HomeActivity";
    private RecyclerView recyclerView;
    Spinner spinner;
    List<Category> catList;
    List<Magazine> magazineList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        spinner = findViewById(R.id.spinner);
        recyclerView = findViewById(R.id.installed_app_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        getAdapter();

        findViewById(R.id.subscribe_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SubscriptionActivity.class));
            }
        });
    }

    private void getAdapter() {
      /*  ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);*/

        //Creating a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        //creating the api interface
        ApiInterface apiService = retrofit.create(ApiInterface.class);

        Call<List<Category>> callCat = apiService.getCategoryList();
        callCat.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                catList =  response.body();
                //Log.e(TAG,"responseCat:"+response.body());
                assert catList != null;
                for (Category magazine :
                        catList) {
                    Log.e(TAG,""+magazine.getCategoyrName());

                }
                Category cat = new Category();
                cat.setRID(0);
                cat.setCategoyrName("All");
                catList.add(0, cat);
                //attach spinner adapter
                SpinnerAdapter spinnerAdapter = new SpinnerAdapter(getApplicationContext(), catList);
                spinner.setAdapter(spinnerAdapter);
                spinnerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        Call<List<Magazine>> call = apiService.getMagazineList();
        call.enqueue(new Callback<List<Magazine>>() {
            @Override
            public void onResponse(Call<List<Magazine>> call, Response<List<Magazine>> response) {
                magazineList =  response.body();
                assert magazineList != null;
                for (Magazine magazine :
                        magazineList) {
                    Log.e(TAG,""+magazine.getThumbImage());
                }

                HomeRecyclerAdapter adapter = new HomeRecyclerAdapter(getApplicationContext(), catList, magazineList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                //grade text watcher
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                       Category category = (Category) spinner.getSelectedItem();
                       List<Magazine> sortMagList = new ArrayList<>();
                       sortMagList.clear();
                       if(category.getRID()> 0) {
                           for (Magazine magazine :
                                   magazineList) {
                               if (magazine.getCategoryRID().equals(category.getRID())) {
                                   sortMagList.add(magazine);
                               }

                           }
                       }else{
                           sortMagList.addAll(magazineList);
                       }
                        //attach apps list to adapter
                        HomeRecyclerAdapter adapter = new HomeRecyclerAdapter(getApplicationContext(), catList, sortMagList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<Magazine>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }

}

