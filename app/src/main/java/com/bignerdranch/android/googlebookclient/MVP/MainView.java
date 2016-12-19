package com.bignerdranch.android.googlebookclient.MVP;

import com.bignerdranch.android.googlebookclient.UI.BookViewItem;

import java.util.ArrayList;



public interface MainView {




    void restoreViewAfterConfigurationChanged();

    void hideStartImage();

    String getSearchRequest();

    void setSearchRequest(String request);

    void textViewFailureMessageSetText(String text);

    void showFailureOnResponseMessage(String message);

    void showListOfItems(ArrayList<BookViewItem> bookViewItems);

    ArrayList<BookViewItem> getBookItems();

    void setNewBookItems(ArrayList<BookViewItem> newBookItems);


    void showLeftRightRowImageBtn();




    void hideLeftRightRowImageBtn();

    void enableLeftRowImageBtn();

    void disableLeftRowImageBtn();

    void enableRightRowImageBtn();

    void disableRightRowImageBtn();

}
