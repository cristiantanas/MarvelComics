package com.ct.android.marvelcomics.view;


import com.ct.android.marvelcomics.model.MarvelHero;

import java.util.List;

public interface HomeView {
    void showProgress();
    void hideProgress();
    void updateView(List<MarvelHero> heroes);
}
