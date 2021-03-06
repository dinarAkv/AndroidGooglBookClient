package com.bignerdranch.android.googlebookclient.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.android.googlebookclient.Helper.ViewSize;
import com.bignerdranch.android.googlebookclient.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView ddapter for book items.
 */

public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {


    private static final String TAG = "BookRecView";

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

        @BindView(R.id.linearLayoutBookPic)
        LinearLayout mLinearLayout_BookPic;


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

        /** Set image for every book item. Image load from internet or set default picture. */
        setImageForBookItem(bookViewItem, holder);

        /** Set height of layout for book image of every item depended from device screen width.  */
        adaptivelyChangeLayoutHeight(holder);

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


    /**
     * Function on base of width of screen get respect mean of height.
     * @return - mean of height for linear layout container for book picture.
     */
    public int getAdaptiveHeightOfLinearLayout()
    {
        Log.d(TAG, "getAdaptiveHeightOfLinearLayout");

        float width = ViewSize.getWidthOfDevice(mView.getContext());
        /** Factor for convert width of screen to respect height
         * of linear layout for book picture. */
        float factor = 1.2f;




        return (int) (width / factor);
    }


    /**
     * Function set image for every book item
     * @param bookViewItem - book object, contain all information of this item of book.
     * @param holder - holder with views. It need to manipulate with view objects of this view item.
     */
    public void setImageForBookItem(BookViewItem bookViewItem,
                                    BookRecyclerViewAdapter.ViewHolder holder)
    {

        /** If URL field contain link than load picture from this link. */
        if (bookViewItem.getmUrl() != null)
        {
            Picasso.with(mView.getContext()).load(bookViewItem.getmUrl())
                    .fit()
                    .into(holder.mImageView_Pic);
        }
        /** Else set default book picture. */
        else
        {
            holder.mImageView_Pic.setImageResource(R.drawable.default_book_pic);
        }

    }


    /**
     * Function adaptively change height of layout for book picture.
     * Height depend from width of device screen.
     * @param holder - holder with views. It need to manipulate with view objects of this view item.
     */
    public void adaptivelyChangeLayoutHeight(BookRecyclerViewAdapter.ViewHolder holder)
    {
        /** Set height of linearLayout for book picture depending from device screen width. */
        ViewGroup.LayoutParams params = holder.mLinearLayout_BookPic.getLayoutParams();
        params.height = getAdaptiveHeightOfLinearLayout();
        holder.mLinearLayout_BookPic.setLayoutParams(params);
    }



}
