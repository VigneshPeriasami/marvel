package com.github.vigneshperiasami.marvel;

import com.github.vigneshperiasami.marvel.models.Comic;

import java.util.List;

public interface IParser {
  List<Comic> parse(String json);
}
