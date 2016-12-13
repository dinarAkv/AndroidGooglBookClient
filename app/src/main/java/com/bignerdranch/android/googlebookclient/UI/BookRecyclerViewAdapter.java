package com.bignerdranch.android.googlebookclient.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.googlebookclient.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView ddapter for book items.
 */

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {


    private ArrayList<BookViewItem> mBookViewItems;
    private Context mContext;

    /** View object for bindings. */
    private View mView;


    public BookRecyclerViewAdapter(ArrayList<BookViewItem> mBookViewItems, Context mContext) {
        this.mBookViewItems = mBookViewItems;
        this.mContext = mContext;
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewTitleOfBook)
        TextView mTextView_Title;

        @BindView(R.id.textViewAuthorsOfBook)
        TextView mTextView_Authors;

        @BindView(R.id.textViewPublishYearOfBook)
        TextView mTextView_Year;

        @BindView(R.id.textViewDiscriptionOfBook)
        TextView mTextView_Discription;

        @BindView(R.id.imageViewBookPic)
        ImageView mImageView_Pic;


        public ViewHolder(View itemView) {
            super(itemView);

            /** Bind view objects. */
            ButterKnife.bind(this, itemView);

            /** Save view object. */
            mView = itemView;

        }
    }






    @Override
    public int getItemCount() {
        return mBookViewItems.size();
    }

    @Override
    public BookRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.book_item, parent, false);


        return new BookRecyclerViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookRecyclerViewAdapter.ViewHolder holder, int position) {

        BookViewItem bookViewItem = mBookViewItems.get(position);

        holder.mTextView_Title.setText(bookViewItem.getmTitle());
        holder.mTextView_Authors.setText(bookViewItem.getmAuthors());
        holder.mTextView_Year.setText(bookViewItem.getmYear());
        holder.mTextView_Discription.setText(bookViewItem.getmDiscription());



        Picasso.with(mView.getContext()).load(bookViewItem.getmUrl())
                .fit()
                .into(holder.mImageView_Pic);

    }


    /***
     * Get list of data of book items
     * @return - list of book items.
     */
    public ArrayList<BookViewItem> getBookDataList()
    {
        return mBookViewItems;
    }

    /**
     * Function set new book data items.
     * @param bookDataList - new book data items.
     */
    public void setBookDataList(ArrayList<BookViewItem> bookDataList)
    {
        mBookViewItems = bookDataList;
    }









}
