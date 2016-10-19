package com.github.vigneshperiasami.marvel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {
  public static String readResourceAsString(String path) {
    InputStream stream = Utils.class.getResourceAsStream(path);
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    String response = "";
    String line;
    try {
      while ((line = reader.readLine()) != null) {
        response += line;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return response;
  }
}
