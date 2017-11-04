package com.ct.android.marvelcomics.presenter;


public interface DetailPresenter {
    void onResume(int heroId);
    void onComicsSectionSelected(int heroId);
    void onEventsSectionSelected(int heroId);
    void onDestroy();
}
