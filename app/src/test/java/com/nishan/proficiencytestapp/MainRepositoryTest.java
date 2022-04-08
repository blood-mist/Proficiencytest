package com.nishan.proficiencytestapp;

import static org.junit.Assert.*;

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

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
@RunWith(RobolectricTestRunner.class)
public class MainUnitTest extends BaseTest{

    @Mock
    FetchDataService fetchDataService;

    @Mock
    private Observer<Items> observer;

    private MainActivity mainActivity;
    private MainViewModel mainViewModel;
    ArrayList<RowsItem> rowsList= new ArrayList<>();
    Items item =new Items("title1", rowsList);


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainViewModel = new ViewModelProvider(MainActivity.class.newInstance()).get(MainViewModel.class);
    }

    @Override
    boolean isMockServerEnabled() {
        return false;
    }


    @Test
    public void fetchData_InvokesCorrectApiCalls(){

        //Given
        this.mockHttpResponse("getData_success.json", HttpURLConnection.HTTP_OK);
        // Pre-test
        assertEquals("User should be null because stream not started yet",null, this.mainViewModel.getItemsLiveData().getValue());
        // Execute View Model
        this.mainViewModel.fetchData();
        // Checks
        assertEquals("User must be fetched", item, this.mainViewModel.getItemsLiveData().getValue());
        assertEquals(false, this.viewModel.isLoading.value, "Should be reset to 'false' because stream ended")
        assertEquals(null, this.viewModel.errorMessage.value, "No error must be founded")

    }

}