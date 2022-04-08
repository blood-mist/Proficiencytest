package com.nishan.proficiencytestapp;

import android.net.Uri;

import androidx.lifecycle.ViewModelProvider;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class BaseTest {
    private MockWebServer mockWebServer;

    @Before
    public void setUp() throws Exception {
        this.configureMockServer();
    }

    private void configureMockServer() throws Exception {
        if (isMockServerEnabled()){
            mockWebServer = new MockWebServer();
            mockWebServer.start();
        }
    }

    @After
    public void tearDown() throws Exception {
        this.stopMockServer();
    }

    private void stopMockServer() throws IOException {
        if (isMockServerEnabled()){
            mockWebServer.shutdown();
        }
    }

    abstract boolean isMockServerEnabled();

    public void mockHttpResponse(String filename, int responseCode){
        mockWebServer.enqueue(new MockResponse().setResponseCode(responseCode).setBody(getJson(filename)));
    }

    private String getJson(String filename) {
        try {
            byte[] array = Files.readAllBytes(Paths.get(filename));
            return array.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }


}
