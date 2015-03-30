package com.duckma.bookshelftutorial;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.duckma.bookshelftutorial.model.BookModel;
import com.duckma.bookshelftutorial.ui.BookViewFragment;
import com.duckma.bookshelftutorial.ui.ListBookFragment;
import com.duckma.bookshelftutorial.ui.WebViewFragment;

/**
 * Copyright © 2015 DuckMa S.r.l. - http://duckma.com
 * <p/>
 * Created by Matteo Gazzurelli on 12/11/14.
 *
 * Fragments: http://developer.android.com/guide/components/fragments.html
 *
 */
public class MainActivity extends Activity {

    ListBookFragment mListBook;
    BookViewFragment mBookView;
    WebViewFragment mWebViewFragment;
    /*
    * Quando si accede alla seguente classe ci sono metodi che vengono
    * invocati perchè fanno parte del ciclo di vita dell'Activity
    * Ci sono tre metodi che vengono chiamati quando si accede ad una Activity e sono:
    * onCreate(), onStart() e onResume()
    * */

    //Questo metodo deve essere sempre presente quando si estende una classe con Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            //Creazione di Fragment incarcati di mostrare la lista libri e le caratteristiche del libro
            mListBook = new ListBookFragment();
            mBookView = new BookViewFragment();
            mWebViewFragment = new WebViewFragment();
            //istanza di FragmentTrasaction presa dalla seguente activity
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            //Aggiunta del fragment alla view passata attraverso ID
            fragmentTransaction.add(R.id.viewListBooks, mBookView, BookViewFragment.class.getName()).hide(mBookView);
            fragmentTransaction.add(R.id.viewListBooks, mWebViewFragment, WebViewFragment.class.getName()).hide(mWebViewFragment);
            fragmentTransaction.add(R.id.viewListBooks, mListBook, ListBookFragment.class.getName());
            //Per aggiungere il fragment
            fragmentTransaction.commit();
        } else {
            //vengono prelevati i vecchi dati in caso essi siano già stati creati
            mListBook = (ListBookFragment) getFragmentManager().findFragmentByTag(ListBookFragment.class.getName());
            mWebViewFragment = (WebViewFragment) getFragmentManager().findFragmentByTag(WebViewFragment.class.getName());
            mBookView = (BookViewFragment) getFragmentManager().findFragmentByTag(BookViewFragment.class.getName());
            getFragmentManager().beginTransaction().hide(mBookView).hide(mWebViewFragment).show(mListBook).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuEsci) {
            finish();
            return true;
        }
        return false;
    }

    //Il metodo viene chiamato da ListBookFragment quando unn'elemento viene selezionato
    public void onClicItemSelect(BookModel bookModel) {
        if (mBookView.isVisible()) {
            return;
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.hide(mWebViewFragment);
        ft.hide(mListBook);
        //chiamo il metodo per caricare i dati
        mBookView.refreshView(bookModel);
        ft.show(mBookView);
        ft.commit();
    }

    //Il metodo viene chiamato da BookViewFragment quando l'utente fa click su button back
    public void onClickBackFragment(View view) {
        if (mListBook.isVisible()) {
            return;
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.hide(mBookView);
        ft.hide(mWebViewFragment);
        ft.show(mListBook);
        ft.commit();
    }

    public void onClickWebViewBook(String url) {
        if (mWebViewFragment.isVisible()) {
            return;
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.hide(mBookView);
        ft.hide(mListBook);
        mWebViewFragment.loadUrl(url);
        ft.show(mWebViewFragment);
        ft.commit();
    }


    //Il metodo viene chiamato da WebViewFragment quando l'utente fa click su button back
    public void onClickBackWebViewFragment(View view) {
        if (mBookView.isVisible()) {
            return;
        }
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.hide(mListBook);
        mWebViewFragment.clearWebView();
        ft.hide(mWebViewFragment);
        ft.show(mBookView);
        ft.commit();
    }

}
