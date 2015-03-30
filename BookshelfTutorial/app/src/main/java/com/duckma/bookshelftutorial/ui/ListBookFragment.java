package com.duckma.bookshelftutorial.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.duckma.bookshelftutorial.MainActivity;
import com.duckma.bookshelftutorial.R;
import com.duckma.bookshelftutorial.adapter.BookListAdapter;
import com.duckma.bookshelftutorial.database.BookDatabaseHandler;
import com.duckma.bookshelftutorial.model.BookModel;

import java.util.ArrayList;

/**
 * Copyright © 2015 DuckMa S.r.l. - http://duckma.com
 * <p/>
 * Created by Matteo Gazzurelli on 17/11/14.
 * <p/>
 * Fragments: http://developer.android.com/guide/components/fragments.html
 * ListView: http://developer.android.com/guide/topics/ui/layout/listview.html
 */
public class ListBookFragment extends Fragment {

    public static final String TAG = ListBookFragment.class.getSimpleName();

    ListView mLvBooks;
    BookListAdapter mAdapter;
    Button mBtAddBook;
    BookDatabaseHandler mBookDatabaseHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_book, container, false);
        setHasOptionsMenu(true);
        mBookDatabaseHandler = new BookDatabaseHandler(getActivity());
        //Creazione di BaseAdapter da associare a listView
        if (savedInstanceState != null) {
            //creazione mAdapter riprendendo i dati salvati onSaveInstanceState
            mAdapter = new BookListAdapter(getActivity(), (ArrayList) savedInstanceState.getParcelableArrayList("books"));
        } else {
            //creazione mAdapter con valori iniziali
            ArrayList<BookModel> bookModels = mBookDatabaseHandler.getAllBooks();
            bookModels.add(new BookModel("Harry Potter", "J. K. Rowling", "Adriano Salani Editore", "Fantasy", "http://www.jkrowling.com/"));
            bookModels.add(new BookModel("Il Signore degli Anelli", " John Ronald Reuel Tolkien", "Bompiani", "Fantasy", "http://www.tolkien.co.uk/index.html"));
            mAdapter = new BookListAdapter(getActivity().getBaseContext(), bookModels);
        }

        mBtAddBook = (Button) view.findViewById(R.id.addBooklList);
        mBtAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gestione evento click button quando si vuole aggiungere un libro
                Intent i = new Intent(getActivity(), BookAddActivity.class);
                startActivityForResult(i, 0);
            }
        });

        //identificazione della listView nel activity_main.xml attraverso ID univoco assegnato
        mLvBooks = (ListView) view.findViewById(R.id.booksList);
        mLvBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((MainActivity) getActivity()).onClicItemSelect((BookModel) mAdapter.getItem(i));
            }
        });
        //assegnazione mAdapter alla listView
        mLvBooks.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //salvataggio oggetti BookModel quando si esce dall'activity
        outState.putParcelableArrayList("books", mAdapter.getArrayBook());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult");
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                BookModel bookModel = data.getParcelableExtra("book");
                mAdapter.addBookModel(bookModel);
                mAdapter.notifyDataSetChanged();
                mBookDatabaseHandler.addBook(bookModel);
                Toast.makeText(getActivity(), "Book Saved", Toast.LENGTH_LONG).show();
                Log.d(TAG, bookModel.title);
            }
        }
    }

}
