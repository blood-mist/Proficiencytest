package com.nishan.proficiencytestapp;

import com.nishan.proficiencytestapp.models.Items;
import com.nishan.proficiencytestapp.models.StateLiveData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FetchDataService  {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    Call<Items> getData();
}
