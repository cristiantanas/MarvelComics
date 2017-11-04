package com.ct.android.marvelcomics.service;

import com.ct.android.marvelcomics.model.MarvelApiResponse;
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

public class GetHeroesService {
    private Retrofit api;

    public void getHeroes(final OnGetHeroesFinishedListener listener) {
        if ( api == null ) {
            api = new Retrofit.Builder()
                    .baseUrl(MarvelApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        MarvelApiService apiService = api.create(MarvelApiService.class);
        long timestamp = System.currentTimeMillis();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            String proof = timestamp + ApiConfig.API_PRIVATE_KEY + ApiConfig.API_KEY;
            digest.update(proof.getBytes(), 0, proof.length());
            String hash = new BigInteger(1, digest.digest()).toString(16);

            Call<MarvelApiResponse> call = apiService.getCharacters(timestamp, ApiConfig.API_KEY, hash);
            call.enqueue(new Callback<MarvelApiResponse>() {
                @Override
                public void onResponse(Call<MarvelApiResponse> call, Response<MarvelApiResponse> response) {
                    MarvelApiResponse.MarvelApiResponseData responseData = response.body().getData();
                    List<MarvelHero> heroes = responseData != null ? responseData.getResults() : new ArrayList<MarvelHero>();
                    listener.onResponse(heroes);
                }

                @Override
                public void onFailure(Call<MarvelApiResponse> call, Throwable t) {
                    listener.onFailure(t.getMessage());
                }
            });
        } catch (NoSuchAlgorithmException e) {
            listener.onFailure(e.getMessage());
        }
    }

    public void getHeroById(int heroId, final OnGetHeroByIdFinishedListener listener) {
        if ( api == null ) {
            api = new Retrofit.Builder()
                    .baseUrl(MarvelApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        MarvelApiService apiService = api.create(MarvelApiService.class);
        long timestamp = System.currentTimeMillis();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            String proof = timestamp + ApiConfig.API_PRIVATE_KEY + ApiConfig.API_KEY;
            digest.update(proof.getBytes(), 0, proof.length());
            String hash = new BigInteger(1, digest.digest()).toString(16);

            Call<MarvelApiResponse> call = apiService.getCharacterById(heroId, timestamp, ApiConfig.API_KEY, hash);
            call.enqueue(new Callback<MarvelApiResponse>() {
                @Override
                public void onResponse(Call<MarvelApiResponse> call, Response<MarvelApiResponse> response) {
                    MarvelApiResponse.MarvelApiResponseData responseData = response.body().getData();
                    List<MarvelHero> heroes = responseData != null ? responseData.getResults() : new ArrayList<MarvelHero>();
                    listener.onResponse(heroes.get(0));
                }

                @Override
                public void onFailure(Call<MarvelApiResponse> call, Throwable t) {
                    listener.onFailure(t.getMessage());
                }
            });
        } catch (NoSuchAlgorithmException e) {
            listener.onFailure(e.getMessage());
        }
    }

    public interface OnGetHeroesFinishedListener {
        void onResponse(List<MarvelHero> heroes);
        void onFailure(String message);
    }

    public interface OnGetHeroByIdFinishedListener {
        void onResponse(MarvelHero hero);
        void onFailure(String message);
    }
}
