package com.github.vigneshperiasami.marvel;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UriBuilder {
  private final String privateKey;
  private final String publicKey;
  private int limit;
  private int offset;

  public UriBuilder(String privateKey, String publicKey) {
    this.privateKey = privateKey;
    this.publicKey = publicKey;
  }

  public UriBuilder limit(int limit) {
    this.limit = limit;
    return this;
  }

  public UriBuilder offset(int offset) {
    this.offset = offset;
    return this;
  }

  public URI build(String baseUrl) throws URISyntaxException {
    return new URI(appendAuthInfo(baseUrl));
  }

  private String appendAuthInfo(String marvelUrl) {
    String withAuthInfo = marvelUrl + "?apikey=" + publicKey
        + "&hash=" + getHash(System.currentTimeMillis());
    if (limit > 0) {
      withAuthInfo += "&limit=" + limit;
    }
    if (offset > 0) {
      withAuthInfo += "&offset=" + offset;
    }
    return withAuthInfo;
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
