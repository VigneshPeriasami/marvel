package com.github.vigneshperiasami.marvel.models;

import com.google.gson.annotations.SerializedName;

public class ComicObject {
  public String name;
  public String role;
  @SerializedName("resourceURI")
  public String resourceUri;
}
