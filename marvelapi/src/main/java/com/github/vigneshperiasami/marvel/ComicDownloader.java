package com.github.vigneshperiasami.marvel;

import com.github.vigneshperiasami.marvel.models.Comic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

final class ComicDownloader implements Downloader {
  private final IParser parser;
  private final String privateKey;
  private final String publicKey;

  public ComicDownloader(IParser parser, String privateKey, String publicKey) {
    this.parser = parser;
    this.privateKey = privateKey;
    this.publicKey = publicKey;
  }

  public List<Comic> downloadComics(String comicUrl) throws URISyntaxException, IOException {
    return downloadComics(new URI(appendAuthInfo(comicUrl)));
  }

  private List<Comic> downloadComics(URI uri) throws IOException {
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

  private String appendAuthInfo(String marvelUrl) {
    return marvelUrl + "?apikey=" + publicKey + "&hash=" + getHash(System.currentTimeMillis());
  }

  private String getHash(long timestamp) {
    String hash = null;
    try {
      byte[] bytes = MessageDigest.getInstance("MD5").digest(
          (String.valueOf(timestamp) + privateKey + publicKey).getBytes());
      hash = new String(bytes);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hash;
  }
}
