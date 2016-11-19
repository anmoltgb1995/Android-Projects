package com.android.learnit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Predefined extends Activity {

    public ListView l;
    public static Activity a;
    WordsDBHelper w;
    SQLiteDatabase db;
    Context ctx;
    String[] setlist;
    public int flag=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predefined);

        ArrayAdapter<String> listadapter;
        ctx=this;
        a=this;

        w=new WordsDBHelper(this);
        db=w.getWritableDatabase();
        Cursor cr=w.getset("pd");
        setlist=new String[cr.getCount()];
        if (cr.moveToFirst())
        {
            for (int i = 0; i < cr.getCount(); i++)
            {
                setlist[i] = cr.getString(0).substring(0, 1).toUpperCase() + cr.getString(0).substring(1);
                cr.moveToNext();
            }
        }
        cr.close();

        List<String> a_setlist = new ArrayList<String>(Arrays.asList(setlist));
        //  List<String> a_countlist = new ArrayList<String>(Arrays.asList(countlist));

        listadapter = new ArrayAdapter<String>(Predefined.this, R.layout.pdsetname, R.id.pdsetn, a_setlist);
        l=(ListView) findViewById(R.id.predefinedlist);

        l.setAdapter(listadapter);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String set = setlist[position];
                Intent intent = new Intent(Predefined.this, Dashboard.class);
                intent.putExtra("key", set);
                intent.putExtra("flag",flag);
                startActivity(intent);
            }
        });
        l.setItemsCanFocus(true);

    }

}
