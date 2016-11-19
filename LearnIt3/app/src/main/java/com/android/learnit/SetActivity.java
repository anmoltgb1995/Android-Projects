package com.android.learnit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class SetActivity extends Activity {
    EditText e1;
    EditText e2;
    EditText e3;
    EditText e4;
    EditText e5;
    EditText e6;
    EditText e7;
    EditText e8;
    EditText e9;
    EditText e10;
    EditText set;
    EditText[] wlist;
    EditText[] dlist;
    Context ctx=this;
   public int flag=1;
    NewWordsDB newWordsDB;
    SQLiteDatabase db,db2;
    String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intentt=getIntent();
       username=  intentt.getStringExtra("user");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        e1=(EditText) findViewById(R.id.w1);
        e2=(EditText) findViewById(R.id.d1);
        e3=(EditText) findViewById(R.id.w2);
        e4=(EditText) findViewById(R.id.d2);
        e5=(EditText) findViewById(R.id.w3);
        e6=(EditText) findViewById(R.id.d3);
        e7=(EditText) findViewById(R.id.w4);
        e8=(EditText) findViewById(R.id.d4);
        e9=(EditText) findViewById(R.id.w5);
        e10=(EditText) findViewById(R.id.d5);
        set=(EditText) findViewById(R.id.set);




       wlist=new EditText[] {e1,e3,e5,e7,e9};
        dlist=new EditText[] {e2,e4,e6,e8,e10};


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_set, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.setdone){
           // f.fin();
            String setname=set.getText().toString().trim();
            if(!setname.equals("")){


            int i;
            String[] words=new String[5];
            String[] defs=new String[5];
            flag=0;
            WordsDBHelper wdb = new WordsDBHelper(ctx);
                NewWordsDB n=new NewWordsDB(ctx);
            for(i=0;i<5;++i){
                words[i]=wlist[i].getText().toString();
                defs[i]=dlist[i].getText().toString();
                if((!((words[i].equals(""))&&(defs[i].equals(""))))||(!((words[i].equals(""))||(defs[i].equals(""))))) {
                    wdb.insert2(wdb, setname, words[i], defs[i],"nd");
                    n.insert(n, setname, words[i], defs[i]);
                }
                if((words[i].equals(""))&&(defs[i].equals(""))){
                    flag++;
                }


            }
            if(flag==5){
                    Toast.makeText(this, "You must fill at least one word and definition to save your set", Toast.LENGTH_SHORT).show();
            }
            else {

                Intent intent = new Intent(SetActivity.this, ViewSets.class);
                intent.putExtra("user",username);
                startActivity(intent);
                finish();
            }
            }
            else{
                Toast.makeText(this,"Set name is empty",Toast.LENGTH_SHORT).show();
            }



        }
        return super.onOptionsItemSelected(item);
    }



}
