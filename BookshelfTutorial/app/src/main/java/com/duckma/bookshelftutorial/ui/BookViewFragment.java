package com.duckma.bookshelftutorial.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.duckma.bookshelftutorial.MainActivity;
import com.duckma.bookshelftutorial.R;
import com.duckma.bookshelftutorial.model.BookModel;

/**
 * Copyright © 2015 DuckMa S.r.l. - http://duckma.com
 * <p/>
 * Created by Matteo Gazzurelli on 17/11/14.
 * <p/>
 * Fragments: http://developer.android.com/guide/components/fragments.html
 */
public class BookViewFragment extends Fragment {
    public static final String TAG = BookViewFragment.class.getSimpleName();

    TextView mTvTitleView;
    TextView mTvAuthorView;
    TextView mTvPublisherView;
    TextView mTvCategoryView;
    Button mBtnUrlView;

    //questo metodo viene chiamato quando il fragment deve disegnare per la prima volta la sua UI
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate per referziare il layout alla seguente UI
        View view = inflater.inflate(R.layout.fragment_book_view, container, false);
        mTvTitleView = (TextView) view.findViewById(R.id.tvTitleView);
        mTvAuthorView = (TextView) view.findViewById(R.id.tvAuthorView);
        mTvPublisherView = (TextView) view.findViewById(R.id.tvPublisherView);
        mTvCategoryView = (TextView) view.findViewById(R.id.tvCategoryView);
        mBtnUrlView = (Button) view.findViewById(R.id.btUrlView);
        mBtnUrlView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).onClickWebViewBook(mBtnUrlView.getText().toString());
            }
        });
        return view;
    }

    public void refreshView(BookModel bookModel) {
        Log.d(TAG, "the title book is" + bookModel.title);
        mTvTitleView.setText(bookModel.getTitle());
        mTvAuthorView.setText(bookModel.getAuthor());
        mTvPublisherView.setText(bookModel.getPublisher());
        mTvCategoryView.setText(bookModel.getCategory());
        mBtnUrlView.setText(bookModel.getUrl());
    }

}
