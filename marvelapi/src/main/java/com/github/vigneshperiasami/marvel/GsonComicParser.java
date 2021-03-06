package com.github.vigneshperiasami.marvel;

import com.github.vigneshperiasami.marvel.models.Comic;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

final class GsonComicParser implements IParser {
  private Gson gson;
  private JsonParser jsonParser = new JsonParser();
  private Type returnType = new TypeToken<List<Comic>>() {}.getType();

  public GsonComicParser(Gson gson) {
    this.gson = gson;
  }

  @Override
  public List<Comic> parse(String json) {
    return gson.fromJson(
        jsonParser.parse(json).getAsJsonObject()
            .getAsJsonObject("data").getAsJsonArray("results"), returnType);
  }
}
