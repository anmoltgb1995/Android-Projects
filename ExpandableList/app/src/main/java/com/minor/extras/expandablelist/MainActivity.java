package com.minor.extras.expandablelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    final ArrayList<ArrayList<HashMap<String, ?>>> child = new ArrayList<ArrayList<HashMap<String, ?>>>();
    final ArrayList<HashMap<String, ?>> group = new ArrayList<HashMap<String, ?>>();
    int lastExpandedGroup = 0;
    ExpandableListView packgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packg);

        /**
         * ExpandableListView can be used to display list within a list
         * The outer list can be referred to as group
         * The inner list can be referred to as child
         * A group can have multiple child views.
         * Group needs to be represented by a layout and data for the groups is defined by a list of hashmaps.
         * Child needs to be represented by a layout and data for child views is defined by a list of a list of hashmaps.
         * Index of Outer list represent the group which the children belong to.
         * Index of Inner list represents each child belonging to that group.
         * The hashmap represents the data for each child.
         */

        packgList = (ExpandableListView) findViewById(R.id.packgList);

        prepareDummyData();

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(getApplicationContext(),group,R.layout.packg_list_group,
                new String[] {"header","price"},new int[]{R.id.header,R.id.price},child,R.layout.packg_list_child,new String[]{"parts","desc"},
                new int[]{R.id.partList,R.id.desc}){
            @Override
            public void onGroupExpanded(int groupPosition) {
                if (lastExpandedGroup != -1
                        && groupPosition != lastExpandedGroup) {
                    packgList.collapseGroup(lastExpandedGroup);
                }
                super.onGroupExpanded(groupPosition);
                lastExpandedGroup = groupPosition;
            }
        };

        packgList.setAdapter(adapter);

    }



    private void prepareDummyData() {
        //allocate new hashmap to add data for group n child
        HashMap<String, Object> row0 = new HashMap<String, Object>();   //hashmap to store data for group
        HashMap<String, Object> row1 = new HashMap<String, Object>();   //hashmap to store data for child views
        row0.put("header", "Short Service");
        row0.put("price", "*999");
        group.add(row0);            //add hashmap to data list for group


        ArrayList list = new ArrayList<HashMap<String,Object>>();      //allocate new list of the child(s) that belong to above group
        row1.put("parts", "Change of Oil and Filter\n" +
                "Chain Adjustment\n" +
                "Lubrication");
        row1.put("desc", "The Short Service Consists of a Thorough Inspection of Virtually all the Items Covered  in a Full Service - Almost Everything we " +
                "Can Check Without Actually Dismantling Your Vehicle.");
        list.add(row1);        //add hashmap to data list for child view
        child.add(list);       //add list of child(s) to outer list

        row0 = new HashMap<String, Object>();
        row1 = new HashMap<String, Object>();

        row0.put("header", "Full Service");
        row0.put("price", "*1699");
        group.add(row0);


        list = new ArrayList<HashMap<String,Object>>();
        //row1.put("parts", new String[]{"Engine and carburettors", "An Oil change - Specify your Grade", "New Oil Filter - OE specification", "New Air Filter - OE Specification",
        //        "Spark Plugs", "Replace Coolant"});
        row1.put("parts", "• An Oil change - Specify your Grade\n" +
                "New Oil Filter - OE specification\n" +
                "New Air Filter - OE Specification\n" +
                "Spark Plugs\n" +
                "Replace Coolant");
        row1.put("desc", "A Thorough Check of your Valve Clearances\n Carburettor/Fuel Injection System Balancing " +
                "Carburettor Adjustment");
        list.add(row1);
        child.add(list);

        row0 = new HashMap<String, Object>();
        row1 = new HashMap<String, Object>();

        row0.put("header", "Major Service");
        row0.put("price", "*3499");
        group.add(row0);

        list = new ArrayList<HashMap<String,Object>>();
        //row1.put("parts", new String[]{"Chassis & Brakes", "Brake Callipers Removed & Cleaned", "Brake Pistons Degreased,Brake Linings Checked",
        //        "Hydraulic Fluid System Flushed", "All Levers & Pivots Cleaned and Lubricated", "General Lubrication and Inspection",
        //        "Tyre Pressures Checked and Adjusted", "All Lights Checked"});
        row1.put("parts", "\uF0B7 Chassis & Brakes\n" +
                "• Brake Callipers Removed & Cleaned\n" +
                "• Brake Pistons Degreased,Brake Linings Checked\n" +
                "• Hydraulic Fluid System Flushed\n" +
                "• All Levers & Pivots Cleaned and Lubricated\n" +
                "• Throttle & Clutch Adjustment\n" +
                "• General Lubrication and Inspection\n" +
                "• Tyre Pressures Checked and Adjusted");
        row1.put("desc", "On all Major Servicing, it is Necessary to Check and Adjust the Valve Clearances on Your Motorcycle. " +
                "Certain Items like, Steering Head Bearings and Rear Swinging Arm Bearings, Wheel Bearings need to " +
                "be Inspected as well. This is in Addition to Everything you can Expect from the Full Service");
        list.add(row1);
        child.add(list);
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
