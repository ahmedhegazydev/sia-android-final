package com.sia.siaandroidapp.model;

import com.sia.siaandroidapp.java.model.JwtTokenResponse;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class JwtTokenRespEntityTest {

    private final String jwttoken = "sdjsdJJWUWjd-d-cN@#&#*cn";
    @Mock
    JwtTokenResponse jwtTokenResponse;
    private int height = 2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(jwtTokenResponse.getJwttoken()).thenReturn(jwttoken);
//        Mockito.when(mediaEntity.getWidth()).thenReturn(width);
    }

    @Test
    public void testJwtToken() {
        Mockito.when(jwtTokenResponse.getJwttoken()).thenReturn(jwttoken);
        Assert.assertEquals("sdjsdJJWUWjd-d-cN@#&#*cn", jwtTokenResponse.getJwttoken());
    }

    //
//    @Test
//    public void testMediaFormat() {
//        Mockito.when(mediaEntity.getFormat()).thenReturn(format);
//        Assert.assertEquals("testing format", mediaEntity.getFormat());
//    }
//
//    @Test
//    public void testMediaHeight() {
//        Mockito.when(mediaEntity.getHeight()).thenReturn(height);
//        Assert.assertEquals(2, mediaEntity.getHeight());
//    }
//
//    @Test
//    public void testMediaUrlIncorrect() {
//        Mockito.when(mediaEntity.getUrl()).thenReturn(url);
//        Assert.assertNotEquals(" testing www.test.com", mediaEntity.getUrl());
//    }
//
    @Test
    public void testJwtTokenIncorrect() {
        Mockito.when(jwtTokenResponse.getJwttoken()).thenReturn(jwttoken);
        Assert.assertNotEquals("any thing hamada", jwtTokenResponse.getJwttoken());
    }
//
//    @Test
//    public void testMediaHeightIncorrect() {
//        Mockito.when(mediaEntity.getHeight()).thenReturn(height);
//        Assert.assertNotEquals(12, mediaEntity.getHeight());
//    }
//


    @After
    public void tearDown() throws Exception {
        jwtTokenResponse = null;
    }
}