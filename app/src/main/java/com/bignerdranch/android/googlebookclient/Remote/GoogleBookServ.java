package com.bignerdranch.android.googlebookclient.Remote;

import com.bignerdranch.android.googlebookclient.Models.BookFindResponse;
import com.bignerdranch.android.googlebookclient.Models.Items;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface GoogleBookServ {




    @GET("/books/v1/volumes")
    Call<BookFindResponse> bookRequest(
            @Query("q") String aRequest
               );




    @GET("/books/v1/volumes")
    Call<BookFindResponse> nextResPageRequest(
            @Query("q") String aRequest,
            @Query("startIndex") String aPage
    );



    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.googleapis.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


}
