package com.gmail.ssb000ss.words.db;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gmail.ssb000ss.words.objects.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssb000ss on 13.06.2017.
 */

public class DBWords {

    private SQLiteDatabase mDb;
    private DBWordsHelper helper;
    private Context context;
    Cursor cursor;

    public DBWords(SQLiteDatabase mDb, DBWordsHelper helper, Context context) {
        this.mDb = mDb;
        this.helper = helper;
        this.context = context;
        this.cursor=getAll();
    }

    public boolean addWord(Word word){
        ContentValues cv=new ContentValues();
        cv.put(DBWordsContract.DBWordEntry.COLUMN_WORD,word.getWord());
        cv.put(DBWordsContract.DBWordEntry.COLUMN_TRANSLATION,word.getTranslation());
        return mDb.insert(DBWordsContract.DBWordEntry.TABLE_NAME,null,cv)>0;
    }

    public boolean deleteWord(Word word){
        return mDb.delete(DBWordsContract.DBWordEntry.TABLE_NAME, DBWordsContract.DBWordEntry._ID+"="+word.getId(),null)>0;
    }

    public Cursor getAll(){
        return mDb.query(DBWordsContract.DBWordEntry.TABLE_NAME
                ,null
                ,null
                ,null
                ,null
                ,null
                , DBWordsContract.DBWordEntry._ID
                );
    }

    public Word getWordById(long byID){
        return null;
    }

    public Word getWordByWord(String word){
        return null;
    }

    public Word getWordByTranslation(String translation){

        return null;
    }

    public List<Word> getList(){
        List<Word> list=new ArrayList<>();
        cursor.moveToPosition(0);
        for (int i = 0; i <cursor.getCount() ; i++) {
            addTestWord(list);
        }
        return list;
        }

    private void addTestWord(List<Word> list) {
        long id = cursor.getLong(cursor.getColumnIndex(DBWordsContract.DBWordEntry._ID));
        String word = cursor.getString(cursor.getColumnIndex(DBWordsContract.DBWordEntry.COLUMN_WORD));
        String translate = cursor.getString(cursor.getColumnIndex(DBWordsContract.DBWordEntry.COLUMN_TRANSLATION));
        list.add(new Word(id, word, translate));
        cursor.moveToNext();
    }


}
