package com.ct.android.marvelcomics.service;


import com.ct.android.marvelcomics.model.MarvelApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MarvelApiService {
    String BASE_URL = "https://gateway.marvel.com:443/";

    @GET("v1/public/characters")
    Call<MarvelApiResponse> getCharacters(@Query("ts") long timestamp, @Query("apikey") String apiKey,
                                          @Query("hash") String hash);

    @GET("v1/public/characters/{characterId}")
    Call<MarvelApiResponse> getCharacterById(@Path("characterId") int characterId, @Query("ts") long timestamp,
                                             @Query("apikey") String apiKey, @Query("hash") String hash);
}
