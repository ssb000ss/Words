package com.gmail.ssb000ss.words.objects;

/**
 * Created by ssb000ss on 13.06.2017.
 */

public class Word {
    private long id;
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public Word(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    private String translation;

    public Word(long id) {
        this.id = id;
    }

    public Word(long id, String word) {
        this.id = id;
        this.word = word;
    }

    public Word(long id, String word, String translation) {
        this.id = id;
        this.word = word;
        this.translation = translation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
