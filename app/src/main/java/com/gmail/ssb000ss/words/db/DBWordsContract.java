package com.gmail.ssb000ss.words.db;

import android.provider.BaseColumns;

/**
 * Created by ssb000ss on 13.06.2017.
 */

public class DBWordsContract {
    public static final class DBWordEntry implements BaseColumns {
        public static final String TABLE_NAME="dictionary";
        public static final String COLUMN_WORD="words";
        public static final String COLUMN_TRANSLATION="translation";
    }

}
