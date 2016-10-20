package com.github.vigneshperiasami.marvelapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.vigneshperiasami.marvel.models.Comic;
import com.github.vigneshperiasami.marvelapp.adapters.GridAdapter;

import java.util.List;

public class BudgetListActivity extends AppCompatActivity {
  private static final String EXTRA_COMIC_LIST = "extra_comic_list";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    GridAdapter gridAdapter = new GridAdapter(this, readComicListFromIntent(getIntent()));
    recyclerView.setAdapter(gridAdapter);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
  }

  public static Intent getIntent(Context context, List<Comic> comicList) {
    Intent intent = new Intent(context, BudgetListActivity.class);
    intent.putExtra(EXTRA_COMIC_LIST, Utils.toString(comicList));
    return intent;
  }

  private List<Comic> readComicListFromIntent(Intent intent) {
    return Utils.fromString(intent.getStringExtra(EXTRA_COMIC_LIST));
  }
}
