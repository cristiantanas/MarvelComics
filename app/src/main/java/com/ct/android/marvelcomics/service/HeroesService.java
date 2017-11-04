package com.ct.android.marvelcomics.service;

import com.ct.android.marvelcomics.model.CharacterApiResponse;
import com.ct.android.marvelcomics.model.Comic;
import com.ct.android.marvelcomics.model.ComicApiResponse;
import com.ct.android.marvelcomics.model.EventApiResponse;
import com.ct.android.marvelcomics.model.HeroEvent;
import com.ct.android.marvelcomics.model.MarvelHero;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeroesService {
    private Retrofit api;

    public HeroesService() {
        if ( api == null ) {
            api = new Retrofit.Builder()
                    .baseUrl(MarvelApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    public void getHeroes(final OnGetHeroesFinishedListener listener) {
        MarvelApiService apiService = api.create(MarvelApiService.class);

        try {
            long timestamp = System.currentTimeMillis();
            String hash = md5(timestamp + ApiConfig.API_PRIVATE_KEY + ApiConfig.API_KEY);

            Call<CharacterApiResponse> call = apiService.getCharacters(timestamp, ApiConfig.API_KEY, hash);
            call.enqueue(new Callback<CharacterApiResponse>() {
                @Override
                public void onResponse(Call<CharacterApiResponse> call, Response<CharacterApiResponse> response) {
                    List<MarvelHero> heroes = new ArrayList<>();
                    CharacterApiResponse responseBody = response.body();
                    if ( responseBody != null ) {
                        CharacterApiResponse.Data responseData = responseBody.getData();
                        heroes = responseData != null ? responseData.getResults() : new ArrayList<MarvelHero>();
                    }

                    listener.onHeroResponse(heroes);
                }

                @Override
                public void onFailure(Call<CharacterApiResponse> call, Throwable t) {
                    listener.onFailure(t.getMessage());
                }
            });
        } catch (NoSuchAlgorithmException e) {
            listener.onFailure(e.getMessage());
        }
    }

    public void getHeroById(int heroId, final OnGetHeroByIdFinishedListener listener) {
        MarvelApiService apiService = api.create(MarvelApiService.class);

        try {
            long timestamp = System.currentTimeMillis();
            String hash = md5(timestamp + ApiConfig.API_PRIVATE_KEY + ApiConfig.API_KEY);

            Call<CharacterApiResponse> call = apiService.getCharacterById(heroId, timestamp, ApiConfig.API_KEY, hash);
            call.enqueue(new Callback<CharacterApiResponse>() {
                @Override
                public void onResponse(Call<CharacterApiResponse> call, Response<CharacterApiResponse> response) {
                    List<MarvelHero> heroes = new ArrayList<>();
                    CharacterApiResponse responseBody = response.body();
                    if ( responseBody != null ) {
                        CharacterApiResponse.Data responseData = responseBody.getData();
                        heroes = responseData != null ? responseData.getResults() : new ArrayList<MarvelHero>();
                    }
                    listener.onHeroResponse(heroes.get(0));
                }

                @Override
                public void onFailure(Call<CharacterApiResponse> call, Throwable t) {
                    listener.onFailure(t.getMessage());
                }
            });
        } catch (NoSuchAlgorithmException e) {
            listener.onFailure(e.getMessage());
        }
    }

    public void searchHeroByName(String searchParam, final OnGetHeroesFinishedListener listener) {
        MarvelApiService apiService = api.create(MarvelApiService.class);

        try {
            long timestamp = System.currentTimeMillis();
            String hash = md5(timestamp + ApiConfig.API_PRIVATE_KEY + ApiConfig.API_KEY);

            Call<CharacterApiResponse> call = apiService.searchCharacterByName(searchParam, timestamp, ApiConfig.API_KEY, hash);
            call.enqueue(new Callback<CharacterApiResponse>() {
                @Override
                public void onResponse(Call<CharacterApiResponse> call, Response<CharacterApiResponse> response) {
                    List<MarvelHero> heroes = new ArrayList<>();
                    CharacterApiResponse responseBody = response.body();
                    if ( responseBody != null ) {
                        CharacterApiResponse.Data responseData = responseBody.getData();
                        heroes = responseData != null ? responseData.getResults() : new ArrayList<MarvelHero>();
                    }
                    listener.onHeroResponse(heroes);
                }

                @Override
                public void onFailure(Call<CharacterApiResponse> call, Throwable t) {
                    listener.onFailure(t.getMessage());
                }
            });
        } catch (NoSuchAlgorithmException e) {
            listener.onFailure(e.getMessage());
        }
    }

    public void getCharacterComics(int heroId, final OnGetComicsFinishedListener listener) {
        MarvelApiService apiService = api.create(MarvelApiService.class);

        try {
            long timestamp = System.currentTimeMillis();
            String hash = md5(timestamp + ApiConfig.API_PRIVATE_KEY + ApiConfig.API_KEY);

            Call<ComicApiResponse> call = apiService.getCharacterComics(heroId, timestamp, ApiConfig.API_KEY, hash);
            call.enqueue(new Callback<ComicApiResponse>() {
                @Override
                public void onResponse(Call<ComicApiResponse> call, Response<ComicApiResponse> response) {
                    List<Comic> comics = new ArrayList<>();
                    ComicApiResponse responseBody = response.body();
                    if ( responseBody != null ) {
                        ComicApiResponse.Data responseData = responseBody.getData();
                        comics = responseData != null ? responseData.getResults() : new ArrayList<Comic>();
                    }
                    listener.onComicResponse(comics);
                }

                @Override
                public void onFailure(Call<ComicApiResponse> call, Throwable t) {
                    listener.onFailure(t.getMessage());
                }
            });
        } catch (NoSuchAlgorithmException e) {
            listener.onFailure(e.getMessage());
        }
    }

    public void getCharacterEvents(int heroId, final OnGetEventsFinishedListener listener) {
        MarvelApiService apiService = api.create(MarvelApiService.class);

        try {
            long timestamp = System.currentTimeMillis();
            String hash = md5(timestamp + ApiConfig.API_PRIVATE_KEY + ApiConfig.API_KEY);

            Call<EventApiResponse> call = apiService.getCharacterEvents(heroId, timestamp, ApiConfig.API_KEY, hash);
            call.enqueue(new Callback<EventApiResponse>() {
                @Override
                public void onResponse(Call<EventApiResponse> call, Response<EventApiResponse> response) {
                    List<HeroEvent> events = new ArrayList<>();
                    EventApiResponse responseBody = response.body();
                    if ( responseBody != null ) {
                        EventApiResponse.Data responseData = responseBody.getData();
                        events = responseData != null ? responseData.getResults() : new ArrayList<HeroEvent>();
                    }
                    listener.onEventResponse(events);
                }

                @Override
                public void onFailure(Call<EventApiResponse> call, Throwable t) {
                    listener.onFailure(t.getMessage());
                }
            });
        } catch (NoSuchAlgorithmException e) {
            listener.onFailure(e.getMessage());
        }
    }

    private String md5(String message) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(message.getBytes(), 0, message.length());
        return new BigInteger(1, messageDigest.digest()).toString(16);
    }

    public interface OnGetHeroesFinishedListener {
        void onHeroResponse(List<MarvelHero> heroes);
        void onFailure(String message);
    }

    public interface OnGetHeroByIdFinishedListener {
        void onHeroResponse(MarvelHero hero);
        void onFailure(String message);
    }

    public interface OnGetComicsFinishedListener {
        void onComicResponse(List<Comic> comics);
        void onFailure(String message);
    }

    public interface OnGetEventsFinishedListener {
        void onEventResponse(List<HeroEvent> events);
        void onFailure(String message);
    }
}
