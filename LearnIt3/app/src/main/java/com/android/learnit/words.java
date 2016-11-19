package com.android.learnit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class words extends Activity {
    Context ctx=this;
    EditText et1;
    EditText et2;
    String set;


    firstact f=new firstact();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_words, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.done){
            f.fin();
            Intent intent=new Intent(words.this,firstact.class);
            intent.putExtra("key",set);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        Intent in=getIntent();
        set=in.getStringExtra("setv");
        et1=(EditText) findViewById(R.id.word);
        et2=(EditText) findViewById(R.id.desc);

    }


    public void onbut(View view){
        String word=et1.getText().toString();
        String desc=et2.getText().toString();
        if((word.equals(""))||(desc.equals(""))){
            Toast.makeText(this,"Text fields are empty, Please try again!",Toast.LENGTH_SHORT).show();
        }
        else {
            et1.setText("");
            et2.setText("");
            WordsDBHelper wdb = new WordsDBHelper(ctx);
            NewWordsDB n=new NewWordsDB(ctx);
            wdb.insert2(wdb,set,word, desc,"nd");
            n.insert(n,set,word,desc);
        }

    }




}
