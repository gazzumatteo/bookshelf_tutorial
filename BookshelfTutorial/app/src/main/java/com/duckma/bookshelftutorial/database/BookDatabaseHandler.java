package com.duckma.bookshelftutorial.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.duckma.bookshelftutorial.model.BookModel;

import java.util.ArrayList;

/**
 * Copyright © 2015 DuckMa S.r.l. - http://duckma.com
 *
 * Created by Matteo Gazzurelli on 18/11/14.
 *
 * Database: http://developer.android.com/training/basics/data-storage/databases.html
 */
public class BookDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bookManager";
    private static final String TABLE_BOOK = "books";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_PUBLISHER = "publisher";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_URL = "url";

    public BookDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_BOOK + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_TITLE + " TEXT,"
                + KEY_AUTHOR + " TEXT,"
                + KEY_PUBLISHER + " TEXT,"
                + KEY_CATEGORY + " TEXT,"
                + KEY_URL + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        //cancellazione vecchio database
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);

        // Creazione nuovo database
        onCreate(sqLiteDatabase);
    }

    // metodo per aggiungere un nuovo libro
    public void addBook(BookModel bookModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, bookModel.getTitle());
        values.put(KEY_AUTHOR, bookModel.getAuthor());
        values.put(KEY_PUBLISHER, bookModel.getPublisher());
        values.put(KEY_CATEGORY, bookModel.getCategory());
        values.put(KEY_URL, bookModel.getUrl());
        // Inserimento riga
        db.insert(TABLE_BOOK, null, values);
        //il secondo argomento coniene la stringa nullColumnHack
        db.close(); // chiusura connessione database
    }

    // Metodo per prelevare una lista di libri
    public ArrayList<BookModel> getAllBooks() {
        ArrayList<BookModel> bookList = new ArrayList<BookModel>();
        // Selziono tutte le query
        String selectQuery = "SELECT  * FROM " + TABLE_BOOK;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // scorrendo tutte le righe e aggiungendole alla lista di libri
        if (cursor.moveToFirst()) {
            do {
                BookModel bookModel = new BookModel(cursor.getString(1), cursor.getString(2),
                        cursor.getString(3),cursor.getString(4),cursor.getString(5));
                // Aggiunta di libro alla lista
                bookList.add(bookModel);
            } while (cursor.moveToNext());
        }
        // ritorna la lista di libri
        return bookList;
    }
}
