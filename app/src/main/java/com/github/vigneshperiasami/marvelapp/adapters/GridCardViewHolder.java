package com.github.vigneshperiasami.marvelapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.vigneshperiasami.marvelapp.R;

public class GridCardViewHolder extends RecyclerView.ViewHolder {
  public ImageView thumbnail;
  public TextView title;
  public TextView price;

  public GridCardViewHolder(View itemView) {
    super(itemView);
    thumbnail = (ImageView) itemView.findViewById(R.id.imageView);
    title = (TextView) itemView.findViewById(R.id.title);
    price = (TextView) itemView.findViewById(R.id.price);
  }
}
