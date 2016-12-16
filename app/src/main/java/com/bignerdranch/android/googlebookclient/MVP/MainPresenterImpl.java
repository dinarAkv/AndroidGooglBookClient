package com.bignerdranch.android.googlebookclient.MVP;

import com.bignerdranch.android.googlebookclient.Helper.Sorter;
import com.bignerdranch.android.googlebookclient.Models.BookFindResponse;
import com.bignerdranch.android.googlebookclient.Remote.InternetConnection;
import com.bignerdranch.android.googlebookclient.UI.BookViewItem;

import java.util.ArrayList;

import retrofit2.Call;


public class MainPresenterImpl implements MainPresenter {

    /** Referance to realize MVP pattern. */
    private MainActivity mMainActivity;

    /** Models of this presenter. */
    /** Control searching pages number. */
    private Pages mSearchPages;



    public MainPresenterImpl(MainActivity mainActivity) {

        mMainActivity = mainActivity;
        mSearchPages = new Pages();
    }

    /** Realize client/server requests. */
    private InternetConnection mInternetConnection;

    /**
     * Realize reaction click on button for searching book on base of key words.
     */
    @Override
    public void clickOnSearchBtn() {
        mInternetConnection = new InternetConnection(this);

        /** Get users request. */
        String userRequest = mMainActivity.getSearchRequest();
        mInternetConnection.findBooksByKeys(userRequest);

        /** It is first page than disable left arrow. */
        mMainActivity.disableLeftRowImageBtn();

        /** Hide left and right arrow buttons. */
        mMainActivity.hideLeftRightRowImageBtn();

        /** Hide image before search link to increase information space. */
        mMainActivity.hideStartImage();

        /** If it is first page of searching results than hide this page. */
        if (mSearchPages.getCurrentResNum() == 0)
        {
            mMainActivity.disableLeftRowImageBtn();
        }

        /** Reset counter of search results. */
        mSearchPages.resetCurrentResultCounter();

    }




    /**
     * Realize reaction click on button for right arrow. It load next searching result page.
     */
    @Override
    public void clickOnRightArrowBtn() {

        /** Get next result for searching page. */
        mSearchPages.increaseResNum();

        /** Form request for next searching result page and send request to server. */
        mInternetConnection.getSpecifySearchingResultPage(
                mMainActivity.getSearchRequest(), mSearchPages.getCurrentResNum()
        );

        /** It is not first page of searching results, so enable button. */
        mMainActivity.enableLeftRowImageBtn();


        /** Hide left and right arrow buttons. */
        mMainActivity.hideLeftRightRowImageBtn();



//        /** Check if next searching page exist. If it not exits (false) than
//         * disable next page button. */
//        if (mSearchPages.isNextSearchResultExist())
//        {
//            mMainActivity.disableRightRowImageBtn();
//        }
//        /** Else we can load next searching page so enable next page button. */
//        else
//        {
//            mMainActivity.enableRightRowImageBtn();
//        }


    }










    /**
     * Realize reaction click on button for left arrow. It load next searching result page.
     */
    @Override
    public void clickOnLeftArrowBtn()
    {
        /** Get previous result for searching page. */
        mSearchPages.decreaseResNum();


        /** Form request for next searching result page and send request to server. */
        mInternetConnection.getSpecifySearchingResultPage(
                mMainActivity.getSearchRequest(), mSearchPages.getCurrentResNum()
        );

        /** If it is  first page of searching results than disable button. */
        if (mSearchPages.getCurrentResNum() == 0)
        {
            mMainActivity.disableLeftRowImageBtn();
        }

        /** Hide left and right arrow buttons. */
        mMainActivity.hideLeftRightRowImageBtn();
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
    public void onResponseToRequestOfServer(ArrayList<BookViewItem> bookViewItems, int totalRequestsNum) {

        /** Show found items. */
        mMainActivity.showListOfItems(bookViewItems);

        /** Set total number of items found for this request. */
        mSearchPages.setTotlaResultsNum(totalRequestsNum);


        /** Check if next searching page exist. If it not exits (false) than
         * disable next page button. */
        if (mSearchPages.isNextSearchResultExist())
        {
            mMainActivity.enableRightRowImageBtn();
        }
        /** Else we can load next searching page so enable next page button. */
        else
        {
            mMainActivity.disableRightRowImageBtn();
        }


    }


    @Override
    public void sortByPublishDate() {

        /** Get current book items in recycler view adapter. */
        ArrayList<BookViewItem> bookViewItems = mMainActivity.getBookItems();

        /** Sort book items by publishing date. */
        bookViewItems = Sorter.sortPublishDateOfBook(bookViewItems);

        /** Set sorted items and show changes. */
        mMainActivity.setNewBookItems(bookViewItems);
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
