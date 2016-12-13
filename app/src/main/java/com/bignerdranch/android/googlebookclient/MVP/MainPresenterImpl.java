package com.bignerdranch.android.googlebookclient.MVP;

import com.bignerdranch.android.googlebookclient.Helper.Sorter;
import com.bignerdranch.android.googlebookclient.Models.BookFindResponse;
import com.bignerdranch.android.googlebookclient.Models.Items;
import com.bignerdranch.android.googlebookclient.Remote.InternetConnection;
import com.bignerdranch.android.googlebookclient.UI.BookViewItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;



public class MainPresenterImpl implements MainPresenter {

    /** Referance to realize MVP pattern. */
    private MainActivity mMainActivity;

    public MainPresenterImpl(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    /** Realize client/server requests. */
    private InternetConnection mInternetConnection;

    /**
     * Realize reaction on click of button for searching book on base of key words.
     */
    @Override
    public void clickOnSearchBtn() {
        mInternetConnection = new InternetConnection(this);

        /** Get users request. */
        String userRequest = mMainActivity.getSearchRequest();
        mInternetConnection.findBooksByKeys(userRequest);

    }

    /**
     * Reaction to request in {@link InternetConnection}.
     * @param call - request object.
     * @param t - exception.
     */
    @Override
    public void onFailureToRequestOfServer(Call<BookFindResponse> call, Throwable t) {
        mMainActivity.showFailureOnResponseMessage(t.getMessage());
    }


    /**
     * Reaction to request in {@link InternetConnection}.
     * @param bookViewItems - list of book items with filled fields.
     */
    @Override
    public void onResponseToRequestOfServer(ArrayList<BookViewItem> bookViewItems) {

        mMainActivity.showListOfItems(bookViewItems);

    }


    @Override
    public void sortByPublishDate() {

    }

    @Override
    public void sortByTitle() {

        /** Get current book items in recycler view adapter. */
        ArrayList<BookViewItem> bookViewItems = mMainActivity.getBookItems();

        /** Sort book items by title. */
        bookViewItems = Sorter.sortTitlesOfBook(bookViewItems);

        /** Set sorted items and show changes. */
        mMainActivity.setNewBookItems(bookViewItems);
    }
}
