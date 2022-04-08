package com.nishan.proficiencytestapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.nishan.proficiencytestapp.models.Items;
import com.nishan.proficiencytestapp.models.StateLiveData;

public class MainViewModel extends AndroidViewModel {
    private MainRepository mainRepository;
    private StateLiveData<Items> itemsLiveData;

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

    public StateLiveData<Items> getItemsLiveData() {
        return itemsLiveData;
    }
}
