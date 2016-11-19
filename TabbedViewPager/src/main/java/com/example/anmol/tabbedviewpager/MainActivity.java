package com.example.anmol.tabbedviewpager;

import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    FloatEvaluator floatEvaluator,eval2,eval3;
    ViewPager myPager;
    View indicator;
    TextView head1, head2;
    ImageView head2img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatEvaluator = new FloatEvaluator();
        eval2 = new FloatEvaluator();

        indicator = findViewById(R.id.indicator);
        head1 = (TextView)findViewById(R.id.head1);
        head2 = (TextView)findViewById(R.id.head2);
        head2img = (ImageView) findViewById(R.id.head2img);

        head2.setVisibility(View.GONE);
        head2img.setVisibility(View.VISIBLE);

        head1.setTextColor(getResources().getColor(R.color.tabSelected));
        //head2.setTextColor(getResources().getColor(R.color.tabUnSelected));

        myPager = (ViewPager) findViewById(R.id.pager);

        myPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return new MyFragment(i);
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        myPager.addOnPageChangeListener(new CustomOnPageChangeListener());


        head1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPager.setCurrentItem(0);
            }
        });


        /*
        head2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPager.setCurrentItem(1);
            }
        });
        */


    }



    class CustomOnPageChangeListener implements ViewPager.OnPageChangeListener {

        //View xview = head2;

        @Override
        public void onPageScrolled(int i, float v, int i1) {

            if (i == 0)
                indicator.setTranslationX(floatEvaluator.evaluate(v, 0, head2.getLeft()));


            if(i==0) {

                if (v < 0.5f) {
                    head2.setVisibility(View.GONE);
                    head2img.setVisibility(View.VISIBLE);
                    head2img.setScaleX(eval2.evaluate(v, 1, 0));
                    head2img.setScaleY(eval2.evaluate(v, 1, 0));
                } else if (v > 0.5) {
                    head2.setVisibility(View.VISIBLE);
                    head2img.setVisibility(View.GONE);
                    head2.setScaleX(eval2.evaluate(v, 0, 1));
                    head2.setScaleY(eval2.evaluate(v, 0, 1));
                }

            }



        }

        @Override
        public void onPageSelected(int i) {

            if (i == 1) {
                //indicator.setTranslationX(head2.getLeft());
                //head2.setTextColor(getResources().getColor(R.color.tabSelected));
                head1.setTextColor(getResources().getColor(R.color.tabUnSelected));
            } else if (i == 0) {
                //head2.setTextColor(getResources().getColor(R.color.tabUnSelected));
                head1.setTextColor(getResources().getColor(R.color.tabSelected));
            }

                /*
                ObjectAnimator move = null;

                if(i == 0)
                    move = ObjectAnimator.ofFloat(indicator, "translationX", 0, indicator.getLeft()-head1.getLeft());
                if(i == 1)
                    move = ObjectAnimator.ofFloat(indicator, "translationX", indicator.getLeft(), head2.getLeft());

                move.setDuration(200);
                move.setInterpolator(new AccelerateInterpolator());
                move.start();
                */

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }

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
