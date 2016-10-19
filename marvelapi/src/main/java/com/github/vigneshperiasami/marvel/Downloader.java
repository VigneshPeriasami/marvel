package com.github.vigneshperiasami.marvel;

import com.github.vigneshperiasami.marvel.models.Comic;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public interface Downloader {
  List<Comic> downloadComics(URI comicUrl) throws IOException;
}
