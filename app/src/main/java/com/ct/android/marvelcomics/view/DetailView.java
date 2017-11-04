package com.ct.android.marvelcomics.view;

import com.ct.android.marvelcomics.model.Comic;
import com.ct.android.marvelcomics.model.HeroEvent;
import com.ct.android.marvelcomics.model.MarvelHero;

import java.util.List;

public interface DetailView {
    void showProgress();
    void hideProgress();
    void updateView(MarvelHero hero);
    void updateComics(List<Comic> comics);
    void updateEvents(List<HeroEvent> events);
}
