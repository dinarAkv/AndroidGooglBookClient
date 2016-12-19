package com.bignerdranch.android.googlebookclient.MVP;

import android.os.Bundle;

import com.bignerdranch.android.googlebookclient.Models.BookFindResponse;
import com.bignerdranch.android.googlebookclient.UI.BookViewItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;



public interface MainPresenter  {





    Bundle onSaveInstanceState(Bundle outState);

    void  onRestoreInstanceState(Bundle savedInstanceState);

    void clickOnSearchBtn();

    void clickOnRightArrowBtn();

    void clickOnLeftArrowBtn();

    void sortByTitle();

    void sortByPublishDate();

    void onResponseToRequestOfServer(ArrayList<BookViewItem> bookViewItem, int totalRequestsNum);

    void onFailureToRequestOfServer(Call<BookFindResponse> call, Throwable t);






}
