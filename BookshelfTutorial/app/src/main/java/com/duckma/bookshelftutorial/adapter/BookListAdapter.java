package com.duckma.bookshelftutorial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duckma.bookshelftutorial.R;
import com.duckma.bookshelftutorial.model.BookModel;

import java.util.ArrayList;

/**
 * Copyright © 2015 DuckMa S.r.l. - http://duckma.com
 *
 * Created by Matteo Gazzurelli on 13/11/14.
 *
 * ListView Adapter http://developer.android.com/guide/topics/ui/layout/listview.html
 * ViewHolder Pattern http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 */
public class BookListAdapter extends BaseAdapter {
    private ArrayList<BookModel> listBooks = new ArrayList<BookModel>();
    private Context mContext;

    public BookListAdapter(Context context, ArrayList<BookModel> bookModels) {
        mContext = context;
        listBooks = bookModels;
    }

    @Override
    public int getCount() {
        return listBooks.size();
    }

    public ArrayList<BookModel> getArrayBook() {
        return listBooks;
    }


    @Override
    public Object getItem(int position) {
        return listBooks.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_book, parent, false);
            holder = new ViewHolder();
            holder.tvItemTitle = (TextView) view.findViewById(R.id.tvItemTitle);
            holder.tvItemAuthor = (TextView) view.findViewById(R.id.tvItemAuthor);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.tvItemTitle.setText(listBooks.get(position).getTitle());
        holder.tvItemAuthor.setText(listBooks.get(position).getAuthor());
        return view;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void addBookModel(BookModel bookModel) {
        listBooks.add(bookModel);
    }

    private class ViewHolder {
        TextView tvItemTitle;
        TextView tvItemAuthor;
    }

}
