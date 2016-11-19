package com.android.learnit;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Dashboard extends Activity {
String set;
    float x,y;
    TextView t;
    public int flag;
    WordsDBHelper databaseHelper;
    ListView lvv;
    SQLiteDatabase db;
    CustomCursorAdapter2 ad;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        b1=(Button) findViewById(R.id.bflashcard);
        Intent i=getIntent();
        flag=i.getIntExtra("flag",0);
         set=i.getStringExtra("key");
        t=(TextView) findViewById(R.id.dashset);
        t.setText(set);


        databaseHelper=new WordsDBHelper(this);
        db=databaseHelper.getWritableDatabase();


        lvv=(ListView) findViewById(R.id.dlist);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                try {if(flag==1){
                    ad = new CustomCursorAdapter2(Dashboard.this, databaseHelper.getsetData(set,"pd"), set);}
                    else{
                    ad = new CustomCursorAdapter2(Dashboard.this, databaseHelper.getsetData(set,"nd"), set);
                }
                    lvv.setAdapter(ad);
                } catch (NullPointerException n) {
                }
            }
        });



        b1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    x = event.getRawX();
                    y = event.getRawY();
                }

                return false;
            }
        });


    }



    public void flash(View view){
        Intent intent=new Intent(Dashboard.this,flashcard.class);
        intent.putExtra("key",set);
        intent.putExtra("x",x);
        intent.putExtra("y",y);
        intent.putExtra("flag",flag);
        startActivity(intent);

    }


public void ques(View view){
    Intent intent=new Intent(Dashboard.this,Quiz.class);
    intent.putExtra("key",set);
    intent.putExtra("flag",flag);
    startActivity(intent);
}
    public void set(View view){
        Intent intent=new Intent(Dashboard.this,match_game.class);
        intent.putExtra("key",set);
        intent.putExtra("flag",flag);
        startActivity(intent);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
       // return super.onPrepareOptionsMenu(menu);
        MenuItem button=menu.findItem(R.id.edit_set);
        if(flag==1){
            button.setVisible(false);
        }
        else{
            button.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.edit_set){
            Intent intent=new Intent(Dashboard.this,firstact.class);
            intent.putExtra("key",set);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
