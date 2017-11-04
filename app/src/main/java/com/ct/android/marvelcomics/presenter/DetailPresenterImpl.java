package com.ct.android.marvelcomics.presenter;

import com.ct.android.marvelcomics.model.Comic;
import com.ct.android.marvelcomics.model.HeroEvent;
import com.ct.android.marvelcomics.model.MarvelHero;
import com.ct.android.marvelcomics.service.HeroesService;
import com.ct.android.marvelcomics.view.DetailView;

import java.util.List;

public class DetailPresenterImpl implements DetailPresenter,
        HeroesService.OnGetHeroByIdFinishedListener,
        HeroesService.OnGetComicsFinishedListener,
        HeroesService.OnGetEventsFinishedListener {
    private DetailView detailView;
    private HeroesService heroesService;

    public DetailPresenterImpl(DetailView view, HeroesService service) {
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
    public void onComicsSectionSelected(int heroId) {
        heroesService.getCharacterComics(heroId, this);
    }

    @Override
    public void onEventsSectionSelected(int heroId) {
        heroesService.getCharacterEvents(heroId, this);
    }

    @Override
    public void onDestroy() {
        detailView = null;
    }

    @Override
    public void onHeroResponse(MarvelHero hero) {
        if ( detailView != null ) {
            detailView.updateView(hero);
            detailView.hideProgress();
        }
    }

    @Override
    public void onComicResponse(List<Comic> comics) {
        if ( detailView != null ) {
            detailView.updateComics(comics);
        }
    }

    @Override
    public void onEventResponse(List<HeroEvent> events) {
        if ( detailView != null ) {
            detailView.updateEvents(events);
        }
    }

    @Override
    public void onFailure(String message) {
        //TODO: Handle failure messages
    }
}
