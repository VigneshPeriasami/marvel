package com.github.vigneshperiasami.marvel.models;

public class Comic {
  private String id;
  private String title;
  private String description;
  private int pageCount;
  private Thumbnail thumbnail;
  private Thumbnail[] images;
  private Price[] prices;
  private InternalComicObject creators;
  private InternalComicObject characters;

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public int getPageCount() {
    return pageCount;
  }

  public String getThumbnailImageUrl() {
    return getThumbnailImageUrl(null);
  }

  public ComicObject[] getAuthors() {
    return creators.items;
  }

  public ComicObject[] getCharacters() {
    return characters.items;
  }

  public double getPrice() {
    if (prices.length < 0) {
      return 0;
    }
    // todo: cache
    for (Price price : prices) {
      if (price.type.equals("printPrice")) {
        return price.price;
      }
    }
    return 0;
  }

  public String getThumbnailImageUrl(String defaultValue) {
    if (thumbnail == null) {
      return defaultValue;
    }
    return thumbnail.path + "/portrait_medium." + thumbnail.extension;
  }
}
