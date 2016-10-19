package com.github.vigneshperiasami.marvelapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.vigneshperiasami.marvel.MarvelApi;
import com.github.vigneshperiasami.marvel.models.Comic;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.github.vigneshperiasami.marvelapp.BuildConfig.MARVEL_PRIVATE_KEY;
import static com.github.vigneshperiasami.marvelapp.BuildConfig.MARVER_PUBLIC_KEY;

public class MainActivity extends Activity implements MarvelListPresenter.MarvelListView {
  private static final String DEFAULT_IMAGE = "";

  private RecyclerView recyclerView;
  private MarvelListPresenter marvelListPresenter = new MarvelListPresenter(this);
  private GridAdapter gridAdapter;
  private View cntBudgetPanel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    cntBudgetPanel = findViewById(R.id.cnt_budget);

    gridAdapter = new GridAdapter(new ArrayList<Comic>());
    recyclerView.setAdapter(gridAdapter);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    marvelListPresenter.loadData();
  }

  @Override
  public void onDataAvailable(List<Comic> comicList) {
    cntBudgetPanel.setVisibility(View.VISIBLE);
    gridAdapter.addAll(comicList);
    gridAdapter.notifyDataSetChanged();
  }

  class GridCardViewHolder extends RecyclerView.ViewHolder {
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

  class GridAdapter extends RecyclerView.Adapter<GridCardViewHolder> {
    private final List<Comic> comicList;

    GridAdapter(List<Comic> comicList) {
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
      Picasso.with(MainActivity.this)
          .load(comic.getThumbnailImageUrl(DEFAULT_IMAGE)).into(holder.thumbnail);

      holder.title.setText(String.format("Title: %s", comic.getTitle()));
      holder.price.setText(String.format("Price: %s", comic.getPrice()));
    }

    public void addAll(List<Comic> comicList) {
      this.comicList.addAll(comicList);
    }

    @Override
    public int getItemCount() {
      return comicList.size();
    }
  }
}
