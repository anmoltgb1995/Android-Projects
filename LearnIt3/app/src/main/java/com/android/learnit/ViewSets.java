package com.android.learnit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewSets extends Activity {
    public ListView l;
    public static Activity a;
    WordsDBHelper w;
    public int size;
    NewWordsDB newWordsDB;

    public String username2;
    String[] setlist2,deflist,wordlist;
    SQLiteDatabase db,db2,db3;
    Context ctx;
    FloatingActionButton fabButton;

    String[] setlist;
    int[] countlist;
    BackgroundTask b;



    TextView t;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_view_sets, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.sync){
           // username2=Username;
            ctx=this;
            newWordsDB=new NewWordsDB(this);
            db2=newWordsDB.getWritableDatabase();
            db3=newWordsDB.getReadableDatabase();


            Cursor cr=newWordsDB.getAllData();
            wordlist=new String[cr.getCount()];
            deflist=new String[cr.getCount()];
            setlist2=new String[cr.getCount()];
            size=cr.getCount();
            if (cr.moveToFirst())
            {
                for (int i = 0; i < cr.getCount(); i++)
                {
                    setlist2[i] = cr.getString(1);
                    cr.moveToNext();
                }
            }
            if (cr.moveToFirst())
            {
                for (int i = 0; i < cr.getCount(); i++)
                {
                    wordlist[i] = cr.getString(2);
                    cr.moveToNext();
                }
            }
            if (cr.moveToFirst())
            {
                for (int i = 0; i < cr.getCount(); i++)
                {
                    deflist[i] = cr.getString(3);
                    cr.moveToNext();
                }
            }
            cr.close();




            String jsonText="";


            try {
                JSONArray entries = new JSONArray();

                for (int i = 0; i < size; ++i) {

                    JSONObject Entry = new JSONObject();
                    Entry.put("set", setlist2[i]);
                    Entry.put("word", wordlist[i]);
                    Entry.put("def", deflist[i]);
                    entries.add(Entry);

                }

                JSONObject Entries = new JSONObject();
                Entries.put("entries",entries);

                jsonText = JSONValue.toJSONString(Entries);
            }catch(JSONException j){}



            String naam="";


            try {
                FileInputStream fileIn=openFileInput("mytextfile.txt");
                InputStreamReader InputRead= new InputStreamReader(fileIn);

                char[] inputBuffer= new char[100];

                int charRead;

                while ((charRead=InputRead.read(inputBuffer))>0) {
                    // char to string conversion
                    String readstring=String.copyValueOf(inputBuffer,0,charRead);
                    naam +=readstring;
                }
                InputRead.close();


            } catch (Exception e) {
                e.printStackTrace();
            }












       //  Toast.makeText(ViewSets.this,naam, Toast.LENGTH_LONG).show();

             newWordsDB.clear(db2);
            b=new BackgroundTask(this);
            b.execute("export",naam,jsonText);




        }
        else if(id==R.id.Pdsets){
            Intent intent1=new Intent(ViewSets.this,Predefined.class);
            startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // Intent intent=getIntent();
       // Username=intent.getStringExtra("user");
      // Toast.makeText(ViewSets.this, Username, Toast.LENGTH_SHORT).show();

        ArrayAdapter<String> listadapter;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sets);
        ctx=this;
        a=this;

        t=(TextView) findViewById(R.id.inv);
        w=new WordsDBHelper(this);
        db=w.getWritableDatabase();
        Cursor cr=w.getset("nd");
        if(cr.getCount()==0){
            t.setVisibility(View.VISIBLE);
        }
        setlist=new String[cr.getCount()];
        if (cr.moveToFirst())
        {
            for (int i = 0; i < cr.getCount(); i++)
            {
                setlist[i] = cr.getString(0);
                cr.moveToNext();
            }
        }
        cr.close();
        /*int j=0;
        for(int i=0;i<setlist.length;++i){
            Cursor cr2=w.getsetData(setlist[i]);
            countlist[j]=cr2.getCount();
            j++;
            cr2.close();
        }
        */

        List<String> a_setlist = new ArrayList<String>(Arrays.asList(setlist));
        //  List<String> a_countlist = new ArrayList<String>(Arrays.asList(countlist));

        listadapter = new ArrayAdapter<String>(ViewSets.this, R.layout.setname, R.id.setn, a_setlist);
        l=(ListView) findViewById(R.id.setlist);

        l.setAdapter(listadapter);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String set = setlist[position];
                Intent intent = new Intent(ViewSets.this, Dashboard.class);
                intent.putExtra("key", set);
                intent.putExtra("flag",0);
                startActivity(intent);
            }
        });
        l.setItemsCanFocus(true);

        l.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                String sett = setlist[position];
                                w.removeset(sett, db);
                                Toast.makeText(ViewSets.this, "Set deleted", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(ViewSets.this,ViewSets.class);
                                startActivity(intent);
                                ViewSets.a.finish();
                                //Yes button clicked
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setMessage("Do you want to delete this set?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            return true;}
        });





        fabButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_add_white_24dp))
                .withButtonColor(getResources().getColor(R.color.color7))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 16)
                .create();








        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ViewSets.this, SetActivity.class);
                i.putExtra("user",username2);
                startActivity(i);

                //overridePendingTransition(R.anim.slide_up_info, R.anim.no_change);
                finish();


            }
        });



    }

}


