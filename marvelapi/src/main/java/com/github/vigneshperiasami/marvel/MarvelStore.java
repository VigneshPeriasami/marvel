package com.github.vigneshperiasami.marvel;

import com.github.vigneshperiasami.marvel.models.Comic;

import java.util.List;

public interface MarvelStore {
  void save(List<Comic> comicList);
  boolean isEmpty();
  List<Comic> readFromStore();
}
