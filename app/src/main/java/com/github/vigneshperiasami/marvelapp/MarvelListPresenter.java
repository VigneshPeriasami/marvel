package com.github.vigneshperiasami.marvelapp;

import android.os.AsyncTask;

import com.github.vigneshperiasami.marvel.MarvelApi;
import com.github.vigneshperiasami.marvel.models.Comic;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import static com.github.vigneshperiasami.marvelapp.BuildConfig.MARVEL_PRIVATE_KEY;
import static com.github.vigneshperiasami.marvelapp.BuildConfig.MARVER_PUBLIC_KEY;

public class MarvelListPresenter {
  private final MarvelListView marvelListView;
  private static final String MARVEL_BASE_URL = "http://gateway.marvel.com/v1/public/comics";
  private final MarvelApi marvelApi;

  public MarvelListPresenter(MarvelListView marvelListView) {
    this(marvelListView, new MarvelApi(MARVEL_PRIVATE_KEY, MARVER_PUBLIC_KEY));
  }

  public MarvelListPresenter(MarvelListView marvelListView, MarvelApi marvelApi) {
    this.marvelListView = marvelListView;
    this.marvelApi = marvelApi;
  }

  public void loadData() {
    try {
      new AsyncDownloader().execute(paginatedUri(20, 100));
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }

  public URI[] paginatedUri(int pageSize, int maxLimit) throws URISyntaxException {
    List<URI> uris = new LinkedList<>();
    for (int i = 0; i < maxLimit; i += pageSize) {
      URI uri = marvelApi.marvelUriBuilder().limit(pageSize).offset(i).build(MARVEL_BASE_URL);
      uris.add(uri);
    }
    return uris.toArray(new URI[uris.size()]);
  }

  class AsyncDownloader extends AsyncTask<URI, List<Comic>, List<Comic>> {

    @Override
    protected List<Comic> doInBackground(URI... params) {
      List<Comic> finalList = new LinkedList<>();
      for (URI uri : params) {
        try {
          List<Comic> partial = marvelApi.fetchMarvelComics(uri);
          finalList.addAll(partial);
          publishProgress(partial);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      return finalList;
    }

    @Override
    protected void onProgressUpdate(List<Comic>... values) {
      super.onProgressUpdate(values);
      marvelListView.onDataAvailable(values[0]);
    }

    @Override
    protected void onPostExecute(List<Comic> comicList) {
      super.onPostExecute(comicList);
    }
  }

  public interface MarvelListView {
    void onDataAvailable(List<Comic> comicList);
  }
}
