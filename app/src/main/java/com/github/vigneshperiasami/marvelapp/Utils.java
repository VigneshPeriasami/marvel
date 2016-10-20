package com.github.vigneshperiasami.marvelapp;


import com.github.vigneshperiasami.marvel.models.Comic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Utils {
  private static final Gson GSON = new Gson();

  // ugly hack instead should go for parcelable.
  public static String toString(List<Comic> comicList) {
    return GSON.toJson(comicList);
  }

  public static List<Comic> fromString(String comicString) {
    Type type = new TypeToken<List<Comic>>() {}.getType();
    return GSON.fromJson(comicString, type);
  }
}
