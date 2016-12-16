package com.bignerdranch.android.googlebookclient.MVP;

/**
 * Class control searching pages internal work.
 *
 */

public class Pages {


    /** Number of results of searching that show in one page. */
    private static final int NUMBER_OF_RESULTS_IN_PAGE = 10;

    /** Current result number for searching. */
    private int mCurrentResNum = 0;

    /** Total number of results of this request. */
    private int mTotlaResultsNum = 0;




    public int getCurrentResNum()
    {
        return mCurrentResNum;
    }


    public void resetCurrentResultCounter()
    {
        mCurrentResNum = 0;
    }

    public void increaseResNum()
    {
        mCurrentResNum += NUMBER_OF_RESULTS_IN_PAGE;
    }


    public void decreaseResNum()
    {
        mCurrentResNum -= NUMBER_OF_RESULTS_IN_PAGE;
    }


    public int getTotlaResultsNum()
    {
        return mTotlaResultsNum;
    }

    public void setTotlaResultsNum(int totalResults)
    {
        mTotlaResultsNum = totalResults;
    }


    /**
     * If check future increase of searching results. If next command {@link #increaseResNum()}
     * lead to non-exist page than return false. If it lead to show of normal searching page
     * than true.
     * Eg. if {@link #mTotlaResultsNum} = 500 and
     * current search page show 500-510 results (if {@link #NUMBER_OF_RESULTS_IN_PAGE} = 10)
     * than this page not exist (false).
     * @return - true - next searching page is exist all, everything is fine.
     *          false - next searching page not exist.
     */
    public boolean isNextSearchResultExist()
    {
        if ((mCurrentResNum + NUMBER_OF_RESULTS_IN_PAGE) >= mTotlaResultsNum)
        {
            return false;
        }
        else
        {
            return true;
        }
    }



}
