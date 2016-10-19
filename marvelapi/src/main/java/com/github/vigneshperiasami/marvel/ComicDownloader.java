package com.github.vigneshperiasami.marvel;

import com.github.vigneshperiasami.marvel.models.Comic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.List;

final class ComicDownloader implements Downloader {
  private final IParser parser;

  public ComicDownloader(IParser parser) {
    this.parser = parser;
  }

  @Override
  public List<Comic> downloadComics(URI uri) throws IOException {
    HttpURLConnection urlConnection = (HttpURLConnection) uri.toURL().openConnection();
    urlConnection.setRequestMethod("GET");
    BufferedReader reader = new BufferedReader(
        new InputStreamReader(urlConnection.getInputStream()));
    String response = "";
    String line;

    while ((line = reader.readLine()) != null) {
      response += line;
    }
    return parser.parse(response);
  }
}
