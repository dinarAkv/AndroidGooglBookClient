package com.bignerdranch.android.googlebookclient.Remote;

import android.util.Log;

import com.bignerdranch.android.googlebookclient.MVP.MainPresenter;
import com.bignerdranch.android.googlebookclient.Models.BookFindResponse;
import com.bignerdranch.android.googlebookclient.Models.Items;
import com.bignerdranch.android.googlebookclient.UI.BookViewItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Object realize logic of internet connection with server.
 */



/** Specify search key words. */
/* https://www.googleapis.com/books/v1/volumes?q=pushkin  */

/** Specify key word and result of search page */
/* https://www.googleapis.com/books/v1/volumes?q=pushkin&startIndex=12 */






public class InternetConnection {

    private static final String TAG = InternetConnection.class.getSimpleName();



    /** To reailze MVP pattern. */
    private MainPresenter mMainPresenter;


    /** Add search command for query. */
    private static final String SEARCH_CMD = "search+";

    /** Get user request. */
//    String requestStr = SEARCH_CMD + mEditText_BookSearch.getText().toString();


    public InternetConnection(MainPresenter mMainPresenter) {
        this.mMainPresenter = mMainPresenter;
    }

    /**
     * Function initialize new call to server with user query.
     * @param userRequest - user query, it is some key words in order
     *                    to find some special books.
     */
    public void findBooksByKeys(String userRequest)
    {
        /** Combine user request and default request command. */
        String fullRequest = SEARCH_CMD + userRequest;

        /** Build object for requests. */
        GoogleBookServ googleBookServ = GoogleBookServ.retrofit.create(GoogleBookServ.class);



        final Call<BookFindResponse> callSearchRequest = googleBookServ.bookRequest(fullRequest);

        Log.d(TAG, "URL = "  + googleBookServ.bookRequest(fullRequest).request().url().toString());


        callSearchRequest.enqueue(new Callback<BookFindResponse>() {
            @Override
            public void onResponse(Call<BookFindResponse> call, Response<BookFindResponse> response) {

                  mMainPresenter.onResponseToRequestOfServer(formItemsOfBookData(response));
            }

            @Override
            public void onFailure(Call<BookFindResponse> call, Throwable t) {
                mMainPresenter.onFailureToRequestOfServer(call, t);
            }
        });

    }


    /**
     * Function fill with data book items {@link BookViewItem}.
     * @param - resulting object to response of request.
     * @return - list of data about every book item.
     */
    public ArrayList<BookViewItem> formItemsOfBookData(Response<BookFindResponse> response)
    {
        /** Container of all items; */
        ArrayList<BookViewItem> bookViewItems = new ArrayList<>();

        for (Items item : response.body().getItems() )
        {

            /** Add new item to the list. */
            bookViewItems.add(fillDataInItem(item));

        }

        return bookViewItems;
    }





    /**
     * Function fill with data one of item.
     * @param item - item that there is need to fill with data.
     * @return - filled with data item.
     */
    public BookViewItem fillDataInItem(Items item)
    {
        BookViewItem bookViewItem = new BookViewItem();

        /** Set all data for this item */
        bookViewItem.setmTitle(item.getVolumeInfo().getTitle());


        /** Fill data about authors. */
        String authorsStr = "";
        /** Combine all authors in one string object. */
        List<String> authors = item.getVolumeInfo().getAuthors();

        /** If null sign that no information about authors. */
        if (authors == null)
        {
            authorsStr = "-";
        }
        /** If 1 author than add him without comma. */
        else if (authors.size() == 1)
        {
            authorsStr = authors.get(0);
        }
        /** If several authors than add them in one string dividing with comma sign. */
        else
        {
            for (String author : authors)
            {
                authorsStr += author + ", ";
            }
        }
        /** Add authors in item. */
        bookViewItem.setmAuthors(authorsStr);

        /** Fill date of book publishing. */
        bookViewItem.setmYear(item.getVolumeInfo().getPublishedDate());

        /** Fill description of book. */
        bookViewItem.setmDiscription(item.getVolumeInfo().getDescription());

        /** Set url of image of this book. */
        bookViewItem.setmUrl(item.getVolumeInfo().getImageLinks().getThumbnail());

        return bookViewItem;
    }








}
