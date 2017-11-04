package com.ct.android.marvelcomics.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ct.android.marvelcomics.R;
import com.ct.android.marvelcomics.model.Comic;
import com.ct.android.marvelcomics.model.HeroEvent;
import com.ct.android.marvelcomics.model.MarvelHero;
import com.ct.android.marvelcomics.presenter.DetailPresenter;
import com.ct.android.marvelcomics.presenter.DetailPresenterImpl;
import com.ct.android.marvelcomics.service.HeroesService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HeroDetailActivity extends AppCompatActivity implements DetailView {

    public static final String ARG_ITEM_ID = "item_id";

    private int heroId;

    private ImageView heroPicture;
    private CollapsingToolbarLayout appBarLayout;
    private ProgressBar progressIndicator;
    private CheckedTextView comicsSection;
    private CheckedTextView eventsSection;
    private TextView heroDescription;

    private DetailPresenter presenter;
    private HeroDetailFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        heroPicture = (ImageView) findViewById(R.id.hero_thumbnail);
        appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        progressIndicator = (ProgressBar) findViewById(R.id.progress);
        comicsSection = (CheckedTextView) findViewById(R.id.comicsSection);
        eventsSection = (CheckedTextView) findViewById(R.id.eventsSection);
        heroDescription = (TextView) findViewById(R.id.hero_description);

        heroId = getIntent().getIntExtra(ARG_ITEM_ID, -1);

        presenter = new DetailPresenterImpl(this, new HeroesService());

        fragment = new HeroDetailFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.resources, fragment)
                .commit();

        comicsSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSectionVisibility();
                presenter.onComicsSectionSelected(heroId);
            }
        });

        eventsSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSectionVisibility();
                presenter.onEventsSectionSelected(heroId);
            }
        });
    }

    private void toggleSectionVisibility() {
        if ( comicsSection.isChecked() ) {
            comicsSection.setChecked(false);
            comicsSection.setBackground(ContextCompat.getDrawable(this, R.drawable.section_inactive));
            comicsSection.setCheckMarkDrawable(android.R.drawable.button_onoff_indicator_off);

            eventsSection.setChecked(true);
            eventsSection.setBackground(ContextCompat.getDrawable(this, R.drawable.section_active));
            eventsSection.setCheckMarkDrawable(android.R.drawable.button_onoff_indicator_on);
        }
        else {
            comicsSection.setChecked(true);
            comicsSection.setBackground(ContextCompat.getDrawable(this, R.drawable.section_active));
            comicsSection.setCheckMarkDrawable(android.R.drawable.button_onoff_indicator_on);

            eventsSection.setChecked(false);
            eventsSection.setBackground(ContextCompat.getDrawable(this, R.drawable.section_inactive));
            eventsSection.setCheckMarkDrawable(android.R.drawable.button_onoff_indicator_off);
        }
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

        heroDescription.setText(hero.getDescription());

        comicsSection.setChecked(true);
        comicsSection.setBackground(ContextCompat.getDrawable(this, R.drawable.section_active));
        comicsSection.setCheckMarkDrawable(android.R.drawable.button_onoff_indicator_on);
        String comicsSectionTitle = String.valueOf(hero.getComics().getReturned()) + " " + getString(R.string.comics_lbl);
        comicsSection.setText(comicsSectionTitle);

        eventsSection.setChecked(false);
        eventsSection.setBackground(ContextCompat.getDrawable(this, R.drawable.section_inactive));
        eventsSection.setCheckMarkDrawable(android.R.drawable.button_onoff_indicator_off);
        String eventsSectionTitle = String.valueOf(hero.getEvents().getReturned()) + " " + getString(R.string.events_lbl);
        eventsSection.setText(eventsSectionTitle);

        presenter.onComicsSectionSelected(hero.getId());
    }

    @Override
    public void updateComics(List<Comic> comics) {
        fragment.showComics(comics);
    }

    @Override
    public void updateEvents(List<HeroEvent> events) {
        fragment.showHeroEvents(events);
    }
}
