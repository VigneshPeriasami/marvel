package com.github.vigneshperiasami.marvelapp.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.vigneshperiasami.marvel.models.Comic;
import com.github.vigneshperiasami.marvelapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridCardViewHolder>  {
  private static final String DEFAULT_IMAGE = "";
  private final Context context;
  private final List<Comic> comicList;

  public GridAdapter(Context context, List<Comic> comicList) {
    this.context = context;
    this.comicList = comicList;
  }

  @Override
  public GridCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic,
        parent, false);
    return new GridCardViewHolder(view);
  }

  @Override
  public void onBindViewHolder(GridCardViewHolder holder, int position) {
    Comic comic = comicList.get(position);
    Picasso.with(context)
        .load(comic.getThumbnailImageUrl(DEFAULT_IMAGE)).into(holder.thumbnail);

    holder.title.setText(String.format("Title: %s", comic.getTitle()));
    holder.price.setText(String.format("Price: %s", comic.getPrice()));
  }

  public void addAll(List<Comic> comicList) {
    this.comicList.addAll(comicList);
  }

  // so tired ..
  public List<Comic> getListRef() {
    return comicList;
  }

  @Override
  public int getItemCount() {
    return comicList.size();
  }
}
