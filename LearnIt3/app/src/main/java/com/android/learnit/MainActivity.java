package com.android.learnit;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyAdapter mAdapter;
    private ViewPager mPager;
    int newPage,lastPage = 5;
    TextView title;
    TextView detail;
    TextView login;
    View PageIndicatorLayout;
    ImageView imgView1,imgView2,img;
    ImageView pageInd1, pageInd2, pageInd3,pageInd4,pageInd5;
    Integer colors[] = null, colors2[] = null;
    String details[] = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    FloatEvaluator floatEvaluator = new FloatEvaluator();
    IntEvaluator intEvaluator = new IntEvaluator();
    IntEvaluator intEvaluator2 =  new IntEvaluator();
    //ScaleAnimation enlarge,shrink;
    Detail4Fragment fragment;

    private void setUpColors() {

        Integer color1 = getResources().getColor(R.color.vpcolor1);
        Integer color2 = getResources().getColor(R.color.vpcolor2);
        Integer color3 = getResources().getColor(R.color.vpcolor3);
        Integer color4 = getResources().getColor(R.color.vpcolor4);
        Integer color5 = getResources().getColor(R.color.vpcolor5);

        colors = new Integer[]{color1, color2, color3, color4, color5};
    }

    private void setUpTextColors() {

        Integer color1 = getResources().getColor(R.color.textcolor1);
        Integer color2 = getResources().getColor(R.color.textcolor2);
        Integer color3 = getResources().getColor(R.color.textcolor3);
        Integer color4 = getResources().getColor(R.color.textcolor4);

        colors2 = new Integer[]{color1, color2, color3, color4};
    }

    private void setUpText() {

        String detail1 = getResources().getString(R.string.subheading_main);
        String detail2 = getResources().getString(R.string.text_1);
        String detail3 = getResources().getString(R.string.text_2);
        String detail4 = getResources().getString(R.string.text_3);
        String detail5 = getResources().getString(R.string.text_4);

        details = new String[]{detail1,detail2,detail3,detail4,detail5};
    }



    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {

        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if(pref.getBoolean("activity_executed", false)){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor ed = pref.edit();
            ed.putBoolean("activity_executed", true);
            ed.commit();
        }


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title= (TextView) findViewById(R.id.heading_main);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/cheddar.ttf");
        title.setTypeface(custom_font);

        //subhead = (TextView) findViewById(R.id.subheading2_main);

        setUpTextColors();
        setUpText();
        setUpColors();
        fragment = new Detail4Fragment();

        detail= (TextView) findViewById(R.id.detailView);

        /*
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeue-Regular.ttf");
        detail.setTypeface(custom_font2);
        */


        login=(TextView) findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, LoginActivity.class));

            }
        });

        login.setVisibility(View.GONE);

        PageIndicatorLayout = findViewById(R.id.pageIndicatorLayout);
        pageInd1 = (ImageView) findViewById(R.id.page1);
        pageInd2 = (ImageView) findViewById(R.id.page2);
        pageInd3 = (ImageView) findViewById(R.id.page3);
        pageInd4 = (ImageView) findViewById(R.id.page4);
        //pageInd5 = (ImageView) findViewById(R.id.page5);

        mAdapter = new MyAdapter(getSupportFragmentManager());

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        mPager.setOffscreenPageLimit(4);
        mPager.addOnPageChangeListener(new CustomOnPageChangeListener());

        title.setAlpha(0f);
        //title.setTranslationY(80);

        AnimatorSet animSet = new AnimatorSet();

        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(title, "alpha", 0f, 1f);
        fadeIn.setStartDelay(2000);
        fadeIn.setDuration(1000);

        /*
        ObjectAnimator lift = ObjectAnimator.ofFloat(title, "translationY", 200, 0);

        //ObjectAnimator scale = ObjectAnimator.ofFloat(title, "scaleX", 1f, 0.66f);

        ObjectAnimator scale = ObjectAnimator.ofObject(title,"textSize",new FloatEvaluator(),68,56);

        animSet.playTogether(lift, scale);
        animSet.setStartDelay(3050);
        animSet.setDuration(750);

        fadeIn.start();
        animSet.start();
        */

        fadeIn.start();

        /**
         * Remove individual page backgrounds to enable background transition
         */

    }

    public class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {

            super(fm);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ImageFragment();
                case 1:
                    return new DetailFragment(R.drawable.img_2);
                case 2:
                    return new Detail2Fragment(R.drawable.img_3);
                case 3:
                    return new Detail3Fragment(R.drawable.img_4);
                case 4:
                    return fragment;

                default:
                    return null;
            }
        }
    }

    private class CustomOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            final float size_enlarged = 1.5f;
            float ds;
            float max = size_enlarged - 1;

            if (position < (mAdapter.getCount() - 1) && position < (colors.length - 1)) {

                mPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));
                //title.setTextColor((Integer) argbEvaluator.evaluate(positionOffset, colors2[position], colors2[position + 1]));
                if(position==0) {
                    title.setTranslationY(intEvaluator.evaluate(positionOffset, 72, 0));
                    title.setTextColor((Integer) argbEvaluator.evaluate(positionOffset, colors2[0], colors2[2]));
                    PageIndicatorLayout.setAlpha(positionOffset);
                    float size = floatEvaluator.evaluate(positionOffset,1f,0.82f);
                    title.setScaleX(size);
                    title.setScaleY(size);
                }

                switch(position) {
                    case 0:
                        imgView1 = null;
                        imgView2 = pageInd1;
                        break;
                    case 1:
                        imgView1 = pageInd1;
                        imgView2 = pageInd2;
                        break;
                    case 2:
                        imgView1 = pageInd2;
                        imgView2 = pageInd3;
                        break;
                    case 3:
                        imgView1 = pageInd3;
                        imgView2 = pageInd4;
                        break;

                }

                ds = max * positionOffset;
                if(imgView1!=null) {
                    imgView1.setScaleX(size_enlarged - ds);
                    imgView1.setScaleY(size_enlarged - ds);
                }
                imgView2.setScaleX(1f + ds);
                imgView2.setScaleY(1f + ds);

            } else {

                // the last page color
                mPager.setBackgroundColor(colors[colors.length - 1]);
                //title.setTextColor(colors2[colors2.length - 1]);

            }

            /*
            if(positionOffset==0){
                detail.setText(details[position]);
            }
            */

            /*
            if(positionOffset==0)
            {

                newPage=position;
                if(newPage == lastPage)
                    return;

                imgView1 = imgView2;

                switch(newPage) {
                    case 0:
                        imgView2 = (ImageView) findViewById(R.id.page1);
                        break;
                    case 1:
                        imgView2 = (ImageView) findViewById(R.id.page2);
                        break;
                    case 2:
                        imgView2 = (ImageView) findViewById(R.id.page3);
                        break;
                    case 3:
                        imgView2 = (ImageView) findViewById(R.id.page4);
                        break;
                }

                lastPage = newPage;

                if(imgView1!=null && imgView2!=null) {

                    ObjectAnimator shrinkX
                            = ObjectAnimator.ofFloat(imgView1, "scaleX", finalSize, 1f);
                    shrinkX.setDuration(200);

                    ObjectAnimator shrinkY
                            = ObjectAnimator.ofFloat(imgView1, "scaleY", finalSize, 1f);
                    shrinkY.setDuration(200);

                    ObjectAnimator enlargeX
                            = ObjectAnimator.ofFloat(imgView2, "scaleX", 1f, finalSize);
                    enlargeX.setDuration(200);

                    ObjectAnimator enlargeY
                            = ObjectAnimator.ofFloat(imgView2, "scaleY", 1f, finalSize);
                    enlargeY.setDuration(200);

                    enlargeX.start();
                    enlargeY.start();
                    shrinkX.start();
                    shrinkY.start();

                }

            }
            */

        }

        @Override
        public void onPageSelected(int position) {

            detail.setText(details[position]);

            if(position==mAdapter.getCount()-1)
                login.setVisibility(View.VISIBLE);
            else
                login.setVisibility(View.GONE);

            //if(position == 4)  fragment.startAnimation();
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }
    }
}
