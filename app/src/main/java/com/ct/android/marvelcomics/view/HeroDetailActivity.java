package com.ct.android.marvelcomics.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ct.android.marvelcomics.R;
import com.ct.android.marvelcomics.model.MarvelHero;
import com.ct.android.marvelcomics.presenter.DetailPresenter;
import com.ct.android.marvelcomics.presenter.DetailPresenterImpl;
import com.ct.android.marvelcomics.service.GetHeroesService;
import com.squareup.picasso.Picasso;

public class HeroDetailActivity extends AppCompatActivity implements DetailView {

    public static final String ARG_ITEM_ID = "item_id";

    private int heroId;

    private ImageView heroPicture;
    private CollapsingToolbarLayout appBarLayout;
    private ProgressBar progressIndicator;

    private DetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        heroPicture = (ImageView) findViewById(R.id.hero_thumbnail);
        appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        progressIndicator = (ProgressBar) findViewById(R.id.progress);

        heroId = getIntent().getIntExtra(ARG_ITEM_ID, -1);

        presenter = new DetailPresenterImpl(this, new GetHeroesService());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume(heroId);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, HeroListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        progressIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressIndicator.setVisibility(View.GONE);
    }

    @Override
    public void updateView(MarvelHero hero) {
        Picasso.with(this)
                .load(hero.getThumbnail().getFullPath())
                .into(heroPicture);
        if (appBarLayout != null) {
            appBarLayout.setTitle(hero.getName());
        }
    }
}
