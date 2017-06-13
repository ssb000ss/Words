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

public class MainActivity extends AppCompatActivity {


//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        mRecyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        // specify an adapter (see also next example)
//        mAdapter = new MyAdapter(myDataset);
//        mRecyclerView.setAdapter(mAdapter);

    RecyclerView recyclerView;
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


        helper=new DBWordsHelper(this);

        mDb=helper.getWritableDatabase();

        context=this;
        dbWords=new DBWords(mDb,helper,context);

        TestUtils.insertTestWord(mDb);
        adapter=new WordAdapter(dbWords.getList());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView=(RecyclerView)findViewById(R.id.rv_word);
        setSupportActionBar(toolbar);


        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
