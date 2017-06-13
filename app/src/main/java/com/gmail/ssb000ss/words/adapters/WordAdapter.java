package com.gmail.ssb000ss.words.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.ssb000ss.words.R;
import com.gmail.ssb000ss.words.objects.Word;

import java.util.List;

/**
 * Created by gENERATION on 13.06.2017.
 */

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    List<Word> list;

    public WordAdapter(List<Word> list) {
        this.list = list;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.word_item, parent, false);

        WordViewHolder vh = new WordViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Word word=list.get(position);
        holder.tv_id.setText(String.valueOf(word.getId()));
        holder.tv_word.setText(word.getWord());
        holder.tv_translation.setText(word.getTranslation());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_word;
        TextView tv_translation;
        TextView tv_id;
        public WordViewHolder(View itemView) {
            super(itemView);

            tv_id=(TextView) itemView.findViewById(R.id.tv_word_id);
            tv_word=(TextView) itemView.findViewById(R.id.tv_word_word);
            tv_translation=(TextView) itemView.findViewById(R.id.tv_word_translation);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
