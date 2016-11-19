package com.android.learnit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sarthakalang on 14/11/15.
 */
public class NewWordsDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;


    static final String DATABASE_NAME = "n_weather.db";

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_WORD_TABLE = "CREATE TABLE " + WordsContract.NewWords.Table_name + " (" +
                WordsContract.NewWords.Column_Id + " INTEGER PRIMARY KEY," +
                WordsContract.NewWords.Column_Setname + " TEXT," +
                WordsContract.NewWords.Column_Word + " TEXT NOT NULL," +
                WordsContract.NewWords.Column_Description + " TEXT " +
                ");";
        db.execSQL(SQL_CREATE_WORD_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WordsContract.NewWords.Table_name);
    }

    public NewWordsDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insert(NewWordsDB n,String setname,String value,String desc){
        SQLiteDatabase sq=n.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(WordsContract.NewWords.Column_Setname,setname);
        cv.put(WordsContract.NewWords.Column_Word,value);
        cv.put(WordsContract.NewWords.Column_Description,desc);
        long k=sq.insert(WordsContract.NewWords.Table_name,null,cv);
        Log.d("abc", "one word added");
    }
    public Cursor getset(){
        SQLiteDatabase db=this.getReadableDatabase();
        String buildSQL = "SELECT DISTINCT "+ WordsContract.NewWords.Column_Setname+" FROM " + WordsContract.NewWords.Table_name;


        return db.rawQuery(buildSQL, null);

    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getReadableDatabase();
        String buildSQL = "SELECT "+ WordsContract.NewWords.Column_Id+" as _id,"+ WordsContract.NewWords.Column_Setname+"," + WordsContract.NewWords.Column_Word+","+ WordsContract.NewWords.Column_Description+" FROM " + WordsContract.NewWords.Table_name;


        return db.rawQuery(buildSQL, null);

    }

    public void clear(SQLiteDatabase database){

        database.execSQL("DELETE FROM " + WordsContract.NewWords.Table_name);
    }

}
