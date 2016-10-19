package com.github.vigneshperiasami.marvel;

import com.github.vigneshperiasami.marvel.models.Comic;
import com.google.gson.Gson;

import org.junit.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class MarvelApiTest {

  @Test
  public void checkBudgetFilter() {
    MarvelApi marvelApi = new MarvelApi("", "");
    List<Comic> comics = getComicFromResources();

    double budget = 10;
    List<Comic> inBudget = marvelApi.maxComicsInBudget(comics, budget);
    double price = 0;
    for (Comic comic : inBudget) {
      price += comic.getPrice();
    }

    assertThat(price).isLessThan(budget);
  }

  private List<Comic> getComicFromResources() {
    String jsonResponse = Utils.readResourceAsString("/comics");
    return new GsonComicParser(new Gson()).parse(jsonResponse);
  }
}
