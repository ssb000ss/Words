package com.gmail.ssb000ss.words.db;



import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gmail.ssb000ss.words.objects.Word;

/**
 * Created by ssb000ss on 13.06.2017.
 */

public class DBWords {

    private SQLiteDatabase mDb;
    private DBWordsHelper helper;
    private Context context;

    public DBWords(SQLiteDatabase mDb, DBWordsHelper helper, Context context) {
        this.mDb = mDb;
        this.helper = helper;
        this.context = context;
    }

    public boolean addWord(Word word){
        ContentValues cv=new ContentValues();
        cv.put(DBWordsContract.DBWordEntry.COLUMN_WORD,word.getWord());
        cv.put(DBWordsContract.DBWordEntry.COLUMN_TRANSLATION,word.getTranslation());
        return mDb.insert(DBWordsContract.DBWordEntry.TABLE_NAME,null,cv)>0;
    }

    public boolean deleteWord(Word word){

    }





}
