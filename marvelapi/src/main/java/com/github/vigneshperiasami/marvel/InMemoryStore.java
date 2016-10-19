package com.github.vigneshperiasami.marvel;

import com.github.vigneshperiasami.marvel.models.Comic;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryStore implements MarvelStore {
  // todo: use LRU
  private Map<String, List<Comic>> uriComicListMap = new HashMap<>();

  @Override
  public void save(URI uri, List<Comic> comicList) {
    uriComicListMap.put(uri.toString(), comicList);
  }

  @Override
  public boolean isDataAvailable(URI uri) {
    List<Comic> comicList = uriComicListMap.get(uri.toString());
    return comicList != null && !comicList.isEmpty();
  }

  @Override
  public List<Comic> readFromStore(URI uri) {
    return uriComicListMap.get(uri.toString());
  }
}
