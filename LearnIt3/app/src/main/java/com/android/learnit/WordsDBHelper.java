package com.android.learnit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sarthakalang on 15/09/15.
 */
public class WordsDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;


    static final String DATABASE_NAME = "weather.db";

    public WordsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WORD_TABLE = "CREATE TABLE " + WordsContract.WordEntry.Table_name + "(" +
                WordsContract.WordEntry.Column_Id + " INTEGER PRIMARY KEY," +
                WordsContract.WordEntry.Column_Setname + " TEXT," +
                WordsContract.WordEntry.Column_Word + " TEXT NOT NULL," +
                WordsContract.WordEntry.Column_Description + " TEXT," +
                WordsContract.WordEntry.Column_Check + " TEXT );";
        db.execSQL(SQL_CREATE_WORD_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WordsContract.WordEntry.Table_name);
    }
    public void insert(WordsDBHelper w,String value,String desc){
        SQLiteDatabase sq=w.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(WordsContract.WordEntry.Column_Word,value);
        cv.put(WordsContract.WordEntry.Column_Description,desc);
        long k=sq.insert(WordsContract.WordEntry.Table_name,null,cv);
        Log.d("abc", "one word added");
    }
    public void insert2(WordsDBHelper w,String setname,String value,String desc,String check){
        SQLiteDatabase sq=w.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(WordsContract.WordEntry.Column_Setname,setname);
        cv.put(WordsContract.WordEntry.Column_Word,value);
        cv.put(WordsContract.WordEntry.Column_Description,desc);
        cv.put(WordsContract.WordEntry.Column_Check,check);
        long k=sq.insert(WordsContract.WordEntry.Table_name,null,cv);
        Log.d("abc", "one word added");
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getReadableDatabase();
        String buildSQL = "SELECT "+ WordsContract.WordEntry.Column_Id+" as _id,"+ WordsContract.WordEntry.Column_Setname+"," + WordsContract.WordEntry.Column_Word+","+ WordsContract.WordEntry.Column_Description+" FROM " + WordsContract.WordEntry.Table_name;


        return db.rawQuery(buildSQL, null);

    }

    public Cursor getsetData(String set,String check){
        SQLiteDatabase db=this.getReadableDatabase();
        String buildSQL = "SELECT "+ WordsContract.WordEntry.Column_Id+" as _id,"+ WordsContract.WordEntry.Column_Setname+"," + WordsContract.WordEntry.Column_Word+","+ WordsContract.WordEntry.Column_Description+" FROM " + WordsContract.WordEntry.Table_name+" WHERE "+WordsContract.WordEntry.Column_Setname+" LIKE '"+set+"' AND "+WordsContract.WordEntry.Column_Check+" LIKE '"+check+"'";


        return db.rawQuery(buildSQL, null);

    }
    public Cursor getset(String check){
        SQLiteDatabase db=this.getReadableDatabase();
        String buildSQL = "SELECT DISTINCT "+ WordsContract.WordEntry.Column_Setname+" FROM " + WordsContract.WordEntry.Table_name+" WHERE "+WordsContract.WordEntry.Column_Check+" LIKE '"+check+"'";


        return db.rawQuery(buildSQL, null);

    }
    public void remove(String set,String word,SQLiteDatabase database){

        database.execSQL("DELETE FROM "+WordsContract.WordEntry.Table_name+" WHERE "+WordsContract.WordEntry.Column_Setname+" LIKE '"+set+"'" +" AND "+WordsContract.WordEntry.Column_Word+" LIKE '"+word+"' AND "+WordsContract.WordEntry.Column_Check+" LIKE 'nd'");
    }


    public void removeset(String set,SQLiteDatabase database){

        database.execSQL("DELETE FROM "+WordsContract.WordEntry.Table_name+" WHERE "+WordsContract.WordEntry.Column_Setname+" LIKE '"+set+"' AND "+WordsContract.WordEntry.Column_Check+" LIKE 'nd'");
    }


    public Cursor getwords(){
        SQLiteDatabase db=this.getReadableDatabase();
        String buildSQL = "SELECT "+WordsContract.WordEntry.Column_Word+" FROM " + WordsContract.WordEntry.Table_name;


        return db.rawQuery(buildSQL, null);

    }
    public Cursor getdefinition(){
        SQLiteDatabase db=this.getReadableDatabase();
        String buildSQL = "SELECT "+WordsContract.WordEntry.Column_Description+" FROM " + WordsContract.WordEntry.Table_name;


        return db.rawQuery(buildSQL, null);
    }
}
