package com.android.learnit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by ANMOL on 11/25/2015.
 */
public class Detail4Fragment extends Fragment {

    ImageView img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Test", "hello");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /*
    public void startAnimation(){

        final ObjectAnimator fade = ObjectAnimator.ofFloat(img,"alpha",1,0);
        fade.setDuration(1000);
        fade.setStartDelay(500);

        final ObjectAnimator fade2 = ObjectAnimator.ofFloat(img,"alpha",0,1);
        fade2.setDuration(1000);

        final ObjectAnimator fade3 = ObjectAnimator.ofFloat(img,"alpha",1,0);
        fade3.setDuration(1000);
        fade3.setStartDelay(1000);

        final ObjectAnimator fade4 = ObjectAnimator.ofFloat(img,"alpha",0,1);
        fade4.setDuration(1000);


        fade.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                img.setBackgroundColor(getResources().getColor(R.color.vpcolor1));
                img.setImageResource(R.drawable.spanish);
                fade2.start();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        fade2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                fade3.start();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


        fade3.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                img.setBackgroundColor(getResources().getColor(R.color.vpcolor1));
                img.setImageResource(R.drawable.english);
                fade4.start();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        fade.start();

    }
    */




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page5, container, false);
        img = (ImageView) view.findViewById(R.id.img_5);

        /*
        b=(Button) view.findViewById(R.id.loginButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), LoginActivity.class));

            }
        });
        */

        return view;

    }
}
