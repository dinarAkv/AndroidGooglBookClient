package com.bignerdranch.android.googlebookclient.UI;

/**
 * Item for preserve fields of every book item in recycleView adapter.
 *
 */

public class BookViewItem  {



    /** Identificator for select sorting fields. */
    public static final String SORT_ID_BY_TITLE = "sort_id_by_title";
    public static final String SORT_ID_BY_PUBLISH_DATE = "sort_id_by_publish_date";


    private String mTitle = "";

    private String mAuthors = "";

    private String mYear = "";

    private String mDiscription = "";

    /** URL of image of the book. */
    private String mUrl = "";

    /** Show which field there is need to sort. As default sort titles. */
    private String mSortSelector = SORT_ID_BY_TITLE;






    public String getmAuthors() {
        return mAuthors;
    }

    public void setmAuthors(String mAuthors) {
        this.mAuthors = mAuthors;
    }

    public String getmDiscription() {
        return mDiscription;
    }

    public void setmDiscription(String mDiscription) {
        this.mDiscription = mDiscription;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmYear() {
        return mYear;
    }

    public void setmYear(String mYear) {
        this.mYear = mYear;
    }

    public String getmSortSelector() {
        return mSortSelector;
    }

    public void setmSortSelector(String mSortSelector) {
        this.mSortSelector = mSortSelector;
    }


}
