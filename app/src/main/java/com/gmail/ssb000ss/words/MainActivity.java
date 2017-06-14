package com.gmail.ssb000ss.words;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.gmail.ssb000ss.words.adapters.WordAdapter;
import com.gmail.ssb000ss.words.db.DBWords;
import com.gmail.ssb000ss.words.db.DBWordsHelper;
import com.gmail.ssb000ss.words.db.TestUtils;
import com.gmail.ssb000ss.words.objects.Word;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    WordAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private SQLiteDatabase mDb;
    private DBWordsHelper helper;
    private Context context;
    DBWords dbWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;
        helper=new DBWordsHelper(this);
        mDb=helper.getWritableDatabase();
        dbWords=new DBWords(mDb,helper,context);

        //TestUtils.insertTestWord(mDb);
        adapter=new WordAdapter(dbWords.getList(),this);

        recyclerView=(RecyclerView)findViewById(R.id.rv_word);

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

       fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbWords.addWord(new Word("sixth","шестой"));
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }

}
