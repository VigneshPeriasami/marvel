package com.github.vigneshperiasami.marvel;

import com.github.vigneshperiasami.marvel.models.Comic;

import java.util.Collections;
import java.util.List;

public class InMemoryStore implements MarvelStore {
  private List<Comic> comics = Collections.emptyList();

  @Override
  public void save(List<Comic> comicList) {
    comics.clear();
    comics.addAll(comicList);
  }

  @Override
  public boolean isEmpty() {
    return comics.isEmpty();
  }

  @Override
  public List<Comic> readFromStore() {
    return comics;
  }
}
