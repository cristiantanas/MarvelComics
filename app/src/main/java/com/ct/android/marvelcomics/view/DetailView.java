package com.ct.android.marvelcomics.view;

import com.ct.android.marvelcomics.model.MarvelHero;

public interface DetailView {
    void showProgress();
    void hideProgress();
    void updateView(MarvelHero hero);
}
