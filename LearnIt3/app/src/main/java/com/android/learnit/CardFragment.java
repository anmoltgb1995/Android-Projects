package com.android.learnit;

/**
 * Created by sarthakalang on 03/11/15.
 */

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class CardFragment extends Fragment{

    private String word,definition;
    int flag=0;
    public CardFragment(){}

    public CardFragment(String word,String definition){
        this.word= word;
        this.definition = definition;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.txt, container, false);

        final flashcard flash = (flashcard) getActivity();

        final RelativeLayout frontView = (RelativeLayout) rootView.findViewById(R.id.cardLayout);
        final TextView frontTextView = (TextView) rootView.findViewById(R.id.front);
        final TextView backView = (TextView) rootView.findViewById(R.id.back);

        Button ttsButton = (Button) rootView.findViewById(R.id.ttsButton);

        backView.setVisibility(View.INVISIBLE);
       AlphaAnimation alpha1 = new AlphaAnimation(0.0f, 1.0f);
        alpha1.setDuration(300);
        alpha1.setStartOffset(600);

        if(flag==0) {
            frontView.startAnimation(alpha1);
            flag++;
        }


        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/helvetica-neue-ultra-light.ttf");
        frontTextView.setTypeface(custom_font);

        custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/HelveticaNeue-Regular.ttf");
        backView.setTypeface(custom_font);

        frontTextView.setText(word);
        backView.setText(definition);

        frontTextView.setText(word);
        backView.setText(definition);


        ttsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //For speaking out the word
                //speakOut() function is called from flashcard activity
                flash.speakOut();
            }
        });


        frontView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlipRoutine(frontView, backView);
            }
        });

        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlipRoutine(backView, frontView);
            }
        });

        return rootView;
    }






    public void FlipRoutine(final View frontView, final View backView){

        final long flipDuration = 200;


        ObjectAnimator flip1
                = ObjectAnimator.ofFloat(backView, "rotationX", 0.0f, 180.0f);
        flip1.setDuration(0);
        //flip1.setStartDelay(1000);


        ObjectAnimator flip2
                = ObjectAnimator.ofFloat(frontView, "rotationX", 0.0f, 90.0f);
        flip2.setDuration(flipDuration/2);
        //flip2.setStartDelay(1000);
        flip2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {


                backView.setVisibility(View.INVISIBLE);
                frontView.setClickable(false);
                backView.setClickable(false);

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                frontView.setVisibility(View.INVISIBLE);
                backView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });



        ObjectAnimator flip3
                = ObjectAnimator.ofFloat(backView, "rotationX", 180.0f, 360.0f);
        flip3.setDuration(flipDuration);
        flip3.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                backView.bringToFront();
                backView.setClickable(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


        flip1.start();
        flip2.start();
        flip3.start();


    }




}
