package com.example.anmol.customlistview;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    final static ArrayList<HashMap<String, ?>> data = new ArrayList<HashMap<String, ?>>();

    static{
        HashMap<String, Object> row  = new HashMap<String, Object>();
        row.put("name", "Sanjeev Patel");
        row.put("svcenter", "PRIME HONDA - VAISHALI");
        row.put("addr", "Ansal Plaza, Main Road, Ghaziabad, Uttar Pradesh");
        row.put("phone", "4388583434");
        row.put("pin", "201010");
        data.add(row);
        row  = new HashMap<String, Object>();
        row.put("name", "Krishna Asawa");
        row.put("svcenter", "PRIME HONDA - EAST DELHI");
        row.put("addr", "Plot -1 , Patparganj Industrial Complex, Delhi");
        row.put("phone", "4838383833");
        row.put("pin", "324321");
        data.add(row);
        row  = new HashMap<String, Object>();
        row.put("name", "Ankur Kulhari");
        row.put("svcenter", "PRIME HYUNDAI- SOUTH DELHI");
        row.put("addr", "Plot -1 , Ashoka Puram, Delhi");
        row.put("phone", "9854545433");
        row.put("pin", "110065");
        data.add(row);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.list_up);

        ListView listView = (ListView) findViewById(R.id.list);

        SimpleAdapter adapter = new SimpleAdapter(this,
                data,
                R.layout.list_row,
                new String[] {"name","svcenter","addr","pin","phone"},
                new int[] { R.id.text1, R.id.text2,R.id.text3,R.id.text4,R.id.text5});
        listView.setAdapter(adapter);

        listView.startAnimation(anim);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
