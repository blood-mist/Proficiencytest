package com.nishan.proficiencytestapp;

import static org.junit.Assert.*;

import android.os.Build;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.nishan.proficiencytestapp.models.Items;
import com.nishan.proficiencytestapp.models.RowsItem;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;

@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = Build.VERSION_CODES.S, minSdk = Build.VERSION_CODES.N, manifest = Config.NONE)

public class MainUnitTest extends BaseTest {

    @Mock
    FetchDataService fetchDataService;

    @Mock
    private Observer<Items> observer;

    private MainActivity mainActivity;
    private MainViewModel mainViewModel;
    ArrayList<RowsItem> rowsList = new ArrayList<>();
    Items item = new Items("title1", rowsList);


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainViewModel = new ViewModelProvider(mainActivity).get(MainViewModel.class);
    }

    @Override
    boolean isMockServerEnabled() {
        return false;
    }


    @Test
    public void fetchData_InvokesCorrectApiCalls() {
        RowsItem rowsItem = new RowsItem();
        rowsItem.setTitle("Hi");
        rowsItem.setImageHref("testUrl");
        rowsItem.setDescription("jsdhkfhj");


        //Given
        this.mockHttpResponse("proficiencyjson.json", HttpURLConnection.HTTP_OK);
        // Pre-test
        assertNull("User should be null because stream not started yet", this.mainViewModel.getItemsLiveData().getValue());
        // Execute View Model
        this.mainViewModel.fetchData();
        // Checks
        assertEquals("User must be fetched", item, Objects.requireNonNull(this.mainViewModel.getItemsLiveData().getValue()).getData());
        assertNull("No error must be founded", this.mainViewModel.getItemsLiveData().getValue().getError());

    }

    @Test
    public void getUser_whenError() {
        // Prepare data
        this.mockHttpResponse("getUser_whenSuccess.json", HttpURLConnection.HTTP_BAD_GATEWAY);
        // Pre-test
        assertNull("User should be null because stream not started yet", this.mainViewModel.getItemsLiveData().getValue());
        // Execute View Model
        this.mainViewModel.fetchData();
        // Checks
        assertNull("User must be null because of http error", this.mainViewModel.getItemsLiveData().getValue());
        assertNotEquals("Error value must not be empty", null, this.mainViewModel.getItemsLiveData().getValue().getError());
    }

}