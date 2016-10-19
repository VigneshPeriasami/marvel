package com.github.vigneshperiasami.marvel;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UriBuilder {
  private final String privateKey;
  private final String publicKey;
  private int limit;
  private int offset;

  UriBuilder(String privateKey, String publicKey) {
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
    // to support caching
    long timestamp = 1234;
    String withAuthInfo = marvelUrl + "?apikey=" + publicKey
        + "&ts=" + timestamp
        + "&hash=" + getHash(timestamp);
    if (limit > 0) {
      withAuthInfo += "&limit=" + limit;
    }
    if (offset > 0) {
      withAuthInfo += "&offset=" + offset;
    }
    return withAuthInfo;
  }

  private String getHash(long timestamp) {
    return MD5((String.valueOf(timestamp) + privateKey + publicKey));
  }

  private String MD5(String md5) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] array = md.digest(md5.getBytes());
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < array.length; ++i) {
        sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
      }
      return sb.toString();
    } catch (java.security.NoSuchAlgorithmException e) {
    }
    return null;
  }
}
