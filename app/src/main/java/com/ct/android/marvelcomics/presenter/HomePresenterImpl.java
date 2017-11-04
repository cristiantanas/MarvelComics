package com.ct.android.marvelcomics.presenter;


import com.ct.android.marvelcomics.model.MarvelHero;
import com.ct.android.marvelcomics.service.HeroesService;
import com.ct.android.marvelcomics.view.HomeView;

import java.util.List;

public class HomePresenterImpl implements HomePresenter, HeroesService.OnGetHeroesFinishedListener {
    private HomeView homeView;
    private HeroesService heroesService;

    public HomePresenterImpl(HomeView view, HeroesService service) {
        this.homeView = view;
        this.heroesService = service;
    }

    @Override
    public void onResume() {
        if ( homeView != null ) {
            homeView.showProgress();
            heroesService.getHeroes(this);
        }
    }

    @Override
    public void search(String searchParam) {
        if ( homeView != null ) {
            homeView.showProgress();
            heroesService.searchHeroByName(searchParam, this);
        }
    }

    @Override
    public void onDestroy() {
        homeView = null;
    }

    @Override
    public void onHeroResponse(List<MarvelHero> heroes) {
        if ( homeView != null ) {
            homeView.updateView(heroes);
            homeView.hideProgress();
        }
    }

    @Override
    public void onFailure(String message) {
        //TODO: Handle failure messages
    }
}
