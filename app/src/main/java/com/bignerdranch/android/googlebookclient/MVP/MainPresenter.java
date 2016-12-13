package com.bignerdranch.android.googlebookclient.MVP;

import com.bignerdranch.android.googlebookclient.Models.BookFindResponse;
import com.bignerdranch.android.googlebookclient.UI.BookViewItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;



public interface MainPresenter  {




    void clickOnSearchBtn();

    void sortByTitle();

    void sortByPublishDate();

    void onResponseToRequestOfServer(ArrayList<BookViewItem> bookViewItem);

    void onFailureToRequestOfServer(Call<BookFindResponse> call, Throwable t);






}
