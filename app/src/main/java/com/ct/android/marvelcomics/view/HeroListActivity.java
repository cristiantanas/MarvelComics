package com.ct.android.marvelcomics.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ct.android.marvelcomics.R;
import com.ct.android.marvelcomics.model.MarvelHero;
import com.ct.android.marvelcomics.presenter.HomePresenter;
import com.ct.android.marvelcomics.presenter.HomePresenterImpl;
import com.ct.android.marvelcomics.service.GetHeroesService;

import java.util.List;


public class HeroListActivity extends AppCompatActivity implements HomeView, MarvelHeroesAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private MarvelHeroesAdapter recyclerViewAdapter;

    private TextView emptyListPlaceholder;
    private ProgressBar progressIndicator;

    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        recyclerView = (RecyclerView) findViewById(R.id.hero_list);
        emptyListPlaceholder = (TextView) findViewById(R.id.list_empty);
        progressIndicator = (ProgressBar) findViewById(R.id.progress);

        presenter = new HomePresenterImpl(this, new GetHeroesService());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressIndicator.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressIndicator.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateView(List<MarvelHero> heroes) {
        if ( heroes.size() > 0 ) {
            emptyListPlaceholder.setVisibility(View.GONE);
            if ( recyclerViewAdapter != null ) {
                recyclerViewAdapter.changeList(heroes);
                recyclerViewAdapter.notifyDataSetChanged();
            }
            else {
                recyclerViewAdapter = new MarvelHeroesAdapter(this, heroes, this);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        }
        else {
            emptyListPlaceholder.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(MarvelHero hero) {
        Intent intent = new Intent(this, HeroDetailActivity.class);
        intent.putExtra(HeroDetailActivity.ARG_ITEM_ID, hero.getId());

        startActivity(intent);
    }
}
