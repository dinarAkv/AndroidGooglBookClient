package com.bignerdranch.android.googlebookclient.MVP;

import com.bignerdranch.android.googlebookclient.UI.BookViewItem;

import java.util.ArrayList;



public interface MainView {



    String getSearchRequest();

    void textViewSetText(String text);

    void showFailureOnResponseMessage(String message);

    void showListOfItems(ArrayList<BookViewItem> bookViewItems);

    ArrayList<BookViewItem> getBookItems();

    void setNewBookItems(ArrayList<BookViewItem> newBookItems);

}