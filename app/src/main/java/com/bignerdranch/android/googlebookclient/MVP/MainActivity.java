package com.bignerdranch.android.googlebookclient.MVP;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Bind view objects. */
        ButterKnife.bind(this);

        /** Initialize presenter. */
        mMainPresenter = new MainPresenterImpl(this);



        /** Initialize listeners. */
        initListeners();




    }


    /***
     * Get user typed search request.
     * @return - request implementation.
     */
    @Override
    public String getSearchRequest() {
        return mEditText_BookSearch.getText().toString();
    }


    @Override
    public void textViewSetText(String text) {
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



    }







}
