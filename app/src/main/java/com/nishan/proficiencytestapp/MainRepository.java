package com.nishan.proficiencytestapp;

import androidx.annotation.NonNull;

import com.nishan.proficiencytestapp.models.Items;
import com.nishan.proficiencytestapp.models.StateLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainRepository {
    private FetchDataService fetchDataService;
    private StateLiveData<Items> itemsLiveData;

    public MainRepository() {
        itemsLiveData = new StateLiveData();
        Retrofit retrofit = ApiManager.getAdapter();
        fetchDataService=retrofit.create(FetchDataService.class);
    }

    public void fetchData() {


        fetchDataService.getData()
                .enqueue(new Callback<Items>() {
                    @Override
                    public void onResponse(@NonNull Call<Items> call, @NonNull Response<Items> response) {
                        if (response.body() != null) {
                            itemsLiveData.postSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Items> call, Throwable t) {
                        itemsLiveData.postError(t);
                    }

                });
    }

    public StateLiveData<Items> getItemsResponseLiveData() {
        return itemsLiveData;
    }
}
