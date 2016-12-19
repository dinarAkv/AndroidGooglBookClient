package com.bignerdranch.android.googlebookclient.MVP;

import android.content.res.Configuration;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.android.googlebookclient.R;
import com.bignerdranch.android.googlebookclient.UI.BookRecyclerViewAdapter;
import com.bignerdranch.android.googlebookclient.UI.BookViewItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MainView {



    private static final String TAG = "MainActivity";



    /** Presenter of MVP pattern. */
    private MainPresenter mMainPresenter;





    /** Id for save current searching phrase in search line. */
    static final String SAVE_CURRENT_SEARCH_STRING = "save_current_search_string";
    /** Id for save current search result. */
    static final String SAVE_CURRENT_SEARCH_RESULT_NUM = "save_current_search_result_num";
    /** Id for save total number of results for this request. */
    static final String SAVE_CURRENT_SEARCH_TOTAL_RES_NUM = "save_current_search_total_res_num";







    @BindView(R.id.linaerLayoutMainActivity)
    LinearLayout mLinearLayout_MainActivity;


    /** Image before search link. */
    @BindView(R.id.imageViewStartImage)
    ImageView mImageView_StartImage;

    /** Get search requests. */
    @BindView(R.id.editTextBookSearch)
    EditText mEditText_BookSearch;

    /** Set request. */
    @BindView(R.id.buttonSearch)
    Button mButton_Search;


    @BindView(R.id.textViewRequestResult)
    TextView mTextView_FailureMessage;


    @BindView(R.id.fabInstruments)
    FloatingActionButton mFloatingActionButton_Options;

    @BindView(R.id.recyclerViewBookItems)
    RecyclerView mRecyclerView_BookItems;


    /** Left arrow imageButton for decrease page number of search result.  */
    @BindView(R.id.imageButtonLeftDecPageNum)
    ImageButton mImageButton_LeftDecPageNum;

    /** Right arrow imageButton for increase page number of search result.  */
    @BindView(R.id.imageButtonRightIncPageNum)
    ImageButton mImageButton_RightIncPageNum;



    /** Adapter for {@link BookRecyclerViewAdapter} restored after configuration has changed. */
    private BookRecyclerViewAdapter mRestoredBookRecyclerViewAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "ON CREATE");

        /** Bind view objects. */
        ButterKnife.bind(this);

        /** Initialize presenter. */
        mMainPresenter = new MainPresenterImpl(this);




        /** Initialize listeners. */
        initListeners();






    }


    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG, "ON RESUME");
    }



    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(TAG, "ON PAUSE");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(TAG, "ON STOP");
    }

    @Override
    protected void onRestart()
    {
        Log.d(TAG, "ON RESTART");
        super.onRestart();



    }




    @Override
    public Object onRetainCustomNonConfigurationInstance()
    {
        /** Save adapter to avoid new information load. */
        return mRecyclerView_BookItems.getAdapter();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {

        Log.d(TAG, "onSaveInstanceState");

        outState = mMainPresenter.onSaveInstanceState(outState);

        super.onSaveInstanceState(outState);

    }


    @Override
    public void restoreViewAfterConfigurationChanged()
    {
        /** Get saved adapter object. */
        mRestoredBookRecyclerViewAdapter = (BookRecyclerViewAdapter) getLastCustomNonConfigurationInstance();

        /** If adapter and recyclerView doesn't null than show content of
         * recyclerView adapter. */
        if (mRestoredBookRecyclerViewAdapter != null && mRecyclerView_BookItems != null)
        {
            /** Attach adapter to recyclerView. */
            mRecyclerView_BookItems.setAdapter(mRestoredBookRecyclerViewAdapter);
            /** Set layoutManager to position the items. */
            mRecyclerView_BookItems.setLayoutManager(new LinearLayoutManager(this));
            /** Restore state when no start image. */
            mImageView_StartImage.setVisibility(View.GONE);
        }



    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {

        Log.d(TAG, "onRestoreInstanceState");

        mMainPresenter.onRestoreInstanceState(savedInstanceState);

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void hideStartImage()
    {
        mImageView_StartImage.setVisibility(View.GONE);
    }

    /***
     * Get user typed search request.
     * @return - request implementation.
     */
    @Override
    public String getSearchRequest() {
        return mEditText_BookSearch.getText().toString();
    }


    /**
     * Set text for search request.
     * @param request - text to set.
     */
    @Override
    public void setSearchRequest(String request)
    {
        mEditText_BookSearch.setText(request);
    }

    @Override
    public void textViewFailureMessageSetText(String text) {
        mTextView_FailureMessage.setText(text);
    }


    /**
     * Show failure.
     * @param message - description of failure.
     */
    @Override
    public void showFailureOnResponseMessage(String message) {
        /** If something wrong, show message about it. */
        mTextView_FailureMessage.setVisibility(View.VISIBLE);
        mTextView_FailureMessage.setText(getResources().getString(R.string.requestFailureText) + message);
    }


    /**
     * Show list of book items.
     * @param bookViewItems - content of every book item.
     */
    @Override
    public void showListOfItems(ArrayList<BookViewItem> bookViewItems) {
        /** Vanish view for failure message. */
        mTextView_FailureMessage.setVisibility(View.GONE);

        /** Create adapter passing data about book items. */
        BookRecyclerViewAdapter bookRecyclerViewAdapter = new BookRecyclerViewAdapter(bookViewItems, this);




        /** Attach adapter to recyclerView. */
        mRecyclerView_BookItems.setAdapter(bookRecyclerViewAdapter);
        /** Set layoutManager to position the items. */
        mRecyclerView_BookItems.setLayoutManager(new LinearLayoutManager(this));




    }


    @Override
    public ArrayList<BookViewItem> getBookItems() {
        return ((BookRecyclerViewAdapter) mRecyclerView_BookItems.getAdapter()).getBookDataList();
    }


    @Override
    public void setNewBookItems(ArrayList<BookViewItem> newBookItems) {

        /** Get adapter of recycler view items. */
        BookRecyclerViewAdapter bookRecyclerViewAdapter =
                ((BookRecyclerViewAdapter) mRecyclerView_BookItems.getAdapter());

        /** Set new list. */
        bookRecyclerViewAdapter.setBookDataList(newBookItems);

        /** Show changes. */
        bookRecyclerViewAdapter.notifyDataSetChanged();

    }


    @Override
    public void showLeftRightRowImageBtn() {
        mImageButton_LeftDecPageNum.setVisibility(View.VISIBLE);
        mImageButton_RightIncPageNum.setVisibility(View.VISIBLE);
    }



    @Override
    public void hideLeftRightRowImageBtn() {
        mImageButton_LeftDecPageNum.setVisibility(View.GONE);
        mImageButton_RightIncPageNum.setVisibility(View.GONE);
    }


    @Override
    public void disableLeftRowImageBtn() {
        mImageButton_LeftDecPageNum.setEnabled(false);
    }

    @Override
    public void enableLeftRowImageBtn() {
        mImageButton_LeftDecPageNum.setEnabled(true);
    }


    @Override
    public void disableRightRowImageBtn()
    {
        mImageButton_RightIncPageNum.setEnabled(false);
    }

    @Override
    public void enableRightRowImageBtn()
    {
        mImageButton_RightIncPageNum.setEnabled(true);
    }

    /**
     * Function initialize listeners.
     */
    public void initListeners()
    {
        mButton_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainPresenter.clickOnSearchBtn();

            }
        });




        mFloatingActionButton_Options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Creat pop up menu object. */
                PopupMenu optians = new PopupMenu(MainActivity.this, mFloatingActionButton_Options);
                /** Inflate popup menu. */
                optians.getMenuInflater().inflate(R.menu.popup_options, optians.getMenu());
                /** Set listener for menu. */
                optians.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        /** Handle selected item. */
                        switch (item.getItemId())
                        {
                            case R.id.sortByTitle:
                                mMainPresenter.sortByTitle();
                                return true;
                            case R.id.sortByPublishDate:
                                mMainPresenter.sortByPublishDate();
                                return true;
                            default:
                                return MainActivity.super.onOptionsItemSelected(item);
                        }

                    }
                });

                optians.show();
            }
        });





        mImageButton_RightIncPageNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainPresenter.clickOnRightArrowBtn();
            }
        });


        mImageButton_LeftDecPageNum.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mMainPresenter.clickOnLeftArrowBtn();
            }
        });


        mRecyclerView_BookItems.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);


                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView_BookItems.getLayoutManager();


                /** Set last visible position in recylerView. */
                int lastPos =  linearLayoutManager.findLastVisibleItemPosition();

                Log.v(TAG, "lastPos = " + Integer.toString(lastPos));


                /** If last visible item is a last in overall array of items
                 *  than show {@link mImageButton_LeftDecPageNum},
                 * {@link mImageButton_RightIncPageNum}. */
                if ( lastPos == (getBookItems().size() - 1 ))
                {
                    mImageButton_LeftDecPageNum.setVisibility(View.VISIBLE);
                    mImageButton_RightIncPageNum.setVisibility(View.VISIBLE);
                }
                /**  Else hide them {@link mImageButton_LeftDecPageNum},
                 * {@link mImageButton_RightIncPageNum}. */
                else
                {
                    mImageButton_LeftDecPageNum.setVisibility(View.GONE);
                    mImageButton_RightIncPageNum.setVisibility(View.GONE);
                }
            }
        });





    }







}
