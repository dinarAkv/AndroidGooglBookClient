package com.bignerdranch.android.googlebookclient.Helper;

import com.bignerdranch.android.googlebookclient.UI.BookViewItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class handle all stuff connected with sorting.
 */

public class Sorter {



    /**
     * Function sort book items by titles.
     * @param bookData - raw unsorted list.
     * @return - sorted list.
     */
    public static ArrayList<BookViewItem> sortTitlesOfBook(ArrayList<BookViewItem> bookData)
    {
        /** Build new arryList copy. */
        ArrayList<BookViewItem> bookItems = bookData;

        /** Sort titles. */
        Collections.sort(bookItems, new Comparator<BookViewItem>() {
            @Override
            public int compare(BookViewItem bookViewItem1, BookViewItem bookViewItem2) {

                return bookViewItem1.getmTitle().compareTo(bookViewItem2.getmTitle());
            }
        });

        return bookItems;
    }





}
