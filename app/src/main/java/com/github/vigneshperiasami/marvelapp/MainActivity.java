package com.github.vigneshperiasami.marvelapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.github.vigneshperiasami.marvel.models.Comic;
import com.github.vigneshperiasami.marvelapp.adapters.GridAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MarvelListPresenter.MarvelListView {
  private RecyclerView recyclerView;
  private MarvelListPresenter marvelListPresenter = new MarvelListPresenter(this);
  private GridAdapter gridAdapter;
  private View cntBudgetPanel;
  private EditText edtBudget;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    cntBudgetPanel = findViewById(R.id.cnt_budget);
    edtBudget = (EditText) findViewById(R.id.edt_budget);

    findViewById(R.id.btn_filter).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (edtBudget.getText().length() == 0) {
          return;
        }
        List<Comic> budgetComics = marvelListPresenter.filterComic(
            gridAdapter.getListRef(), Double.valueOf(edtBudget.getText().toString()));
        showBudgetComics(budgetComics);
      }
    });

    gridAdapter = new GridAdapter(this, new ArrayList<Comic>());
    recyclerView.setAdapter(gridAdapter);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    marvelListPresenter.loadData();
  }

  private void showBudgetComics(List<Comic> budgetComics) {
    startActivity(BudgetListActivity.getIntent(this, budgetComics));
  }

  @Override
  public void onDataAvailable(List<Comic> comicList) {
    cntBudgetPanel.setVisibility(View.VISIBLE);
    gridAdapter.addAll(comicList);
    gridAdapter.notifyDataSetChanged();
  }
}
