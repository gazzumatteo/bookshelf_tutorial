package com.duckma.bookshelftutorial.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.duckma.bookshelftutorial.R;
import com.duckma.bookshelftutorial.model.BookModel;

/**
 * Copyright © 2015 DuckMa S.r.l. - http://duckma.com
 *
 * Created by Matteo Gazzurelli on 14/11/14.
 *
 * Activity: http://developer.android.com/guide/components/activities.html
 */
public class BookAddActivity extends Activity {
    EditText mEtTitleBook;
    EditText mEtAuthorBook;
    EditText mEtPublisherBook;
    EditText mEtCategoryBook;
    EditText mEtUrlBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add);
        mEtTitleBook = (EditText) findViewById(R.id.etTittleBook);
        mEtAuthorBook = (EditText) findViewById(R.id.etAuthorBook);
        mEtPublisherBook = (EditText) findViewById(R.id.etPublisherBook);
        mEtCategoryBook = (EditText) findViewById(R.id.etCategoryBook);
        mEtUrlBook = (EditText) findViewById(R.id.etUrlBook);
    }

    public void onClickAddBook(View view) {
        //AlertDialog che permette all'utente di decidere se salvare i dati o modifcare i dati inseriti
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_create_book)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (checkData()) {
                            getNotification();
                            //Creazione BookModel
                            BookModel bookModel = new BookModel(mEtTitleBook.getText().toString(), mEtAuthorBook.getText().toString(),
                                    mEtPublisherBook.getText().toString(), mEtCategoryBook.getText().toString(), mEtUrlBook.getText().toString());
                            Intent intent = new Intent();
                            intent.putExtra("book", bookModel);
                            setResult(RESULT_OK, intent);
                            //Terminazione Activity
                            finish();
                        }
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //L'azione non viene gestita dato che non c'è ne bisogno
                    }
                });
        builder.show();
    }

    public void getNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder notification = new Notification.Builder(getBaseContext());
        notification.setContentTitle(mEtTitleBook.getText().toString());
        notification.setContentText(mEtAuthorBook.getText().toString());
        notification.setSmallIcon(R.drawable.ic_launcher);
        notification.setAutoCancel(true);
        notification.getNotification().flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification.build());
    }

    public boolean checkData() {
        if (mEtTitleBook.getText().toString().isEmpty() ||
                mEtAuthorBook.getText().toString().isEmpty() ||
                mEtCategoryBook.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "The field is empty", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}
