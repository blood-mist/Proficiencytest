package com.nishan.proficiencytestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nishan.proficiencytestapp.databinding.ActivityMainBinding;
import com.nishan.proficiencytestapp.models.Items;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private RecyclerAdapter adapter;
    private ActivityMainBinding mainBinding;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);
        adapter = new RecyclerAdapter();
        swipeRefreshLayout=mainBinding.swipeContainer;
        RecyclerView recyclerView = mainBinding.recyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.init();
        viewModel.getItemsLiveData().observe(this, items -> {
            if (items != null) {
                Objects.requireNonNull(getSupportActionBar()).setTitle(items.getTitle());
                adapter.setResults(items.getRows());
            }else{
                //if items is null then there is something wrong with fecthing data from the API
                Toast.makeText(this,"Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.fetchData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //fetch data on swipe down to refresh
                viewModel.fetchData();
                // remove the progressbar after data is fecthed
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }
}