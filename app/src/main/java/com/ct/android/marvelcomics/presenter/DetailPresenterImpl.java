package com.ct.android.marvelcomics.presenter;

import com.ct.android.marvelcomics.model.MarvelHero;
import com.ct.android.marvelcomics.service.GetHeroesService;
import com.ct.android.marvelcomics.view.DetailView;

public class DetailPresenterImpl implements DetailPresenter, GetHeroesService.OnGetHeroByIdFinishedListener {
    private DetailView detailView;
    private GetHeroesService heroesService;

    public DetailPresenterImpl(DetailView view, GetHeroesService service) {
        detailView = view;
        heroesService = service;
    }

    @Override
    public void onResume(int heroId) {
        if ( detailView != null ) {
            detailView.showProgress();
            heroesService.getHeroById(heroId, this);
        }
    }

    @Override
    public void onDestroy() {
        detailView = null;
    }

    @Override
    public void onResponse(MarvelHero hero) {
        if ( detailView != null ) {
            detailView.updateView(hero);
            detailView.hideProgress();
        }
    }

    @Override
    public void onFailure(String message) {
        //TODO: Handle failure messages
    }
}
