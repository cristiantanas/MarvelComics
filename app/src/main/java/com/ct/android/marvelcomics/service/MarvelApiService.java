package com.ct.android.marvelcomics.service;


import com.ct.android.marvelcomics.model.CharacterApiResponse;
import com.ct.android.marvelcomics.model.ComicApiResponse;
import com.ct.android.marvelcomics.model.EventApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MarvelApiService {
    String BASE_URL = "https://gateway.marvel.com:443/";

    @GET("v1/public/characters")
    Call<CharacterApiResponse> getCharacters(@Query("ts") long timestamp, @Query("apikey") String apiKey,
                                             @Query("hash") String hash);

    @GET("v1/public/characters")
    Call<CharacterApiResponse> searchCharacterByName(@Query("nameStartsWith") String nameStartsWith,
                                                     @Query("ts") long timestamp,
                                                     @Query("apikey") String apiKey, @Query("hash") String hash);

    @GET("v1/public/characters/{characterId}")
    Call<CharacterApiResponse> getCharacterById(@Path("characterId") int characterId, @Query("ts") long timestamp,
                                                @Query("apikey") String apiKey, @Query("hash") String hash);

    @GET("v1/public/characters/{characterId}/comics")
    Call<ComicApiResponse> getCharacterComics(@Path("characterId") int characterId, @Query("ts") long timestamp,
                                              @Query("apikey") String apiKey, @Query("hash") String hash);

    @GET("v1/public/characters/{characterId}/events")
    Call<EventApiResponse> getCharacterEvents(@Path("characterId") int characterId, @Query("ts") long timestamp,
                                              @Query("apikey") String apiKey, @Query("hash") String hash);
}
