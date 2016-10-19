package com.github.vigneshperiasami.marvel;

import com.github.vigneshperiasami.marvel.models.Comic;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface Downloader {
  List<Comic> downloadComics(String comicUrl) throws URISyntaxException, IOException;
}
