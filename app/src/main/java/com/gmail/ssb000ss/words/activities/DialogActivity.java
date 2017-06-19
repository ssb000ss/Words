package com.gmail.ssb000ss.words.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gmail.ssb000ss.words.R;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DB_CODE_RESULT_ADD_WORD = "add_word";
    public static final String DB_CODE_RESULT_ADD_TRANSLATE ="add_translate" ;
    EditText word;
    EditText translation;
    Button btn_ok,btn_cancel;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        intent=getIntent();
        word=(EditText) findViewById(R.id.et_detail_word);
        translation= (EditText) findViewById(R.id.et_detail_translate);
        btn_cancel = (Button) findViewById(R.id.btn_detail_cancel);
        btn_ok = (Button) findViewById(R.id.btn_detail_ok);
        btn_ok.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        String newWord=word.getText().toString();
        String newTranslate=translation.getText().toString();
        int id=v.getId();
        switch (id){
            case R.id.btn_detail_ok:
                if(!((newWord.isEmpty())||(newTranslate.isEmpty()))){
                intent.putExtra(DB_CODE_RESULT_ADD_WORD,newWord);
                intent.putExtra(DB_CODE_RESULT_ADD_TRANSLATE,newTranslate);
                setResult(RESULT_OK,intent);
                }
                else setResult(RESULT_CANCELED,intent);
                finish();
                break;
            case R.id.btn_detail_cancel:
                setResult(RESULT_CANCELED,intent);
                finish();
                break;
        }
    }
}
