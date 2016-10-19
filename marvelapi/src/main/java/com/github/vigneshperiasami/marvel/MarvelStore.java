package com.github.vigneshperiasami.marvel;

import com.github.vigneshperiasami.marvel.models.Comic;

import java.net.URI;
import java.util.List;

public interface MarvelStore {
  void save(URI uri, List<Comic> comicList);
  boolean isDataAvailable(URI uri);
  List<Comic> readFromStore(URI uri);
}
