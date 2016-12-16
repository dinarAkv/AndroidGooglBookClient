package com.bignerdranch.android.googlebookclient.Helper;

import com.bignerdranch.android.googlebookclient.UI.BookViewItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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



    /** Default format for full date. */
    public static final String DEFAULT_YYYY_MM_DD = "0000-00-00";

    /** Default format for truncated date. */
    private static final String DEFAULT_MM_DD = "-00-00";

    /** Position after "0000" in {@link #DEFAULT_YYYY_MM_DD}. */
    public static final int POS_AFTER_YYYY = 4;



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


    /**
     * Function sort book items by publish dates.
     * @param bookData - raw unsorted list.
     * @return - sorted list.
     */
    public static ArrayList<BookViewItem> sortPublishDateOfBook(ArrayList<BookViewItem> bookData)
    {
        /** Build new arryList copy. */
        ArrayList<BookViewItem> bookItems = bookData;


        for (BookViewItem bookItem : bookItems)
        {
            /** Length of normal date, eg. 1999-09-19. */
            int lengthNormalDate = 10; //

            /** First hyphen position in normal date. */
            int firstHyphenPos = 5; ;

            /** Second hyphen position in normal date. */
            int secondHyphenPos = 8;




            /** If exist only year, than add default month and day, eg. 1999-00-00.
             * This addition need to correct work of sorting algorithm. */
            if (bookItem.getmYear().length() == 4)
            {
                String year = bookItem.getmYear();
                year += DEFAULT_MM_DD;
                bookItem.setmYear(year);
            }
            /** If year field doesn't exist, string is empty ("") or any other case
             * than add full default date "0000-00-00", that move item to the end of list.
             * This addition need to correct work of sorting algorithm. */
            else if (bookItem.getmYear().length() == 0)
            {
                bookItem.setmYear(DEFAULT_YYYY_MM_DD);
            }

        }


        /** Sort collection of books by publish date.
         * Sort from early dates in the beginning to late dates in the end.  */
        Collections.sort(bookItems, new Comparator<BookViewItem>() {
            public int compare(BookViewItem item1, BookViewItem item2) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try
                {
                    return dateFormat.parse(item1.getmYear()).compareTo(dateFormat.parse(item2.getmYear()));
                }
                catch(ParseException e)
                {
                    throw new IllegalArgumentException(e);
                }
            }
        });

        /**  Reverse sorting from late dates in the beginning and early date at the end. */
        Collections.reverse(bookItems);

        /** Delete default format sub strings added upper {@link #DEFAULT_YYYY_MM_DD},
         * {@link #DEFAULT_MM_DD}. */
        bookItems = returnOriginalFormatPublishDate(bookItems);



        return bookItems;
    }


    /**
     * Function get sorted books objects {@link BookViewItem} and delete "00-00", "0000-00-00" in publish date field
     * to return original format for this objects.
     * @return - books objects {@link BookViewItem} with original format of
     *          publish date field.
     */
    public static ArrayList<BookViewItem> returnOriginalFormatPublishDate(ArrayList<BookViewItem> bookData)
    {

        /** Build new arryList copy. */
        ArrayList<BookViewItem> bookItems = bookData;

        for (BookViewItem bookViewItem : bookItems)
        {
            /** If publish date of {@link BookViewItem} has {@link DEFAULT_YYYY_MM_DD}
             * than delete fully this date. */
            if (bookViewItem.getmYear().equals(DEFAULT_YYYY_MM_DD))
            {
                bookViewItem.setmYear("");
            }
            /** If publish date of {@link BookViewItem} has a year (eg. 1954) but has default
             * month and date (eg. 1954-00-00), than truncate default month and date */
            else if (bookViewItem.getmYear()
                    .substring(POS_AFTER_YYYY, DEFAULT_YYYY_MM_DD.length()).equals(DEFAULT_MM_DD))
            {
                bookViewItem.setmYear(bookViewItem.getmYear().substring(0, POS_AFTER_YYYY));
            }
        }


        return bookItems;
    }







}
