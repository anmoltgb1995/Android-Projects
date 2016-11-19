package com.android.learnit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

public class firstact extends Activity {
    public static Activity a;
    CustomCursorAdapter ad;
    ListView lvv;
    Context context;
    String sett;
SQLiteDatabase db;

    WordsDBHelper databaseHelper;




    public void fin(){
    finish();
}




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_firstact);
        a=this;

        Intent i=getIntent();
         sett=i.getStringExtra("key");
        databaseHelper=new WordsDBHelper(this);
        db=databaseHelper.getWritableDatabase();


        lvv=(ListView) findViewById(R.id.lv);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    ad = new CustomCursorAdapter(firstact.this, databaseHelper.getsetData(sett,"nd"), sett);
                    lvv.setAdapter(ad);
                } catch (NullPointerException n) {
                }
            }
        });
        lvv.setItemsCanFocus(true);



        FloatingActionButton fabButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_add_white_24dp))
                .withButtonColor(getResources().getColor(R.color.color7))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 16)
                .create();
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(firstact.this, words.class);
                i.putExtra("setv", sett);
                startActivity(i);
                overridePendingTransition(R.anim.slide_up_info, R.anim.no_change);

                finish();



            }
        });
    }
    public void refresh(){
        finish();
        startActivity(getIntent());
    }





}
