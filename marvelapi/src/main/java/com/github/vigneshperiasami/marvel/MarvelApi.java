package com.github.vigneshperiasami.marvel;

import com.github.vigneshperiasami.marvel.models.Comic;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MarvelApi {
  private final Downloader comicDownloader;
  private final MarvelStore store;
  private final String privateKey;
  private final String publicKey;

  public MarvelApi(String privateKey, String publicKey) {
    this(new InMemoryStore(), privateKey, publicKey);
  }

  public MarvelApi(MarvelStore marvelStore, String privateKey, String publicKey) {
    this(new ComicDownloader(new GsonComicParser(new Gson())), marvelStore, privateKey, publicKey);
  }

  MarvelApi(Downloader comicDownloader, MarvelStore store, String privateKey, String publicKey) {
    this.comicDownloader = comicDownloader;
    this.store = store;
    this.privateKey = privateKey;
    this.publicKey = publicKey;
  }

  public UriBuilder marvelUriBuilder() {
    return new UriBuilder(privateKey, publicKey);
  }

  public List<Comic> fetchMarvelComics(URI comicUri) throws IOException, URISyntaxException {
    if (store.isDataAvailable(comicUri)) {
      return store.readFromStore(comicUri);
    }
    List<Comic> comics = comicDownloader.downloadComics(comicUri);
    store.save(comicUri, comics);
    return comics;
  }

  // first attempt getting maximum possible comics
  public List<Comic> maxComicsInBudget(List<Comic> comicList, double maxBudget) {
    List<Comic> comicsInBudget = new LinkedList<>();
    List<Comic> sortedByPrices = sortByPrices(comicList);
    double remainingBudget = maxBudget;

    for (Comic comic : sortedByPrices) {
      if (comic.getPrice() > remainingBudget) {
        break;
      }

      comicsInBudget.add(comic);
      remainingBudget = remainingBudget - comic.getPrice();
    }
    return comicsInBudget;
  }

  private List<Comic> sortByPrices(List<Comic> comicList) {
    // never modify the order of the given list.
    List<Comic> listToSort = new ArrayList<>(comicList);
    Collections.sort(listToSort, new Comparator<Comic>() {
      @Override
      public int compare(Comic o1, Comic o2) {
        return Double.compare(o1.getPrice(), o2.getPrice());
      }
    });
    return listToSort;
  }
}
