package com.android.learnit;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Test", "hello");

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_1, container, false);

        /*
        TextView title= (TextView) view.findViewById(R.id.heading_main);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/cheddar.ttf");
        title.setTypeface(custom_font);


        TextView subhead = (TextView) view.findViewById(R.id.subheading_main);


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 195, 10, 10);
        */

        ImageView img = (ImageView) view.findViewById(R.id.logo_main);
        TextView subhead2 = (TextView) view.findViewById ( R.id.subheading2_main);
        //img.setLayoutParams(params);

        img.setScaleY(0);
        img.setScaleX(0);
        subhead2.setAlpha(0f);

        ObjectAnimator scale1X = ObjectAnimator.ofFloat(img, "scaleX", 0f, 1.2f);
        scale1X.setDuration(500);

        ObjectAnimator scale1Y = ObjectAnimator.ofFloat(img, "scaleY", 0f, 1.2f);
        scale1Y.setDuration(500);

        ObjectAnimator scale2X = ObjectAnimator.ofFloat(img, "scaleX", 1.2f, 1f);
        scale2X.setDuration(250);

        ObjectAnimator scale2Y = ObjectAnimator.ofFloat(img, "scaleY", 1.2f, 1f);
        scale2Y.setDuration(250);

        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(subhead2, "alpha", 0f, 1f);
        fadeIn.setDuration(250);

        final AnimatorSet animSet2 = new AnimatorSet();
        animSet2.playTogether(scale2X, scale2Y,fadeIn);

        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(scale1X,scale1Y);
        animSet.setStartDelay(2050);

        animSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                animSet2.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animSet.start();

        /*
        AccelerateDecelerateInterpolator acc= new AccelerateDecelerateInterpolator();

        TranslateAnimation move = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.ABSOLUTE, -75);
        move.setDuration(700);
        move.setStartOffset(550);
        move.setInterpolator(acc);
        move.setFillAfter(true);

        animListener scaleListener = new animListener(img,move);
        scale.setAnimationListener(scaleListener);


        AlphaAnimation alpha1 = new AlphaAnimation(0.0f, 1.0f);
        alpha1.setDuration(1000);
        alpha1.setStartOffset(1500);
        AlphaAnimation alpha2 = new AlphaAnimation(0.0f, 1.0f);
        alpha2.setDuration(1000);
        alpha2.setStartOffset(1500);
        AlphaAnimation alpha3 = new AlphaAnimation(0.0f, 1.0f);
        alpha3.setStartOffset(5000);
        alpha3.setFillAfter(true);

        */

        //img.startAnimation(scale);
        //title.startAnimation(alpha1);
        //subhead.startAnimation(alpha2);
        //subhead2.startAnimation(alpha3);



        return view;
    }
}