package com.ct.android.marvelcomics.presenter;



public interface HomePresenter {
    void onResume();
    void search(String searchParam);
    void onDestroy();
}
