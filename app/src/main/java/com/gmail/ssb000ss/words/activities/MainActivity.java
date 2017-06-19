package com.gmail.ssb000ss.words.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.gmail.ssb000ss.words.R;
import com.gmail.ssb000ss.words.adapters.WordDbAdapter;
import com.gmail.ssb000ss.words.db.DBWords;
import com.gmail.ssb000ss.words.db.DBWordsHelper;
import com.gmail.ssb000ss.words.db.TestUtils;
import com.gmail.ssb000ss.words.objects.Word;

import static com.gmail.ssb000ss.words.activities.DialogActivity.DB_CODE_RESULT_ADD_TRANSLATE;
import static com.gmail.ssb000ss.words.activities.DialogActivity.DB_CODE_RESULT_ADD_WORD;

public class MainActivity extends AppCompatActivity {

    private static final int DB_CODE_ADD_WORD =  154;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FloatingActionButton fab;
    //адаптер для работы с rv с помощью курсора
    WordDbAdapter adapter;

    //объект для создания и получения бд
    DBWordsHelper helper;
    //бд
    SQLiteDatabase mDb;
    //дополнительный объект для работы с бд
    DBWords dbWords;

    Cursor cursor;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data==null||resultCode==RESULT_CANCELED)return;
        else if(resultCode==RESULT_OK){
            String word=data.getStringExtra(DB_CODE_RESULT_ADD_WORD);
            String translate=data.getStringExtra(DB_CODE_RESULT_ADD_TRANSLATE);
            dbWords.addWord(new Word(word,translate));
            adapter.swapCursor(dbWords.getAll());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        helper=new DBWordsHelper(this);
        mDb=helper.getWritableDatabase();


        dbWords=new DBWords(mDb,this);


        cursor=dbWords.getAll();

        Word word=dbWords.getWordById(21);
        System.out.println(word.getWord());

        //// TODO: 14.06.2017 надо проверить  есть ли хотя бы одна запись если нет то закинуть Тест данные 
       //TestUtils.insertTestWord(mDb);


        adapter=new WordDbAdapter(cursor,this);

        recyclerView=(RecyclerView)findViewById(R.id.rv_word);

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,DialogActivity.class);
                startActivityForResult(intent,DB_CODE_ADD_WORD);

                adapter.swapCursor(dbWords.getAll());
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();
                if (dbWords.deleteWord(id)) {
                }
                adapter.swapCursor(dbWords.getAll());
            }
        }).attachToRecyclerView(recyclerView);
    }

}
