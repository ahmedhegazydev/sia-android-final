package com.sia.siaandroidapp.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class SplashScreen {

    @Mock
    Context mockContext;
    @Mock
    SharedPreferences mockPrefs;
    @Mock
    SharedPreferences.Editor mockEditor;

    @Before
    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        Mockito.when(mockContext.getSharedPreferences(anyString(),
//                anyInt())).thenReturn(mockPrefs);
//        Mockito.when(mockContext.getSharedPreferences(anyString(),
//                anyInt()).edit()).thenReturn(mockEditor);
//
//        Mockito.when(mockPrefs.getString("YOUR_KEY", null)).thenReturn("YOUR_VALUE");


    }

    @Test
    public void checkIfUserIsLoggedIn() {


    }

    @After
    public void tearDown() throws Exception {
        mockContext = null;

    }
}