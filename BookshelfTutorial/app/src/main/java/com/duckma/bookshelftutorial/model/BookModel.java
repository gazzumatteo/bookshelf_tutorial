package com.duckma.bookshelftutorial.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright © 2015 DuckMa S.r.l. - http://duckma.com
 *
 * Created by Matteo Gazzurelli on 13/11/14.
 *
 * Res:
 * - http://www.javapractices.com/topic/TopicAction.do?Id=187
 * - http://www.step-10.com/SoftwareDesign/General/ObjectModeling.html
 */
public class BookModel implements Parcelable{

    //Modello base per la descrizione di un libro
    public String title;
    public String author;
    public String publisher;
    public String category;
    public String url;

    public BookModel (String title, String author, String publisher, String category, String url){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //scriviamo ogni stringa nel Parcel.
        //Quando leggiamo il parcel andiamo a prelevare i dati nello stesso ordine
        parcel.writeString(title);
        parcel.writeString(author);
        parcel.writeString(publisher);
        parcel.writeString(category);
        parcel.writeString(url);

    }

    /*
    * Questo campo è importante per android affinchè possa
    * creare un nuovo oggetto individualmente o come un array di oggetti
    *
    * Questo vuol dire ch è possibile utilizzare il costrutto di
    * default per creare oggetti.
    */
    public static final Parcelable.Creator<BookModel> CREATOR
            = new Parcelable.Creator<BookModel>() {
        public BookModel createFromParcel(Parcel in) {
            return new BookModel(in);
        }

        public BookModel[] newArray(int size) {
            return new BookModel[size];
        }
    };

    //Costrutto utilizzato quando viene ricostruito l'oggetto da un parcel
    private BookModel(Parcel in) {
        //leggiamo i valori nello stesso ordine in cui sono stati scritti
        this.title = in.readString();
        this.author = in.readString();
        this.publisher = in.readString();
        this.category = in.readString();
        this.url = in.readString();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
