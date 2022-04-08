package com.nishan.proficiencytestapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nishan.proficiencytestapp.models.Items;

public class MainViewModel extends AndroidViewModel {
    private MainRepository mainRepository;
    private LiveData<Items> itemsLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }
    public void init() {
        mainRepository = new MainRepository();
        itemsLiveData = mainRepository.getItemsResponseLiveData();
    }

    public void fetchData() {
        mainRepository.fetchData();
    }

    public LiveData<Items> getItemsLiveData() {
        return itemsLiveData;
    }
}
