package com.founderr.founderr;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;


public interface ApiInterface {
    @POST("MagazineList")
    Call<List<Magazine>> getMagazineList();

    @POST("Categories")
    Call<List<Category>> getCategoryList();
}