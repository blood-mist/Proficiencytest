package com.nishan.proficiencytestapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nishan.proficiencytestapp.models.Items;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainRepository {
    private FetchDataService fetchDataService;
    private MutableLiveData<Items> itemsLiveData;

    public MainRepository() {
        itemsLiveData = new MutableLiveData<>();
        Retrofit retrofit = ApiManager.getAdapter();
        fetchDataService=retrofit.create(FetchDataService.class);
    }

    public void fetchData() {


        fetchDataService.getData()
                .enqueue(new Callback<Items>() {
                    @Override
                    public void onResponse(@NonNull Call<Items> call, @NonNull Response<Items> response) {
                        if (response.body() != null) {
                            itemsLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Items> call, @NonNull Throwable t) {
                        itemsLiveData.postValue(null);
                    }
                });
    }

    public LiveData<Items> getItemsResponseLiveData() {
        return itemsLiveData;
    }
}
