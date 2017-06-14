package com.gmail.ssb000ss.words.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.ssb000ss.words.R;
import com.gmail.ssb000ss.words.db.DBWordsContract;

/**
 * Created by ssb000ss on 14.06.2017.
 */

public class WordDbAdapter extends RecyclerView.Adapter<WordDbAdapter.WordDbViewHolder>{

    Cursor cursor;
    Context wContext;

    public WordDbAdapter(Cursor wCursor, Context wContext) {
        this.cursor = wCursor;
        this.wContext = wContext;
    }

    @Override
    public WordDbViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(wContext)
                .inflate(R.layout.word_item, parent, false);
        return new WordDbAdapter.WordDbViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WordDbViewHolder holder, int position) {

        if (!cursor.moveToPosition(position))return;

        long id= cursor.getLong(cursor.getColumnIndex(DBWordsContract.DBWordEntry._ID));
        String word=cursor.getString(cursor.getColumnIndex(DBWordsContract.DBWordEntry.COLUMN_WORD));
        String translation=cursor.getString(cursor.getColumnIndex(DBWordsContract.DBWordEntry.COLUMN_TRANSLATION));

        holder.tv_id.setText(String.valueOf(id));
        holder.tv_word.setText(word);
        holder.tv_translation.setText(translation);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) cursor.close();
        cursor = newCursor;
        if (newCursor != null) {
            this.notifyDataSetChanged();
        }
    }

    public class WordDbViewHolder extends RecyclerView.ViewHolder {

        TextView tv_word;
        TextView tv_translation;
        TextView tv_id;

        public WordDbViewHolder(View itemView) {
            super(itemView);

            tv_id = (TextView) itemView.findViewById(R.id.tv_word_id);
            tv_word = (TextView) itemView.findViewById(R.id.tv_word_word);
            tv_translation = (TextView) itemView.findViewById(R.id.tv_word_translation);
        }
    }

}
