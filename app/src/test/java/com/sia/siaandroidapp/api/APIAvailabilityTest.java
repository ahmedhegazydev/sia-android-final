package com.sia.siaandroidapp.api;

import com.sia.siaandroidapp.java.constants.Constants;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class APIAvailabilityTest {

    @Test
    public void testAvailability() throws Exception {
        URLConnection connection = new
                URL(Constants.BASE_URL + Constants.REGISTER)
                .openConnection();
        InputStream response = connection.getInputStream();

        StringBuilder buffer = new StringBuilder();
        try (BufferedReader reader = new
                BufferedReader(new InputStreamReader(response,
                Charset.defaultCharset()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                buffer.append(line);
            }
        }

        assert buffer.length() > 0;
    }
}