package com.github.vigneshperiasami.marvel;

import com.google.gson.Gson;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class GsonComicParserTest {

  @Test
  public void basicSerialization() {
    String jsonResponse = Utils.readResourceAsString("/comics");
    GsonComicParser gsonComicParser = new GsonComicParser(new Gson());
    assertThat(gsonComicParser.parse(jsonResponse)).hasSize(20);
  }
}
