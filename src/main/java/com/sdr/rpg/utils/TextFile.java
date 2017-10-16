package com.sdr.rpg.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by Konstantin on 16.10.2017.
 */

public class TextFile {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static final String readFromResource(String resource) {
        return read(TextFile.class.getResourceAsStream(resource));
    }

    private static final String read(InputStream is) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) builder.append(line + LINE_SEPARATOR);
            reader.close();
            System.gc();
            return builder.toString().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
